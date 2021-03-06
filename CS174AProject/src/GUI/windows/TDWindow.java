package GUI.windows;

import java.awt.Dimension;

import java.awt.FlowLayout;


import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import GUI.monitor.LoginButtonMonitor;
import GUI.monitor.TDMonitor;
import User.Account;
import User.Customer;
import User.Pocket_account;

public class TDWindow extends JFrame {
	private JTextField amount;
	private JPasswordField password;
	public Customer c;
	public Account a;
	public Pocket_account p;
	public int type;
	public TDWindow(Customer c, Account a, int i) {
		super();
		this.c = c;
		this.a = a;
		this.type = i;
	}
	

	public String getPIN() {
		return this.password.getText();
	}
	public void launchWindow(){
		this.setLayout(new FlowLayout());
		String title = "";
		switch(type) {
			case 1: title = "Deposit";
				break;
			case 2: title = "Withdraw";
				break;
			case 3: 
				title = "Top-Up";
				break;
			case 4: 
				title = "Purchase";
				break;
			case 5: 
				title = "Collect";
				break;
		}
		this.setTitle(title);
		this.setSize(240,110);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		JLabel userLabel = new JLabel("Amount:  ");
		JLabel pwdLabel = new JLabel("Password:");
		this.add(userLabel);
		Dimension dim = new Dimension(160, 20);
		this.amount = new JFormattedTextField();
		amount.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				Runnable format = new Runnable() {
					@Override
					public void run() {
						String text = amount.getText();
						if (!text.matches("\\d*(\\.\\d{0,2})?")) {
							amount.setText(text.substring(0, text.length() - 1));
						}
					}
				};
				SwingUtilities.invokeLater(format);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {

			}

			@Override
			public void changedUpdate(DocumentEvent e) {

			}
		});
		this.amount.setPreferredSize(dim);;
		this.add(this.amount);
		this.add(pwdLabel);
		this.password = new JPasswordField();
		this.password.setPreferredSize(dim);
		password.addKeyListener(new java.awt.event.KeyAdapter() {
		    public void keyTyped(java.awt.event.KeyEvent e) { 
		        if (password.getText().length() >= 4 ) 
		            e.consume(); 
		    }  
		});
		this.add(this.password);
		
		JButton loginButton = new JButton(title);
		TDMonitor lbm = new TDMonitor(this);
		loginButton.setActionCommand("1");
		loginButton.addActionListener(lbm);
		this.add(loginButton);
		
		JButton SignUP = new JButton("Back");
		SignUP.setActionCommand("2");
		SignUP.addActionListener(lbm);
		this.add(SignUP);
		
		
		
		this.setVisible(true);
	}
	public float getAmount() {
		float myAmount = 0;
		try {
			myAmount = Float.parseFloat(this.amount.getText());
		} catch (NumberFormatException e) {
			System.out.print("Incorrect Format!");
		}
		return myAmount;
	}
	
}


