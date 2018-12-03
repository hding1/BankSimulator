package GUI.windows;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

import GUI.BankTellerMonitor.BankTellerMonitor;

public class BankTellerWindow extends JFrame{
    
    public void launchBankTellerWindow(){
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
        this.setTitle("Menu");
        this.setSize(350,400);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        BankTellerMonitor btm = new BankTellerMonitor(this);
        JButton Button1 = new JButton("Enter Check Transaction");
        Button1.setActionCommand("1");
        Button1.setAlignmentX(Component.CENTER_ALIGNMENT);
        Button1.setHorizontalAlignment(SwingConstants.CENTER);
        Button1.setMinimumSize(new Dimension(100,20));
        Button1.setMaximumSize(new Dimension(350,40));
        Button1.addActionListener(btm);
        this.getContentPane().add(Button1);
        JButton Button2 = new JButton("Generate Monthly Statement");
        Button2.setActionCommand("2");
        Button2.setAlignmentX(Component.CENTER_ALIGNMENT);
        Button2.setAlignmentY(Component.CENTER_ALIGNMENT);
        Button2.setMinimumSize(new Dimension(100,20));
        Button2.setMaximumSize(new Dimension(350,40));
        Button2.addActionListener(btm);
        this.getContentPane().add(Button2);
        JButton Button3 = new JButton("List Closed Accounts");
        Button3.setAlignmentX(Component.CENTER_ALIGNMENT);
        Button3.setAlignmentY(Component.CENTER_ALIGNMENT);
        Button3.setMinimumSize(new Dimension(100,20));
        Button3.setMaximumSize(new Dimension(350,40));
        Button3.setActionCommand("3");
        Button3.addActionListener(btm);
        this.getContentPane().add(Button3);
        JButton Button4 = new JButton("Generate Government Drug and Tax Evasion Report");
        Button4.setAlignmentX(Component.CENTER_ALIGNMENT);
        Button4.setAlignmentY(Component.CENTER_ALIGNMENT);
        Button4.setMinimumSize(new Dimension(100,20));
        Button4.setMaximumSize(new Dimension(350,40));
        Button4.setActionCommand("4");
        Button4.addActionListener(btm);
        this.getContentPane().add(Button4);
        JButton Button5 = new JButton("Customer Report");
        Button5.setAlignmentX(Component.CENTER_ALIGNMENT);
        Button5.setAlignmentY(Component.CENTER_ALIGNMENT);
        Button5.setMinimumSize(new Dimension(100,20));
        Button5.setMaximumSize(new Dimension(350,40));
        Button5.setActionCommand("5");
        Button5.addActionListener(btm);
        this.getContentPane().add(Button5);
        JButton Button6 = new JButton("Add Interest");
        Button6.setAlignmentX(Component.CENTER_ALIGNMENT);
        Button6.setAlignmentY(Component.CENTER_ALIGNMENT);
        Button6.setMinimumSize(new Dimension(100,20));
        Button6.setMaximumSize(new Dimension(350,40));
        Button6.setActionCommand("6");
        Button6.addActionListener(btm);
        this.getContentPane().add(Button6);
        JButton Button7 = new JButton("Create Account");
        Button7.setAlignmentX(Component.CENTER_ALIGNMENT);
        Button7.setAlignmentY(Component.CENTER_ALIGNMENT);
        Button7.setMinimumSize(new Dimension(100,20));
        Button7.setMaximumSize(new Dimension(350,40));
        Button7.setActionCommand("7");
        Button7.addActionListener(btm);
        this.getContentPane().add(Button7);
        JButton Button8 = new JButton("Delete Closed Accounts and Customers");
        Button8.setAlignmentX(Component.CENTER_ALIGNMENT);
        Button8.setAlignmentY(Component.CENTER_ALIGNMENT);
        Button8.setMinimumSize(new Dimension(100,20));
        Button8.setMaximumSize(new Dimension(350,40));
        Button8.setActionCommand("8");
        Button8.addActionListener(btm);
        this.getContentPane().add(Button8);
        JButton Button9 = new JButton("Delete Transactions");
        Button9.setAlignmentX(Component.CENTER_ALIGNMENT);
        Button9.setAlignmentY(Component.CENTER_ALIGNMENT);
        Button9.setMinimumSize(new Dimension(100,20));
        Button9.setMaximumSize(new Dimension(350,40));
        Button9.setActionCommand("9");
        Button9.addActionListener(btm);
        this.getContentPane().add(Button9);
        JButton Button10 = new JButton("Exit");
        Button10.setAlignmentX(Component.CENTER_ALIGNMENT);
        Button10.setAlignmentY(Component.CENTER_ALIGNMENT);
        Button10.setMinimumSize(new Dimension(100,20));
        Button10.setMaximumSize(new Dimension(350,40));
        Button10.setActionCommand("10");
        Button10.addActionListener(btm);
        this.getContentPane().add(Button10);
        
        
        this.setVisible(true);
    }
}

