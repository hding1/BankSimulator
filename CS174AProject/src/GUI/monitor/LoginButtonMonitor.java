package GUI.monitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JOptionPane;


import GUI.windows.loginWindow;
import GUI.windows.SelectWindow;

public class LoginButtonMonitor implements ActionListener {
	private loginWindow loginWindow;

	/**

	 * @param loginWindow
	 */
	public LoginButtonMonitor(loginWindow loginWindow) {
		this.loginWindow = loginWindow;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		this.loginWindow.setVisible(false);
		SelectWindow selectwindow=new SelectWindow();
		selectwindow.launchSelectwindow();
		// TODO prompt next window
		
//		if(validate()){
//
//		}
//		else{
//			this.loginWindow.setVisible(false);
//			loginWindow lw1 = new loginWindow();
//			lw1.launchLoginWindow();
//		}
	}
	public int	validate(){
		String userid = this.loginWindow.getUserid().getText();
		String userpassword= new String(this.loginWindow.getPassword().getPassword());
//		String username=User.username
//		String password=User.password

//possible validate
//		if(!userid.equals(username)){	 
//						JOptionPane.showMessageDialog(this.loginWindow, 
//				    			  "wrong user name", "Message",
//									JOptionPane.ERROR_MESSAGE);
//			    	  return 0;
//			      }
//			      if(!userpassword.equals(password)){
//			    	  JOptionPane.showMessageDialog(this.loginWindow, 
//			    			  "wrong password", "Message",
//								JOptionPane.ERROR_MESSAGE);
//			    	  return 0;
//			      }    		    
//				return 1;
		return 1;
		}


}
