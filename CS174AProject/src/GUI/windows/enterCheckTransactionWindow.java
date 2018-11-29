package GUI.windows;

import java.awt.Component;

import java.awt.Dimension;

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
		this.setTitle("Enter Check Transaction");
		this.setSize(300,280);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		JLabel checkLabel = new JLabel("Check Number(10 Digit Number):         ");
		Dimension dim = new Dimension(160, 20);
		this.check_transaction = new JTextField();
		this.check_transaction.setPreferredSize(dim);
		this.add(checkLabel);
		this.add(this.check_transaction);
		
		this.setVisible(true);
	}
}
