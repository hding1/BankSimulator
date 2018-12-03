package GUI.BankTellerMonitor;

import java.awt.event.ActionListener;


import GUI.windows.BankTellerWindow;
import GUI.BankTellerWindow.*;
import GUI.BankTellerMonitor.*;

import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;

import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

import java.sql.*;


public class BankTellerMonitor extends JFrame implements ActionListener{
	private BankTellerWindow bankTellerWindow;
	private JTextField check_transaction;
	private JTextField cname;
	
	public BankTellerMonitor(BankTellerWindow bankTellerWindow) {
		this.bankTellerWindow = bankTellerWindow;
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		int command = Integer.parseInt(e.getActionCommand());
		String m = e.getActionCommand();
		switch (m) {
		case "1":
			enterCheckTransactionWindow ectw =new enterCheckTransactionWindow();
			ectw.launchEnterCheckTransactionWindow();
			System.out.println("1");
			
			break;
		case "2":
			generateMonthlyStatementWindow gmsw = new generateMonthlyStatementWindow();
			gmsw.launchMonthlyStatementWindow();
			System.out.println("2");

			break;
		case "3":
			listClosedAccountWindow lcaw = new listClosedAccountWindow();
			lcaw.launchListClosedAccountWindow();
			System.out.println("List Closed Account");

			break;
		case "4":
			System.out.println("4");

			break;
        case "5":
			System.out.println("5");
			createReportWindow crw = new createReportWindow();
			crw.launchCreateReportWindow();
			
			//JButton createReport = new JButton("Create Report");
			//createReportMonitor crm = createReportMonitor(this);
			//this.add(createReport);
			

			
			break;
        case "6":
			System.out.println("6");

        	break;
        case "7":
        	createAccountWindow kk = new createAccountWindow();
        	kk.launchCreateAccountWindow();
			System.out.println("7");

        	
			break;
        case "8":
        	deleteClosedAccountsAndCustomers dcaac = new deleteClosedAccountsAndCustomers();
        	dcaac.actionPerformed();
			break;
        case "9":
        	deleteTransaction dt = new deleteTransaction();
        	dt.actionPerformed();
        	System.out.println("9999999");
        	
			break;
        case "10":
        	bankTellerWindow.setVisible(false);
			System.out.println("10");

        	System.exit(0);
        	break;
		}
	}
}