package model;

import db.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IncidentManager {
    public void logIncident(Incident incident) {
        try (Connection conn = DatabaseManager.connect()) {
            String query = "INSERT INTO incidents (id, type, description, severity) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, incident.getId());
                stmt.setString(2, incident.getClass().getSimpleName());
                stmt.setString(3, incident.getDescription());
                stmt.setString(4, incident.getSeverity());
                stmt.executeUpdate();
                incident.notifyResponseTeam();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Incident> getAllIncidents() {
        List<Incident> incidents = new ArrayList<>();
        try (Connection conn = DatabaseManager.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM incidents")) {
            while (rs.next()) {
                String id = rs.getString("id");
                String type = rs.getString("type");
                String description = rs.getString("description");
                String severity = rs.getString("severity");

                Incident incident = switch (type) {
                    case "DataBreach" -> new DataBreach(id, description, severity);
                    case "PhishingAttempt" -> new PhishingAttempt(id, description, severity);
                    case "MalwareInfection" -> new MalwareInfection(id, description, severity);
                    case "UnauthorizedAccess" -> new UnauthorizedAccess(id, description, severity);
                    case "DDoSAttack" -> new DDoSAttack(id, description, severity);
                    default -> null;
                };

                if (incident != null) {
                    incidents.add(incident);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return incidents;
    }
}
