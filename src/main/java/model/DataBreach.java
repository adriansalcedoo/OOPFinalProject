
package model;

public class DataBreach extends Incident {
    public DataBreach(String id, String description, String severity) {
        super(id, description, severity);
    }

    @Override
    public void notifyResponseTeam() {
        System.out.println("Data Breach: Notifying IT and Legal teams...");
    }
}
