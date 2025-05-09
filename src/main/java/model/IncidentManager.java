
package model;

import java.util.ArrayList;
import java.util.List;

public class IncidentManager {
    private List<Incident> incidents = new ArrayList<>();

    public void logIncident(Incident incident) {
        incidents.add(incident);
        incident.notifyResponseTeam();
    }

    public List<Incident> getAllIncidents() {
        return incidents;
    }
}
