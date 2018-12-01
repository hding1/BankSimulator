package GUI.windows;

import java.awt.BorderLayout;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

import GUI.monitor.PocketMonitor;
import GUI.monitor.SelectMonitor;
import User.Account;
import User.Customer;
import User.Pocket_account;

//import GUI.monitor.SelectButtonMonitor;



public class PocketWindow extends JFrame {
	private Customer c;
	private Pocket_account a;
	public PocketWindow(Customer c, Pocket_account a) {
		super();
		this.c = c;
		this.a = a;
	}
	public void launchSelectWindow(){
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
		this.setTitle("Pocket Menu");
		this.setSize(300,180);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		PocketMonitor sbm = new PocketMonitor(this);
		JButton Button1 = new JButton("Account Info");
		Button1.setActionCommand("1");
		Button1.setAlignmentX(Component.CENTER_ALIGNMENT);
		Button1.setHorizontalAlignment(SwingConstants.CENTER);
		Button1.setMinimumSize(new Dimension(100,20));
		Button1.setMaximumSize(new Dimension(150,30));
		Button1.addActionListener(sbm);
		this.getContentPane().add(Button1);
		JButton Button2 = new JButton("Top-Up");
		Button2.setActionCommand("2");
		Button2.setAlignmentX(Component.CENTER_ALIGNMENT);
		Button2.setAlignmentY(Component.CENTER_ALIGNMENT);
		Button2.setMinimumSize(new Dimension(100,20));
		Button2.setMaximumSize(new Dimension(150,30));
		Button2.addActionListener(sbm);
		this.getContentPane().add(Button2);
		JButton Button3 = new JButton("Purchase");
		Button3.setAlignmentX(Component.CENTER_ALIGNMENT);
		Button3.setAlignmentY(Component.CENTER_ALIGNMENT);
		Button3.setMinimumSize(new Dimension(100,20));
		Button3.setMaximumSize(new Dimension(150,30));
		Button3.setActionCommand("3");
		Button3.addActionListener(sbm);
		this.getContentPane().add(Button3);
		JButton Button4 = new JButton("Collect");
		Button4.setAlignmentX(Component.CENTER_ALIGNMENT);
		Button4.setAlignmentY(Component.CENTER_ALIGNMENT);
		Button4.setMinimumSize(new Dimension(100,20));
		Button4.setMaximumSize(new Dimension(150,30));
		Button4.setActionCommand("4");
		Button4.addActionListener(sbm);
		this.getContentPane().add(Button4);
		JButton Button5 = new JButton("Pay-Friend");
		Button5.setAlignmentX(Component.CENTER_ALIGNMENT);
		Button5.setAlignmentY(Component.CENTER_ALIGNMENT);
		Button5.setMinimumSize(new Dimension(100,20));
		Button5.setMaximumSize(new Dimension(150,30));
		Button5.setActionCommand("5");
		Button5.addActionListener(sbm);
		this.getContentPane().add(Button5);
		JButton Button6 = new JButton("Exit");
		Button6.setAlignmentX(Component.CENTER_ALIGNMENT);
		Button6.setAlignmentY(Component.CENTER_ALIGNMENT);
		Button6.setMinimumSize(new Dimension(100,20));
		Button6.setMaximumSize(new Dimension(150,30));
		Button6.setActionCommand("6");
		Button6.addActionListener(sbm);
		this.getContentPane().add(Button6);
		
		this.setVisible(true);
	}
	public Customer getCustomer() {
		return this.c;
	}
	public Pocket_account getAccount() {
		return this.a;
	}
	
}
