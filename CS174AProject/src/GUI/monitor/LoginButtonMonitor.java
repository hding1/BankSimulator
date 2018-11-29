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
import GUI.windows.BankTellerWindow;

import java.sql.*;

public class LoginButtonMonitor implements ActionListener {
	private loginWindow loginWindow;

	/**

	 * @param loginWindow
	 */
	
	static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	static final String DB_URL = "jdbc:oracle:thin:@cloud-34-133.eci.ucsb.edu:1521:XE";

	static final String USERNAME = "fliang";
	static final String PASSWORD = "123455";
	

	
	public LoginButtonMonitor(loginWindow loginWindow) {
		this.loginWindow = loginWindow;
	}
	


	//@Override
	public void actionPerformed(ActionEvent e) {

		
		
		if(new String(this.loginWindow.getUserid().getText()).equals("0000000000") && new String(String.valueOf(this.loginWindow.getPassword().getPassword())).equals("1234")) {
			this.loginWindow.setVisible(false);
			BankTellerWindow bankTellerWindow=new BankTellerWindow();
			bankTellerWindow.launchBankTellerWindow();
			return;
		}
		else {
		
			Connection conn = null;
			Statement stmt = null;
		
			if(new String("").equals(this.loginWindow.getUserid().getText())) {
				JOptionPane.showMessageDialog(null, "TIN Field is blank!", "", JOptionPane.PLAIN_MESSAGE);
				return;
			}
		
			try{
				//STEP 2: Register JDBC driver
				Class.forName(JDBC_DRIVER);
				
				//STEP 3: Open a connection
				System.out.println("Connecting to a selected database...");
				conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
				System.out.println("Connected database successfully...");
				
				//STEP 4: Execute a query
				System.out.println("Creating statement...");
				stmt = conn.createStatement();
				
				//String sql = "SELECT cid, cname, city, discount FROM cs174.Customers";
				String sql = "SELECT Aid FROM Account";
				ResultSet rs = stmt.executeQuery(sql);
				
				while(rs.next()) {
					String cid = rs.getString("Aid");
					if(new String(cid).equals(this.loginWindow.getUserid().getText())) {
						this.loginWindow.setVisible(false);
		    			SelectWindow selectwindow=new SelectWindow();
		    			selectwindow.launchSelectwindow();
		    			return;
					}
				}
				JOptionPane.showMessageDialog(null, "Account " + this.loginWindow.getUserid().getText() + " does not exist!", "", JOptionPane.PLAIN_MESSAGE);
				rs.close();
			}catch(SQLException se){
				//Handle errors for JDBC
				se.printStackTrace();
			}catch(Exception ea){
				//Handle errors for Class.forName
				ea.printStackTrace();
			}finally{
				//finally block used to close resources
				try{
					if(stmt!=null)
						conn.close();
				}catch(SQLException se){
				}// do nothing
				try{
					if(conn!=null)
						conn.close();
				}catch(SQLException se){
					se.printStackTrace();
				}//end finally try
			}
		
			//this.loginWindow.setVisible(false);
			//SelectWindow selectwindow=new SelectWindow();
			//selectwindow.launchSelectwindow();
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
