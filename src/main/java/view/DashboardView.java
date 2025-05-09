package view;

import controller.IncidentController;
import utils.FileScanner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class DashboardView extends JFrame {
    private final IncidentController controller = new IncidentController();

    public DashboardView() {
        setTitle("Dashboard");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load image
        ImageIcon backgroundIcon = new ImageIcon("/Users/adriansalcedo/Downloads/OOPFinalProject/src/main/resources/VIRUS DETECTOR.png");
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setLayout(new FlowLayout());
        setContentPane(backgroundLabel);

        JTextField descField = new JTextField(20);
        JComboBox<String> severityBox = new JComboBox<>(new String[]{"Low", "Medium", "High"});
        JComboBox<String> typeBox = new JComboBox<>(new String[]{"DataBreach", "PhishingAttempt"});
        JButton logButton = new JButton("Log Incident");
        JButton scanButton = new JButton("Scan File");
        JTextArea incidentArea = new JTextArea(10, 30);
        incidentArea.setEditable(false);

        JPanel panel = new JPanel();
        panel.setOpaque(false); // Make background transparent
        panel.add(new JLabel("Description:"));
        panel.add(descField);
        panel.add(new JLabel("Severity:"));
        panel.add(severityBox);
        panel.add(new JLabel("Type:"));
        panel.add(typeBox);
        panel.add(logButton);
        panel.add(scanButton);
        panel.add(new JScrollPane(incidentArea));
        backgroundLabel.add(panel);

        logButton.addActionListener((ActionEvent e) -> {
            controller.logIncident((String) typeBox.getSelectedItem(), descField.getText(), (String) severityBox.getSelectedItem());
            incidentArea.setText(controller.getIncidentReport());
        });

        scanButton.addActionListener((ActionEvent e) -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                String scanResult = FileScanner.scanFile(file);
                incidentArea.setText(scanResult);
            }
        });

        setVisible(true);
    }
}
