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

public class enterCheckTransactionWindow extends JFrame{
	
private JTextField check_transaction;
	
	public void launchEnterCheckTransactionWindow() {
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
		//this.setLayout(new FlowLayout());
		this.setTitle("Enter Check Transaction");
		this.setSize(370,120);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		JLabel checkLabel = new JLabel("Check Number(10 Digit Number):         ");
		//checkLabel.setMaximumSize(new Dimension(300,10));
		checkLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		checkLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		Dimension dim = new Dimension(300, 50);
		this.check_transaction = new JTextField();
		this.check_transaction.setMaximumSize(dim);
		this.check_transaction.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.check_transaction.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		this.add(checkLabel);
		this.getContentPane().add(this.check_transaction);

		
		this.setVisible(true);
	}

}
