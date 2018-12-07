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

public class createReportWindow extends JFrame{
	private JTextField cname;

	public JTextField getCname() {
		return this.cname;
	}

	public void launchCreateReportWindow() {
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
		this.setLayout(new FlowLayout());
		this.setTitle("");
		this.setSize(370, 120);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		JLabel CustomerName = new JLabel("Enter Customer TaxID:");
		this.add(CustomerName);
		this.cname = new JTextField();
		Dimension dim = new Dimension(300, 30);
		this.cname.setPreferredSize(dim);
		this.add(cname);
		
		JButton createReport = new JButton("Create Report");
		createReportMonitor crm = new createReportMonitor(this);
		createReport.addActionListener(crm);
		this.add(createReport);
		
		this.setVisible(true);
	}
	
}
