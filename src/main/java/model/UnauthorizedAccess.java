package model;


public class UnauthorizedAccess extends Incident {
    public UnauthorizedAccess(String id, String description, String severity) {
        super(id, description, severity);
    }

    @Override
    public void notifyResponseTeam() {
        System.out.println("Unauthorized Access: Notifying security operations center...");
    }
}