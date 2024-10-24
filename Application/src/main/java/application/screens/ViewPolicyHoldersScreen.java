package application.screens;

import api.policyholder.PolicyHolder;
import api.policyholder.PolicyHolderService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewPolicyHoldersScreen extends JFrame {

    public ViewPolicyHoldersScreen() {
        setTitle("View All Policy Holders");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnNames = {"Policy Holder ID", "First Name", "Last Name", "DOB", "Company Name", "Phone Number", "Address"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);

        PolicyHolderService service = new PolicyHolderService();
        List<PolicyHolder> policyHolders = service.getAllPolicyHolders();

        for (PolicyHolder holder : policyHolders) {
            model.addRow(new Object[]{
                    holder.getPolicyHolderID(),
                    holder.getFirstName(),
                    holder.getLastName(),
                    holder.getDateOfBirth(),
                    holder.getCompanyName(),
                    holder.getPhoneNumber(),
                    holder.getAddress()
            });
        }

        add(new JScrollPane(table), BorderLayout.CENTER);
        setVisible(true);
    }
}

