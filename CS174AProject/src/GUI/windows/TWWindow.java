package GUI.windows;

import java.awt.Dimension;

import java.awt.FlowLayout;


import javax.swing.JButton;
import javax.swing.JComboBox;
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
import GUI.monitor.TWMonitor;
import User.Account;
import User.Customer;

public class TWWindow extends JFrame {
	private JTextField amount;
	private JPasswordField password;
	private JComboBox<String> AccountList;
	private JTextField account;
	public Customer c;
	public Account a;
	public int type;
	
	public TWWindow(Customer c, Account a, int type) {
		super();
		this.c = c;
		this.a = a;
		this.type = type;
	}
	

	public String getPIN() {
		return this.password.getText();
	}
	public void launchWindow(){
		this.setLayout(new FlowLayout());
		String title = "";
		Dimension dim = new Dimension(150, 20);
		JLabel FromID = new JLabel("From:       ");
		this.add(FromID);
		JTextField accountID = new JTextField();
		accountID.setPreferredSize(dim);
		accountID.setText(a.getAccount());
		accountID.setEditable(false);
		this.add(accountID);
		JLabel ToID = new JLabel("To            ");
		this.add(ToID);
		switch(type) {
		case 1:
			title = "Transfer";
			String[] accountString = c.getSACID();
			AccountList = new JComboBox<>(accountString);
			AccountList.setPreferredSize(dim);
			this.getContentPane().add(AccountList);
			break;
		case 2:	
			title = "Wire";
			this.account = new JFormattedTextField();
			account.getDocument().addDocumentListener(new DocumentListener() {
				@Override
				public void insertUpdate(DocumentEvent e) {
					Runnable format = new Runnable() {
						@Override
						public void run() {
							String text = account.getText();
							if (!text.matches("\\d*(\\\\d{0,2})?")) {
								account.setText(text.substring(0, text.length() - 1));
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
			this.account.setPreferredSize(dim);
			this.add(account);
			break;
		case 3:
			title = "Pay-Friend";
			this.account = new JFormattedTextField();
			account.getDocument().addDocumentListener(new DocumentListener() {
				@Override
				public void insertUpdate(DocumentEvent e) {
					Runnable format = new Runnable() {
						@Override
						public void run() {
							String text = account.getText();
							if (!text.matches("\\d*(\\\\d{0,2})?")) {
								account.setText(text.substring(0, text.length() - 1));
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
			this.account.setPreferredSize(dim);
			this.add(account);
		}
		this.setTitle(title);
		this.setSize(240,160);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		JLabel userLabel = new JLabel("Amount:  ");
		JLabel pwdLabel = new JLabel("Password:");

		
		
		this.add(userLabel);

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
		TWMonitor lbm = new TWMonitor(this);
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
	public String getAccountID() {
		switch(type) {
		case 1:
			return (String) AccountList.getSelectedItem();
		case 2:
			return account.getText();
		case 3:
			return account.getText();
		}
		return account.getText();
	}
	
}


