
package controller;

import model.*;

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
        }
        if (incident != null) {
            manager.logIncident(incident);
        }
    }

    public String getIncidentReport() {
        StringBuilder sb = new StringBuilder();
        for (Incident incident : manager.getAllIncidents()) {
            sb.append(incident.getClass().getSimpleName())
              .append(" - ")
              .append(incident.getId())
              .append(" - ")
              .append(incident.getSeverity())
              .append("\n");
        }
        return sb.toString();
    }
}
