package User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Customer {
	
	private String Name;
	private String TaxID;
	private String Address;
	private String Pin;
	private ArrayList<Account> Accounts;
	final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	final String DB_URL = "jdbc:oracle:thin:@cloud-34-133.eci.ucsb.edu:1521:XE";

	final String USERNAME = "fliang";
	final String PASSWORD = "123455";

	Connection conn = null;
	Statement stmt = null;
	
	public Customer(){
		this.Name = "";
		this.TaxID = "";
		this.Address = "";
	}
	
	public Customer(String Name, String TaxID, String Address, String Pin){
		this.Name = Name;
		this.TaxID = TaxID;
		this.Address = Address;
		this.Pin = Pin;
		Accounts = new ArrayList<Account>();		
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

			String query = "SELECT O.Aid FROM Own_by O WHERE O.TaxID = "+this.TaxID;
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String aid = rs.getString("Aid");
	            Accounts.add(getAccount(aid));
	            System.out.println(aid); 
	            System.out.println("pull accounts complete");
	        }
			rs.close();
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
	public String[] getAccountID() {
		String[] result = new String[Accounts.size()];
		for(int i = 0;i<Accounts.size();i++) {
			result[i] = Accounts.get(i).getAccount();
		}
		return result;
	}
	 
	public String getName() {
		return this.Name;
	}
	
	public String getTaxID() {
		return this.TaxID;
	}
	
	public String getAddress() {
		return this.Address;
	}
	
	public ArrayList<Account> getList(){
		return Accounts;
	}
	
	public void setName(String Name) {
		this.Name = Name;
	}
	
	public void setTaxID(String TaxID) {
		this.TaxID = TaxID;
	}
	
	public void setAddress(String Address) {
		this.Address = Address;
	}
	
	public void setPIN(String Pin) {
		this.Pin = Pin;
	}
	
	private Account getAccount(String aid) {
		try {
			String query = "";
			ResultSet rs;
			if(aid.charAt(0)=='1') {
				query = "SELECT * From Account A, Student_Checking S WHERE A.Aid = "+aid +"AND S.Aid="+aid;
				rs = stmt.executeQuery(query);
				rs.next();
				Account new_account = new Student_check_account(rs.getString("Aid"),rs.getString("TaxID"),rs.getFloat("Amount"),rs.getString("Branch"),rs.getString("Open").charAt(0),rs.getFloat("Interest_rate"));
				return new_account;
			}else if(aid.charAt(0)=='2') {
				query = "SELECT * From Account A, Interest_Checking S WHERE A.Aid = "+aid +"AND S.Aid="+aid;
				rs = stmt.executeQuery(query);
				rs.next();
				Account new_account = new Interest_check_account(rs.getString("Aid"),rs.getString("TaxID"),rs.getFloat("Amount"),rs.getString("Branch"),rs.getString("Open").charAt(0),rs.getFloat("Interest_rate"));
				return new_account;
			}else if(aid.charAt(0)=='3') {
				query = "SELECT * From Account A, Saving S WHERE A.Aid = "+aid +"AND S.Aid="+aid;
				rs = stmt.executeQuery(query);
				rs.next();
				Account new_account = new Saving_account(rs.getString("Aid"),rs.getString("TaxID"),rs.getFloat("Amount"),rs.getString("Branch"),rs.getString("Open").charAt(0),rs.getFloat("Interest_rate"));
				return new_account;
			}else {
				query = "SELECT * From Account A, Pocket S WHERE A.Aid = "+aid +"AND S.Aid="+aid;
				rs = stmt.executeQuery(query);
				rs.next();
				Account new_account = new Pocket_account(rs.getString("Aid"),rs.getString("TaxID"),rs.getFloat("Amount"),rs.getString("Branch"),rs.getString("Open").charAt(0),rs.getString("Link_aid"));
				return new_account;
			}
			
			
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception ea) {
			// Handle errors for Class.forName
			ea.printStackTrace();
		} 
		return null;
	}
	
	
}
