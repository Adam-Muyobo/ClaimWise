package application.testing;

import api.area.Area;
import api.area.AreaService;
import api.policy.Policy;
import api.policy.PolicyService;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.Date;

public class MainFrame extends JFrame {
    private final AreaService areaService;
    private final PolicyService policyService;

    private JTextArea areaTextArea;
    private JTextArea policyTextArea;

    public MainFrame() {
        areaService = new AreaService();
        policyService = new PolicyService();

        setTitle("Insurance Database GUI");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Area Panel
        JPanel areaPanel = createAreaPanel();
        tabbedPane.addTab("Areas", areaPanel);

        // Policy Panel
        JPanel policyPanel = createPolicyPanel();
        tabbedPane.addTab("Policies", policyPanel);

        add(tabbedPane);
    }

    private JPanel createAreaPanel() {
        JPanel areaPanel = new JPanel(new BorderLayout());

        // Text area for displaying Area data
        areaTextArea = new JTextArea();
        areaTextArea.setEditable(false);
        JScrollPane areaScrollPane = new JScrollPane(areaTextArea);
        areaPanel.add(areaScrollPane, BorderLayout.CENTER);

        // Input and control panel for Area
        JPanel controlPanel = new JPanel();

        JLabel areaNameLabel = new JLabel("Area Name:");
        JTextField areaNameField = new JTextField(10);

        JButton searchButton = new JButton("Search Area");
        JButton insertButton = new JButton("Insert Area");
        JButton updateButton = new JButton("Update Area");
        JButton deleteButton = new JButton("Delete Area");

        controlPanel.add(areaNameLabel);
        controlPanel.add(areaNameField);
        controlPanel.add(searchButton);
        controlPanel.add(insertButton);
        controlPanel.add(updateButton);
        controlPanel.add(deleteButton);

        areaPanel.add(controlPanel, BorderLayout.NORTH);

        // Search button action
        searchButton.addActionListener(e -> {
            String areaName = areaNameField.getText();
            Area area = areaService.getAreaByName(areaName);
            if (area != null) {
                areaTextArea.setText(area.toString());
            } else {
                areaTextArea.setText("Area not found.");
            }
        });

        // Insert button action
        insertButton.addActionListener(e -> {
            String areaName = areaNameField.getText();
            boolean success = areaService.insertArea(new Area(areaName));
            areaTextArea.setText(success ? "Area inserted successfully." : "Error inserting area.");
        });

        // Update button action
        updateButton.addActionListener(e -> {
            String oldAreaName = JOptionPane.showInputDialog("Enter old area name:");
            String newAreaName = areaNameField.getText();
            boolean success = areaService.updateArea(oldAreaName, new Area(newAreaName));
            areaTextArea.setText(success ? "Area updated successfully." : "Error updating area.");
        });

        // Delete button action
        deleteButton.addActionListener(e -> {
            String areaName = areaNameField.getText();
            boolean success = areaService.deleteArea(areaName);
            areaTextArea.setText(success ? "Area deleted successfully." : "Error deleting area.");
        });

        return areaPanel;
    }

    private JPanel createPolicyPanel() {
        JPanel policyPanel = new JPanel(new BorderLayout());

        // Text area for displaying Policy data
        policyTextArea = new JTextArea();
        policyTextArea.setEditable(false);
        JScrollPane policyScrollPane = new JScrollPane(policyTextArea);
        policyPanel.add(policyScrollPane, BorderLayout.CENTER);

        // Input and control panel for Policy
        JPanel controlPanel = new JPanel();

        JLabel policyNumberLabel = new JLabel("Policy Number:");
        JTextField policyNumberField = new JTextField(5);

        JLabel policyNameLabel = new JLabel("Policy Name:");
        JTextField policyNameField = new JTextField(10);

        JButton searchButton = new JButton("Search Policy");
        JButton insertButton = new JButton("Insert Policy");
        JButton updateButton = new JButton("Update Policy");
        JButton deleteButton = new JButton("Delete Policy");

        controlPanel.add(policyNumberLabel);
        controlPanel.add(policyNumberField);
        controlPanel.add(policyNameLabel);
        controlPanel.add(policyNameField);
        controlPanel.add(searchButton);
        controlPanel.add(insertButton);
        controlPanel.add(updateButton);
        controlPanel.add(deleteButton);

        policyPanel.add(controlPanel, BorderLayout.NORTH);

        // Search button action
        searchButton.addActionListener(e -> {
            try {
                int policyNumber = Integer.parseInt(policyNumberField.getText());
                Policy policy = policyService.getPolicyById(policyNumber);
                if (policy != null) {
                    policyTextArea.setText(policy.toString());
                } else {
                    policyTextArea.setText("Policy not found.");
                }
            } catch (NumberFormatException ex) {
                policyTextArea.setText("Invalid Policy Number.");
            }
        });

        // Insert button action
        insertButton.addActionListener(e -> {
            String policyName = policyNameField.getText();
            Policy policy = new Policy(0, "PH001", policyName, "Life", new BigDecimal(5000), new Date());
            boolean success = policyService.insertPolicy(policy);
            policyTextArea.setText(success ? "Policy inserted successfully." : "Error inserting policy.");
        });

        // Update button action
        updateButton.addActionListener(e -> {
            try {
                int policyNumber = Integer.parseInt(policyNumberField.getText());
                String policyName = policyNameField.getText();
                Policy newPolicy = new Policy(policyNumber, "PH123", policyName, "Life", new BigDecimal(700), new Date());
                boolean success = policyService.updatePolicy(policyNumber, newPolicy);
                policyTextArea.setText(success ? "Policy updated successfully." : "Error updating policy.");
            } catch (NumberFormatException ex) {
                policyTextArea.setText("Invalid Policy Number.");
            }
        });

        // Delete button action
        deleteButton.addActionListener(e -> {
            try {
                int policyNumber = Integer.parseInt(policyNumberField.getText());
                boolean success = policyService.deletePolicy(policyNumber);
                policyTextArea.setText(success ? "Policy deleted successfully." : "Error deleting policy.");
            } catch (NumberFormatException ex) {
                policyTextArea.setText("Invalid Policy Number.");
            }
        });

        return policyPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}

