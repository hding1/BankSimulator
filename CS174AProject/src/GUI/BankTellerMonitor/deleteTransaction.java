package GUI.BankTellerMonitor;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import java.sql.*;

import javax.swing.JOptionPane;

public class deleteTransaction {

	final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	final String DB_URL = "jdbc:oracle:thin:@cloud-34-133.eci.ucsb.edu:1521:XE";

	final String USERNAME = "fliang";
	final String PASSWORD = "123455";

	Connection conn = null;
	Statement stmt = null;
	
	public void actionPerformed() {
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

			try {
				
				String sql = "DELETE FROM Record_Transaction;";
				PreparedStatement deleteTransactions = conn.prepareStatement(sql);
				deleteTransactions.executeUpdate();
				System.out.println("Delete Transaction Completed");

			} catch (Exception ee) {
				System.out.println(ee);
			} finally {
				System.out.println("Function Completed");
				JOptionPane.showMessageDialog(null, "Delete Transaction Completed!", "", JOptionPane.PLAIN_MESSAGE);				
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
	}
}
