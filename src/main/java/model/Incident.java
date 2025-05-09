
package model;

public abstract class Incident {
    protected String id;
    protected String description;
    protected String severity;

    public Incident(String id, String description, String severity) {
        this.id = id;
        this.description = description;
        this.severity = severity;
    }

    public abstract void notifyResponseTeam();

    public String getId() { return id; }
    public String getSeverity() { return severity; }
}
