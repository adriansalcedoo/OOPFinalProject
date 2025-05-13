package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;

public class FileScanner {
    private static final List<String> SUSPICIOUS_KEYWORDS = Arrays.asList("password", "eval(", "exec", "cmd", "system(", "base64", "xor");

    public static String scanFile(File file) {
        if (file == null || !file.exists()) {
            return "File does not exist.";
        }

        StringBuilder report = new StringBuilder();
        report.append("Scanning: ").append(file.getName()).append("\n");

        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] content = fis.readAllBytes();
            String text = new String(content);

            // Hashing
            report.append("SHA-256 Hash: ").append(computeSHA256(content)).append("\n");

            boolean found = false;
            for (String keyword : SUSPICIOUS_KEYWORDS) {
                if (text.toLowerCase().contains(keyword)) {
                    report.append("⚠ Suspicious keyword found: ").append(keyword).append("\n");
                    found = true;
                }
            }

            if (!found) {
                report.append("✔ No known suspicious patterns detected.\n");
            }
        } catch (IOException e) {
            report.append("Error reading file: ").append(e.getMessage()).append("\n");
        }

        return report.toString();
    }

    private static String computeSHA256(byte[] content) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(content);
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (Exception e) {
            return "Error computing hash";
        }
    }
}