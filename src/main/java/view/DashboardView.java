package view;

import controller.IncidentController;
import model.Incident;
import utils.FileScanner;
import utils.IncidentDetector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class DashboardView extends JFrame {
    private final IncidentController controller = new IncidentController();
    private final JTextArea incidentArea = new JTextArea(10, 30);
    private final JComboBox<String> filterTypeBox = new JComboBox<>(new String[]{"All", "DataBreach", "PhishingAttempt", "MalwareInfection", "UnauthorizedAccess", "DDoSAttack"});
    private final JComboBox<String> filterSeverityBox = new JComboBox<>(new String[]{"All", "Low", "Medium", "High"});

    public DashboardView() {
        setTitle("Dashboard");
        setSize(1000, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel backgroundPanel = new JPanel() {
            private Image backgroundImage = new ImageIcon("src/main/resources/VIRUS DETECTOR.png").getImage();
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(false);

        JPanel filterPanel = new JPanel();
        filterPanel.setOpaque(false);
        filterPanel.add(new JLabel("Filter Type:"));
        filterPanel.add(filterTypeBox);
        filterPanel.add(new JLabel("Filter Severity:"));
        filterPanel.add(filterSeverityBox);
        JButton refreshButton = new JButton("Refresh Report");
        JButton exportButton = new JButton("Export Report");
        filterPanel.add(refreshButton);
        filterPanel.add(exportButton);

        JPanel inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        JTextField descField = new JTextField(20);
        JComboBox<String> severityBox = new JComboBox<>(new String[]{"Low", "Medium", "High"});
        JComboBox<String> typeBox = new JComboBox<>(new String[]{"DataBreach", "PhishingAttempt", "MalwareInfection", "UnauthorizedAccess", "DDoSAttack"});
        JButton logButton = new JButton("Log Incident");
        JButton scanButton = new JButton("Scan File");

        inputPanel.add(new JLabel("Description:"));
        inputPanel.add(descField);
        inputPanel.add(new JLabel("Severity:"));
        inputPanel.add(severityBox);
        inputPanel.add(new JLabel("Type:"));
        inputPanel.add(typeBox);
        inputPanel.add(logButton);
        inputPanel.add(scanButton);

        incidentArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(incidentArea);

        mainPanel.add(filterPanel, BorderLayout.NORTH);
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        backgroundPanel.add(mainPanel, BorderLayout.CENTER);

        add(new JScrollPane(backgroundPanel));

        logButton.addActionListener((ActionEvent e) -> {
            controller.logIncident((String) typeBox.getSelectedItem(), descField.getText(), (String) severityBox.getSelectedItem());
            updateIncidentArea();
        });

        scanButton.addActionListener((ActionEvent e) -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    byte[] content = Files.readAllBytes(file.toPath());
                    String text = new String(content);
                    String scanResult = FileScanner.scanFile(file);

                    Incident detected = IncidentDetector.detect(file.getName(), text);
                    if (detected != null) {
                        controller.logIncident(detected.getClass().getSimpleName(), detected.getDescription(), detected.getSeverity());
                        JOptionPane.showMessageDialog(this, "🚨 " + detected.getClass().getSimpleName() + " detected!", "Threat Alert", JOptionPane.WARNING_MESSAGE);
                    }

                    incidentArea.setText(scanResult);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error scanning file: " + ex.getMessage());
                }
            }
        });

        refreshButton.addActionListener((ActionEvent e) -> updateIncidentArea());
        exportButton.addActionListener((ActionEvent e) -> exportReport());

        updateIncidentArea();
        setVisible(true);
    }

    private void updateIncidentArea() {
        String typeFilter = (String) filterTypeBox.getSelectedItem();
        String severityFilter = (String) filterSeverityBox.getSelectedItem();

        List<Incident> filtered = controller.getAllIncidents().stream()
                .filter(inc -> "All".equals(typeFilter) || inc.getClass().getSimpleName().equals(typeFilter))
                .filter(inc -> "All".equals(severityFilter) || inc.getSeverity().equalsIgnoreCase(severityFilter))
                .toList();

        StringBuilder sb = new StringBuilder();
        for (Incident inc : filtered) {
            sb.append(inc.getClass().getSimpleName())
                    .append(" - ").append(inc.getId())
                    .append(" - ").append(inc.getSeverity())
                    .append("\n");
        }

        sb.append("\nTotal: ").append(filtered.size()).append(" incident(s)");
        incidentArea.setText(sb.toString());
    }

    private void exportReport() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Report");
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
                writer.write(incidentArea.getText());
                JOptionPane.showMessageDialog(this, "Report exported successfully.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Failed to export report: " + e.getMessage());
            }
        }
    }
}
