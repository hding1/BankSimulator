package User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public abstract class Account {
	protected float Amount;
	protected String Account_id;
	protected String Pname;
	protected String Branch;
	protected char Status;
	private ArrayList<Transaction> tlist;
	final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	final String DB_URL = "jdbc:oracle:thin:@cloud-34-133.eci.ucsb.edu:1521:XE";

	final String USERNAME = "fliang";
	final String PASSWORD = "123455";
	
	Connection conn = null;
	Statement stmt = null;
	
	Account(){
		this.Amount = 0;
		this.Account_id = "";
		this.Pname = "";
		this.Branch = "";
		this.Status = '1';
	}
	
	Account(String Account_id, String TaxID, float Amount, String Branch, char status){
		this.Amount = Amount;
		this.Account_id = Account_id;
		this.Pname = TaxID;
		this.Branch = Branch;
		this.Status = status;
		tlist = new ArrayList<Transaction>();		
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

			String query = "SELECT * FROM Record_Transaction T WHERE T.Aid_1 = '"+Account_id+"'"+" UNION "+" SELECT * FROM Record_Transaction T WHERE T.Aid_2 = '"+Account_id+"'";

			PreparedStatement accountQuery = conn.prepareStatement(query);
			ResultSet rs = accountQuery.executeQuery();
			while(rs.next()) {
				String tid = rs.getString("Tid");
				String date = rs.getString("TransactionDate");
				String aid1 = rs.getString("Aid_1");
				String aid2 = rs.getString("Aid_2");
				String type = rs.getString("TypeTransaction");
				float num = rs.getFloat("Amount");
	            tlist.add(new Transaction(tid, aid1, aid2,date,type,num));
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
	
	public float getAmount() {
		return this.Amount;
	} 
	public ArrayList<Transaction> getList(){
		return tlist;
	}
	public String getAccount() {
		return this.Account_id;
	}
	
	public String getPname() {
		return this.Pname;
	}
	
	public String getBranch() {
		return this.Branch;
	}
	
	public char getStatus() {
		return this.Status;
	}
	
	public void setAmount(float Amount) {
		this.Amount = Amount;
	}
	
	public void setAccount(String Account_id) {
		this.Account_id = Account_id;
	}
	
	public void setPname(String Pname) {
		this.Pname = Pname;
	}
	
	public void setBranch(String Branch) {
		this.Branch = Branch;
	}
	
	public void setStatus(char Status) {
		this.Status = Status;
	}

}
