
package view;

import controller.AuthController;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SignUpView extends JFrame {
    private final AuthController authController = new AuthController();

    public SignUpView() {
        setTitle("Sign Up");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JButton createButton = new JButton("Create Account");

        JPanel panel = new JPanel();
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(createButton);

        createButton.addActionListener((ActionEvent e) -> {
            authController.signup(usernameField.getText(), new String(passwordField.getPassword()));
            JOptionPane.showMessageDialog(this, "Account created!");
            dispose();
        });

        add(panel);
        setVisible(true);
    }
}
