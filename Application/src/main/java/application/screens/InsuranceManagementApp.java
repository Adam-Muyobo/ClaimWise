package application.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsuranceManagementApp extends JFrame {

    public InsuranceManagementApp() {
        setTitle("Insurance Management System");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1));

        JButton addPolicyHolderButton = new JButton("Add New Policy Holder");
        JButton viewPolicyHoldersButton = new JButton("View All Policy Holders");
        JButton listPoliciesButton = new JButton("List Policies and Related Details");
        JButton findPoliciesButton = new JButton("Find Policies by Claim Number or Holder");
        JButton claimsReportButton = new JButton("Generate Claims Report");
        JButton subscriptionsReportButton = new JButton("Generate Subscriptions Report");
        JButton profitReportButton = new JButton("Generate Profit Report");
        JButton addProductButton = new JButton("Add New Product");

        addPolicyHolderButton.addActionListener(e -> new AddPolicyHolderScreen());
        viewPolicyHoldersButton.addActionListener(e -> new ViewPolicyHoldersScreen());
        // listPoliciesButton.addActionListener(e -> new ListPoliciesScreen());
        // findPoliciesButton.addActionListener(e -> new FindPoliciesScreen());
        // claimsReportButton.addActionListener(e -> new ClaimsReportScreen());
        // subscriptionsReportButton.addActionListener(e -> new SubscriptionsReportScreen());
       // profitReportButton.addActionListener(e -> new ProfitReportScreen());
       addProductButton.addActionListener(e -> new AddProductScreen());

        add(addPolicyHolderButton);
        add(viewPolicyHoldersButton);
        add(listPoliciesButton);
        add(findPoliciesButton);
        add(claimsReportButton);
        add(subscriptionsReportButton);
        add(profitReportButton);
        add(addProductButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InsuranceManagementApp::new);
    }
}
