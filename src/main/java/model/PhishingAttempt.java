
package model;

public class PhishingAttempt extends Incident {
    public PhishingAttempt(String id, String description, String severity) {
        super(id, description, severity);
    }

    @Override
    public void notifyResponseTeam() {
        System.out.println("Phishing Attempt: Alerting user support team...");
    }
}
