package GUI.windows;

import java.awt.Dimension;

import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import GUI.monitor.LoginButtonMonitor;

public class loginWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField userid;
	private JPasswordField password;
	
	public JTextField getUserid() {
		return userid;
	}
	public JPasswordField getPassword() {
		return password;
	}
	public void launchLoginWindow(){
		this.setLayout(new FlowLayout());
		this.setTitle("DebtsRus State Bank");
		this.setSize(240,110);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		JLabel userLabel = new JLabel("TIN:         ");
		JLabel pwdLabel = new JLabel("Password:");
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
		
		JButton loginButton = new JButton("Login");
		LoginButtonMonitor lbm = new LoginButtonMonitor(this);
		loginButton.setActionCommand("1");
		loginButton.addActionListener(lbm);
		this.add(loginButton);
		
		JButton SignUP = new JButton("Sign up");
		SignUP.setActionCommand("2");
		SignUP.addActionListener(lbm);
		this.add(SignUP);
		
		
		
		this.setVisible(true);
	}
	public static void main(String[] args) {
		loginWindow lw = new loginWindow();
		lw.launchLoginWindow();
	}
	
	
}


