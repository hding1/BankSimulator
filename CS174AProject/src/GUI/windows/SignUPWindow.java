package GUI.windows;

import java.awt.BorderLayout;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;

import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import GUI.monitor.SelectMonitor;
import GUI.monitor.SignUPButtonMonitor;

public class SignUPWindow extends JFrame{
	private JTextField userid;
	private JPasswordField password;	
	
	public void launchSignUPWindow(){
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
		this.setTitle("Sign Up");
		this.setSize(300,180);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		SignUPButtonMonitor subm = new SignUPButtonMonitor(this);
		
		JLabel userLabel = new JLabel("Your ID:         ");
		JLabel pwdLabel = new JLabel("Your Password:         ");
		this.add(userLabel);
		Dimension dim = new Dimension(160, 20);
		this.userid = new JTextField();
		this.userid.setPreferredSize(dim);
		this.add(this.userid);
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
		
		JButton Button1 = new JButton("Register");
		Button1.setActionCommand("1");
		Button1.setAlignmentX(Component.CENTER_ALIGNMENT);
		Button1.setAlignmentY(Component.CENTER_ALIGNMENT);
		Button1.setHorizontalAlignment(SwingConstants.CENTER);
		Button1.setMinimumSize(new Dimension(100,20));
		Button1.setMaximumSize(new Dimension(150,30));
		Button1.addActionListener(new MyButton1Listener(this));
		this.getContentPane().add(Button1);
		
		
		this.setVisible(true);
	}
	
	public class MyButton1Listener implements ActionListener{
		SignUPWindow supw;
		
		MyButton1Listener(SignUPWindow supw){
			this.supw = supw;
		}
		
        public void actionPerformed(ActionEvent a){
        	//TO DO:
        	//INSERT INFORMATION INTO SQL TABLE.
        	
        	
        	this.supw.setVisible(false);
			loginWindow lw = new loginWindow();
			lw.launchLoginWindow();
        }
	}
	
}
