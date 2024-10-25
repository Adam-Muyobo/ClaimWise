package application.screens;

import api.area.Area;
import api.area.AreaService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAreaScreen extends JFrame {
    private final JTextField areaNameField;

    public AddAreaScreen() {
        setTitle("Add New Area");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(8, 2));

        // input fields
        add(new JLabel("Area Name:"));
        areaNameField = new JTextField();
        add(areaNameField);

        // add button
        JButton addButton = new JButton("Add Area");

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Area newArea = new Area(
                    areaNameField.getText()
                );

                AreaService service = new AreaService();
                if (service.insertArea(newArea)) {
                    JOptionPane.showMessageDialog(null, "Area Added!");
                } else {
                    JOptionPane.showMessageDialog(null, "Error Adding Area.");
                }
                dispose(); // Close the window after adding
            }
        });
        add(addButton);

        setVisible(true);
    }
    
}
