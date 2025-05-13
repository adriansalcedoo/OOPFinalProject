package controller;

import model.*;
import java.util.List;

public class IncidentController {
    private IncidentManager manager = new IncidentManager();

    public void logIncident(String type, String description, String severity) {
        Incident incident = null;
        String id = "INC" + System.currentTimeMillis();

        switch (type) {
            case "DataBreach":
                incident = new DataBreach(id, description, severity);
                break;
            case "PhishingAttempt":
                incident = new PhishingAttempt(id, description, severity);
                break;
            case "MalwareInfection":
                incident = new MalwareInfection(id, description, severity);
                break;
            case "UnauthorizedAccess":
                incident = new UnauthorizedAccess(id, description, severity);
                break;
            case "DDoSAttack":
                incident = new DDoSAttack(id, description, severity);
                break;
        }

        if (incident != null) {
            manager.logIncident(incident);
        }
    }

    public List<Incident> getAllIncidents() {
        return manager.getAllIncidents();
    }
}
