package GUI.monitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;


import GUI.windows.loginWindow;
import User.Customer;
import GUI.windows.SelectWindow;
import GUI.windows.SignUPWindow;
import GUI.windows.TDWindow;
import GUI.windows.BankTellerWindow;
import GUI.windows.SelectAccountWindow;

import java.sql.*;
import java.text.SimpleDateFormat;

public class TDMonitor implements ActionListener {
	private TDWindow td;

	/**

	 * @param loginWindow
	 */
	
	static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	static final String DB_URL = "jdbc:oracle:thin:@cloud-34-133.eci.ucsb.edu:1521:XE";

	static final String USERNAME = "fliang";
	static final String PASSWORD = "123455";
	Connection conn = null;
	Statement stmt = null;
	boolean flag;
	

	
	public TDMonitor(TDWindow tdWindow) {
		this.td = tdWindow;
		flag = false;
	}
	


	//@Override
	public void actionPerformed(ActionEvent e) {
		int command = Integer.parseInt(e.getActionCommand());
		switch (command) {
		case 1:
			//acount update
			String typeT = "";
			if(td.type) {
				typeT = "Depost";
			}else {
				typeT = "Withdraw";
			}
			float total = this.td.a.getAmount();
			if(this.td.type) {
				total = this.td.getAmount()+this.td.a.getAmount();
			}else {
				total = this.td.a.getAmount()-this.td.getAmount();
			}
			if(total>0.01) {
			this.td.a.setAmount(total);
			try {
				// STEP 2: Register JDBC driver
				Class.forName(JDBC_DRIVER);

				// STEP 3: Open a connection
				System.out.println("Connecting to a selected database...");
				conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
				System.out.println("Connected database successfully...");

				// STEP 4: Execute a query
				System.out.println("Creating statement...");
				stmt = conn.createStatement();

//			      createTable(conn);
				String sql = "SELECT * FROM Customer C Where C.TaxID = "+this.td.c.getTaxID();
			    ResultSet rs = stmt.executeQuery(sql);
			    while(rs.next()){
			    	String pin = rs.getString("PIN");
			    	if(pin.equals(this.td.getPIN())) {
			    		updateAmount(total);
			    		if(total<=0.01) {
			    			closeAccount();
			    		}
			    		flag = true;
			    	}else {
			    		JOptionPane.showMessageDialog(null, "Incorrect PIN", "Incorrect PIN", JOptionPane.PLAIN_MESSAGE);
			    	}
			    }
			} catch (SQLException se) {
				// Handle errors for JDBC
				se.printStackTrace();
			} catch (Exception ea) {
				// Handle errors for Class.forName
				ea.printStackTrace();
			} finally {
				// finally block used to close resources
				try {
					if (stmt != null)
						conn.close();
				} catch (SQLException se) {
				} // do nothing
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException se) {
					se.printStackTrace();
				} // end finally try
			}
			if(flag) {

				JOptionPane.showMessageDialog(null, typeT+" Succeed\nYour current balance is \n$"+total,"Transaction Successful",  JOptionPane.PLAIN_MESSAGE);
				this.td.setVisible(false);
				SelectWindow window = new SelectWindow(this.td.c,this.td.a);
				window.launchSelectWindow();
			}
			}else if(total>=0){
				JOptionPane.showMessageDialog(this.td, "The account is closed"+typeT.toLowerCase()+" amount:"+this.td.getAmount(),"Transaction Failed",  JOptionPane.PLAIN_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(this.td, "Invalid Input\nThis transaction will make balance to go below 0.\nYour "+typeT.toLowerCase()+" amount:"+this.td.getAmount(),"Transaction Failed",  JOptionPane.PLAIN_MESSAGE);
			}
			break; 
		case 2:
			this.td.setVisible(false);
			SelectWindow window = new SelectWindow(this.td.c,this.td.a);
			window.launchSelectWindow();
			break;
		}
		
		
	}
	public void updateAmount(float total) {
		try {
			PreparedStatement update = conn.prepareStatement(
					"UPDATE Account SET amount = "+total+" WHERE Aid = '"+this.td.a.getAccount()+"'");
			
			System.out.println("Update completed!");
			update.executeUpdate();
			String tid = "";
			String typeT = "";
			if(td.type) {
				tid+=TIDGenerator(1);
				typeT = "depost";
			}else {
				tid+=TIDGenerator(2);
				typeT = "withdraw";
			}
			String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
			update = conn.prepareStatement("INSERT INTO Record_Transaction (Tid, TransactionDate, Aid_1, Aid_2, TypeTransaction, Amount ) VALUES ('" + tid + "','" + timeStamp + "','"+this.td.a.getAccount()+"','" +this.td.a.getAccount()+"','"+ typeT +"'," + this.td.getAmount()+")");
			update.executeUpdate();
			System.out.println("Transaction completed!");
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("funcion completed!");
		}
	}
	public String TIDGenerator(int type) {
		String result = Integer.toString(type);
		Random rand = new Random();
		for (int i = 0; i < 9; i++) {
			int rand_int = rand.nextInt(10);
			result += Integer.toString(rand_int);
		}

		return result;
	}
	public void closeAccount() {
		try {
			PreparedStatement update = conn.prepareStatement(
					"UPDATE Account SET Open = '0' WHERE Aid = '"+this.td.a.getAccount()+"'");
			update.executeUpdate();
			System.out.println("Close completed!");
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("funcion completed!");
		}
	}

}
