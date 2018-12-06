package GUI.BankTellerWindow;


import java.awt.Component;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import GUI.BankTellerMonitor.*;

public class enterCheckTransactionWindow extends JFrame{
	
private JTextField check_customerName;
	
	public String getCheckCustomerName() {
		return check_customerName.getText();
	}
	
	public void launchEnterCheckTransactionWindow() {
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
		//this.setLayout(new FlowLayout());
		this.setTitle("Enter Check Transaction");
		this.setSize(370,100);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		JLabel checkLabel = new JLabel("Enter Customer Name:");
		checkLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		checkLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		this.check_customerName = new JTextField();
		this.check_customerName.setMaximumSize(new Dimension(300, 30));
		this.check_customerName.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.check_customerName.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		this.add(checkLabel);
		this.getContentPane().add(this.check_customerName);
		
		JButton enter = new JButton("Enter");
		enter.setAlignmentX(Component.CENTER_ALIGNMENT);
		enter.setAlignmentY(Component.CENTER_ALIGNMENT);
		createCheckMonitor ccm = new createCheckMonitor(this);
		enter.addActionListener(ccm);
		this.add(enter);
		
		this.setVisible(true);
	}

}
