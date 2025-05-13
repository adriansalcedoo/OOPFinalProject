package utils;

import model.*;

public class IncidentDetector {

    public static Incident detect(String fileName, String content) {
        String id = "INC" + System.currentTimeMillis();
        String severity = "High";
        String text = content.toLowerCase();

        if (text.contains("eval(") && text.contains("base64")) {
            return new MalwareInfection(id, "Obfuscated script using base64 eval in " + fileName, severity);
        }

        if (text.contains("password") && text.contains("login") && text.contains("http://")) {
            return new PhishingAttempt(id, "Phishing pattern with credentials and external link in " + fileName, severity);
        }

        if (text.contains("cmd") && text.contains("exec")) {
            return new UnauthorizedAccess(id, "Remote code execution pattern detected in " + fileName, severity);
        }

        if (text.contains("ping") && text.split("ping").length > 10) {
            return new DDoSAttack(id, "Multiple ping instructions detected (possible DDoS) in " + fileName, severity);
        }

        return null; // No known threat pattern
    }
}
