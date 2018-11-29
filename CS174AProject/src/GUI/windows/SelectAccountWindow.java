package GUI.windows;

import java.awt.BorderLayout;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import GUI.monitor.SelectMonitor;
import User.Customer;

//import GUI.monitor.SelectButtonMonitor;



public class SelectAccountWindow extends JFrame {
	private Customer c;
	private JList<String> AccountList;
	public SelectAccountWindow(Customer a) {
		super();
		this.c = a;
	}
	public void launchSelectwindow(){
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
		this.setTitle("Welcome "+c.getName());
		this.setSize(240,260);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		//Labels
		JLabel userLabel = new JLabel("Select an Account Below");
		JLabel space = new JLabel("");
		JLabel accountLabel = new JLabel("Account ID                      Balance");
		userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		space.setAlignmentX(Component.CENTER_ALIGNMENT);
		accountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		this.add(userLabel);
		this.add(space);
		this.add(accountLabel);
		
		//List
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for(int i = 0;i<c.getList().size();i++) {
			listModel.addElement(c.getList().get(i));
		}
		AccountList = new JList<>(listModel);
		AccountList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(new JScrollPane(AccountList));
		
		//Button
		JButton Button1 = new JButton("Select");
		Button1.setActionCommand("1");
		Button1.setAlignmentX(Component.CENTER_ALIGNMENT);
		Button1.setHorizontalAlignment(SwingConstants.CENTER);
		Button1.setMinimumSize(new Dimension(100, 20));
		Button1.setMaximumSize(new Dimension(150, 30));
		add(Button1);
		this.setVisible(true);
	}
	
}
