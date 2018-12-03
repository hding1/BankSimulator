package GUI.BankTellerMonitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import java.sql.*;

import javax.swing.JOptionPane;

public class deleteClosedAccountsAndCustomers {//implements ActionListener{

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
				
				String sql = "SELECT TaxID, Aid FROM Account a WHERE a.Open = '0'";
				ResultSet rs = stmt.executeQuery(sql);

				
				/*PreparedStatement deleteCloseAccount = conn.prepareStatement("DELETE FROM Account WHERE Open = '0';");
				PreparedStatement deleteCloseCustomer = conn.prepareStatement("D");
				deleteCloseAccount.executeUpdate();*/
				
				while(rs.next()) {
					System.out.println(rs.getString("TaxID"));
					String TaxID = rs.getString("TaxID");
					String sql2 = "DELETE FROM Own_by WHERE TaxID = '" + TaxID + "'";
					String sql3 = "DELETE FROM Customer WHERE TaxID = '" + TaxID + "'";
					String sql4 = "DELETE FROM Account WHERE Open = '0'";
					PreparedStatement deleteCustomerTable = conn.prepareStatement(sql2);
					PreparedStatement deleteOwnbyTable = conn.prepareStatement(sql3);
					PreparedStatement deleteAccountTable = conn.prepareStatement(sql4);
					deleteCustomerTable.executeUpdate();
					System.out.println("SQL COMMAND COMPLETE");
					deleteOwnbyTable.executeUpdate();
					System.out.println("SQL COMMAND COMPLETE");
					deleteAccountTable.executeUpdate();
				System.out.println("Delete Completed");
				}
			} catch (Exception ee) {
				System.out.println(ee);
			} finally {
				System.out.println("Function Completed");
				JOptionPane.showMessageDialog(null, "All Closed Accounts And Customers With No Accounts Are Deleted!", "", JOptionPane.PLAIN_MESSAGE);				
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
