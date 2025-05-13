
package view;

import controller.AuthController;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LoginView extends JFrame {
    private final AuthController authController = new AuthController();

    public LoginView() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("Login");
        JButton signupButton = new JButton("Sign Up");

        JPanel panel = new JPanel();
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(signupButton);

        loginButton.addActionListener((ActionEvent e) -> {
            if (authController.login(usernameField.getText(), new String(passwordField.getPassword()))) {
                JOptionPane.showMessageDialog(this, "Login successful");
                new DashboardView();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Login failed");
            }
        });

        signupButton.addActionListener((ActionEvent e) -> {
            new SignUpView();
        });

        add(panel);
        setVisible(true);
    }
}
