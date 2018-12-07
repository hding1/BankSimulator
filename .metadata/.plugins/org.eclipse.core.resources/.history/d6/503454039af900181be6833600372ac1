package GUI.BankTellerWindow;

import java.util.*; 
import java.awt.event.*; 
import java.awt.*; 
import javax.swing.*;
import java.sql.*;
import GUI.BankTellerMonitor.*;

public class monthlyStatementWindow extends JFrame{

	
	final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	final String DB_URL = "jdbc:oracle:thin:@cloud-34-133.eci.ucsb.edu:1521:XE";

	final String USERNAME = "fliang";
	final String PASSWORD = "123455";
	
	Connection conn = null;
	Statement stmt, stmt2, stmt3 = null;
	
	public void launchMonthlyStatementWindow(generateMonthlyStatementWindow gmsw) {
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
		this.setTitle("Monthly Statement");
		this.setSize(400,600);
		this.setResizable(false);
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		
		JLabel Primary_Owner = new JLabel("Primary Owner: " + gmsw.getCname().getText());
		this.add(Primary_Owner);
	    
	    
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
		      stmt2 = conn.createStatement();
		      stmt3 = conn.createStatement();

		      String sql_allAccount = "SELECT O.Aid FROM Own_by O INNER JOIN Customer C ON C.Name = '" + gmsw.getCname().getText() + "' AND O.TaxID = C.TaxID";
		      
		      ResultSet rs = stmt.executeQuery(sql_allAccount);
		      while(rs.next()){
		    	  
		    	  System.out.println(rs.getString("Aid"));
		    	  
		    	  String type = "";
		    	  if(rs.getString("Aid").substring(0,1).equals("1")) {
		    		  type = "Student Checking";
		    	  }else if(rs.getString("Aid").substring(0,1).equals("2")) {
		    		  type = "Interest Checking";
		    	  }else if(rs.getString("Aid").substring(0,1).equals("3")) {
		    		  type = "Saving";
		    	  }else if(rs.getString("Aid").substring(0,1).equals("4")) {
		    		  type = "Pocket";
		    	  }
		    	  
		    	  JLabel Account = new JLabel("Account owned: " + rs.getString("Aid") + "   Type: " + type);
		    	  this.add(Account);
		    	  
		    	  
			      final DefaultListModel a3 = new DefaultListModel();
			      a3.addElement("Name:      " + "Address:");
			     
			      
			      String sql_getCustomerNameAndAddress = "SELECT C.Name, C.Address FROM Customer C INNER JOIN Own_by O ON O.Aid = '" + rs.getString("Aid") + "' AND O.TaxID = C.TaxID";
			      String getTransaction = "SELECT T.Tid, T.TypeTransaction, T.Amount FROM Record_Transaction T WHERE T.Aid_2 = '" + rs.getString("Aid") + "'";
			      
			      ResultSet rs2 = stmt2.executeQuery(sql_getCustomerNameAndAddress);
			      while(rs2.next()) {
			    	  a3.addElement(rs2.getString("Name") + "      " + rs2.getString("Address"));
			      }
			      
			      final JList ListCustomer = new JList(a3); 
			      ListCustomer.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			      ListCustomer.setSelectedIndex(0);
			      JScrollPane ListCustomerPane = new JScrollPane(ListCustomer);
			      ListCustomerPane.setPreferredSize(new Dimension(300, 200));
			      ListCustomerPane.setAlignmentX(Component.CENTER_ALIGNMENT);
			      ListCustomerPane.setAlignmentY(Component.CENTER_ALIGNMENT);
			      this.add(ListCustomerPane);
			      
			      JLabel Transaction = new JLabel("All Transactions:     ");
			      this.add(Transaction);
			      
			      
			      

			      final DefaultListModel a4 = new DefaultListModel();
			      ResultSet rs3 = stmt3.executeQuery(getTransaction);
			      while(rs3.next()) {
			    	  a4.addElement("Tid:    " + rs3.getString("Tid") + "   Type:    " + rs3.getString("TypeTransaction") + "     Amount:    " + rs3.getString("Amount"));
			      }
			      final JList ListType = new JList(a4);
			      ListType.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			      ListType.setSelectedIndex(1);
			      JScrollPane ListTypePane = new JScrollPane(ListType);
			      ListTypePane.setPreferredSize(new Dimension(370, 200));
			      ListTypePane.setAlignmentX(Component.CENTER_ALIGNMENT);
			      ListTypePane.setAlignmentY(Component.CENTER_ALIGNMENT);
			      this.add(ListTypePane);

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
	
		
		
		
		JButton back = new JButton("back");
		monthlyStatementMonitor msm = new monthlyStatementMonitor(this);
	    back.setAlignmentX(Component.CENTER_ALIGNMENT);
	    back.setAlignmentY(Component.CENTER_ALIGNMENT);
	    back.addActionListener(msm);
	    this.add(back);		
	    
	    this.setVisible(true);
		
		
	}
	
	
}
