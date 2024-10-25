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
        JButton addPolicy = new JButton("Add Policy");
        JButton updatePolicy = new JButton("Update Policy");
        JButton deletePolicy = new JButton("Delete Policy");

        JButton profitReportButton = new JButton("Generate Profit Report");
        JButton addProductButton = new JButton("Add New Product");
        JButton addBrokerButton = new JButton("Add New Broker");
        JButton addClaimButton = new JButton("Add New Claim");
        JButton addAreaButton = new JButton("Add New Area");
        JButton deleteProductButton = new JButton("Delete");

        JButton listPoliciesButton = new JButton("List Policies and Related Details");
        JButton claimsReportButton = new JButton("Generate Claims Report");
        JButton subscriptionsReportButton = new JButton("Generate Subscriptions Report");

        addPolicyHolderButton.addActionListener(e -> new AddPolicyHolderScreen());
        viewPolicyHoldersButton.addActionListener(e -> new ViewPolicyHoldersScreen());
        listPoliciesButton.addActionListener(e -> new RetrievePolicyScreen());
        addPolicy.addActionListener(e -> new AddPolicyScreen());
        updatePolicy.addActionListener(e -> new UpdatePolicyScreen());
        deletePolicy.addActionListener(e -> new DeletePolicyScreen());
        // claimsReportButton.addActionListener(e -> new ClaimsReportScreen());
        // subscriptionsReportButton.addActionListener(e -> new SubscriptionsReportScreen());
       // profitReportButton.addActionListener(e -> new ProfitReportScreen());
       addProductButton.addActionListener(e -> new AddProductScreen());
       addAreaButton.addActionListener(e -> new AddAreaScreen());
       addBrokerButton.addActionListener(e -> new AddBrokerScreen());
       addClaimButton.addActionListener(e -> new AddClaimScreen());
       deleteProductButton.addActionListener(e -> new DeleteProductScreen());



        add(addPolicyHolderButton);
        add(viewPolicyHoldersButton);
        add(addPolicy);
        add(listPoliciesButton);
        add(updatePolicy);
        add(deletePolicy);
        add(claimsReportButton);
        add(subscriptionsReportButton);
        add(profitReportButton);
        add(addProductButton);
        add(addBrokerButton);
        add(addAreaButton);
        add(addClaimButton);
        add(deleteProductButton);


        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InsuranceManagementApp::new);
    }
}
