package model;

public class DDoSAttack extends Incident {
    public DDoSAttack(String id, String description, String severity) {
        super(id, description, severity);
    }

    @Override
    public void notifyResponseTeam() {
        System.out.println("DDoS Attack: Alerting infrastructure and network teams...");
    }
}