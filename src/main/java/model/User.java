package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    private String username;
    private String passwordHash;

    public User(String username, String rawPassword) {
        this.username = username;
        this.passwordHash = hashPassword(rawPassword);
    }

    public String getUsername() { return username; }

    public boolean checkPassword(String input) {
        return passwordHash.equals(hashPassword(input));
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes)
                sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
