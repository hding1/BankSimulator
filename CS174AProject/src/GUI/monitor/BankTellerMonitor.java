package GUI.monitor;

import java.awt.event.ActionListener;

import GUI.windows.BankTellerWindow;
import GUI.windows.SelectWindow;
import GUI.windows.enterCheckTransactionWindow;

import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

import java.awt.Component;
import java.awt.Dimension;

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
	
	public BankTellerMonitor(BankTellerWindow bankTellerWindow) {
		this.bankTellerWindow = bankTellerWindow;
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		int command = Integer.parseInt(e.getActionCommand());
		switch (command) {
		case 1:
			enterCheckTransactionWindow ectw =new enterCheckTransactionWindow();
			ectw.launchEnterCheckTransactionWindow();
			
			break;
		case 2:
			
			break;
		case 3:
			
			break;
		case 4:
			
			break;
        case 5:
        	
			break;
        case 6:

        	break;
        case 7:
        	
			break;
        case 8:
        	
			break;
        case 9:
        	
			break;
        case 10:
        	bankTellerWindow.setVisible(false);
        	System.exit(0);
        	break;
		}
	}
}
