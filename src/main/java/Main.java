import db.DatabaseManager;
import view.LoginView;

public class Main {
    public static void main(String[] args) {
        DatabaseManager.initialize(); //  Ensure tables are created
        new LoginView();              // Launch the UI
    }
}
