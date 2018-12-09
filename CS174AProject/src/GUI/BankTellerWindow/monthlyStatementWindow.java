package GUI.BankTellerWindow;

import java.util.*; 
import java.awt.event.*; 
import java.awt.*; 
import javax.swing.*;
import java.sql.*;
import GUI.BankTellerMonitor.*;


public class monthlyStatementWindow extends JFrame{

	
	float initial_amount;
	float final_amount;
	
	final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	final String DB_URL = "jdbc:oracle:thin:@cloud-34-133.eci.ucsb.edu:1521:XE";

	final String USERNAME = "fliang";
	final String PASSWORD = "123455";
	
	Connection conn = null;
	Statement stmt, stmt2, stmt3, stmt4, stmt5= null;
	
	public void launchMonthlyStatementWindow(generateMonthlyStatementWindow gmsw) {
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
		this.setTitle("Monthly Statement");
		this.setSize(400,800);
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
		      stmt4 = conn.createStatement();
		      stmt5 = conn.createStatement();
		      
		      

		      String sql_allAccount = "SELECT O.Aid, A.Type FROM Own_by O INNER JOIN Customer C ON C.TaxID = '" + gmsw.getCname().getText() + "' AND O.TaxID = C.TaxID INNER JOIN Account A ON O.Aid = A.Aid";
		      
		      
		      ResultSet rs = stmt.executeQuery(sql_allAccount);
		      final DefaultListModel a4 = new DefaultListModel();
		      //final JList ListType = new JList(a4);


		      while(rs.next()){
		    	  
		    	  
		    	  String sql_getAmount = "SELECT A.Amount FROM Account A WHERE A.Aid = '" + rs.getString("Aid") + "'";
		    	  ResultSet finalAmount = stmt4.executeQuery(sql_getAmount);
		    	  while(finalAmount.next()) final_amount = Float.valueOf(finalAmount.getString("Amount"));
		    	  
		    	  String sql_getTransaction = "SELECT I.Amount FROM initialAmount I WHERE I.Aid = '" + rs.getString("Aid") + "'";
		    	  ResultSet initialAmount = stmt5.executeQuery(sql_getTransaction);
		    	  while(initialAmount.next())  initial_amount = Float.valueOf(initialAmount.getString("Amount"));
		    	  
		    	  JLabel Account = new JLabel("Account owned: " + rs.getString("Aid") + "   Type: " + rs.getString("Type"));
		    	  this.add(Account);
		    	  
		    	  JLabel Balance = new JLabel("Initial Balance: " + initial_amount + "  Final Balance:   " + final_amount);
		    	  this.add(Balance);
		    	  if((final_amount) > 100000) 
						JOptionPane.showMessageDialog(null, "The limit of the insurance has been reached!", "", JOptionPane.PLAIN_MESSAGE);				

		    	  
			      final DefaultListModel a3 = new DefaultListModel();
			     
			      
			      String sql_getCustomerNameAndAddress = "SELECT C.Name, C.Address FROM Customer C INNER JOIN Own_by O ON O.Aid = '" + rs.getString("Aid") + "' AND O.TaxID = C.TaxID";
			      String getTransaction = "SELECT T.Tid, T.Aid_1, T.Aid_2, T.TypeTransaction, T.Amount FROM Record_Transaction T WHERE T.Aid_1 = '" + rs.getString("Aid") + "' OR T.Aid_2 = '" + rs.getString("Aid") + "'";
			      
			      ResultSet rs2 = stmt2.executeQuery(sql_getCustomerNameAndAddress);
			      while(rs2.next()) {
			    	  a3.addElement("Name: " + rs2.getString("Name") + "      " + "Address: " + rs2.getString("Address"));
			      }
			      
			      final JList ListCustomer = new JList(a3); 
			      ListCustomer.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			      ListCustomer.setSelectedIndex(0);
			      JScrollPane ListCustomerPane = new JScrollPane(ListCustomer);
			      ListCustomerPane.setPreferredSize(new Dimension(300, 100));
			      ListCustomerPane.setAlignmentX(Component.CENTER_ALIGNMENT);
			      ListCustomerPane.setAlignmentY(Component.CENTER_ALIGNMENT);
			      this.add(ListCustomerPane);
			      

			      
			      
			      

			      //final DefaultListModel a4 = new DefaultListModel();
			      ResultSet rs3 = stmt3.executeQuery(getTransaction);
			      while(rs3.next()) {
			    	  String type = rs3.getString("TypeTransaction").replaceAll(" ", "");
			          if(type.equals("Deposit")) 
			           a4.addElement("Tid: " + rs3.getString("Tid") + "      " + rs3.getString("TypeTransaction") + "   To:" + rs3.getString("Aid_2") +  " $" + rs3.getString("Amount"));
			          else if(type.equals("Check") || type.equals("Withdrawal")) 
			           a4.addElement("Tid: " + rs3.getString("Tid") + "      " + rs3.getString("TypeTransaction") + "   From:" + rs3.getString("Aid_1") +  " $" + rs3.getString("Amount"));
			          else if(type.equals("Top-Up"))
			           a4.addElement("Tid: " + rs3.getString("Tid") + "   From: " + rs3.getString("Aid_2") + " " + rs3.getString("TypeTransaction") + "   To:" + rs3.getString("Aid_1") +  " $" + rs3.getString("Amount"));
			          else if(type.equals("Wire") || type.equals("Transfer"))
			           a4.addElement("Tid: " + rs3.getString("Tid") + "   From: " + rs3.getString("Aid_1") + " " + rs3.getString("TypeTransaction") + "   To:" + rs3.getString("Aid_2") +  " $" + rs3.getString("Amount"));
			          else if(type.equals("Purchase")) 
			           a4.addElement("Tid: " + rs3.getString("Tid") + "      " + rs3.getString("Aid_1") + "      " + rs3.getString("TypeTransaction") + " $" + rs3.getString("Amount"));
			          else if(type.equals("Collect"))
			           a4.addElement("Tid: " + rs3.getString("Tid") + "   From: " + rs3.getString("Aid_2") + " " + rs3.getString("TypeTransaction") + "   To:" + rs3.getString("Aid_1") +  " $" + rs3.getString("Amount"));
			          else if(type.equals("Pay-Friend"))
			           a4.addElement("Tid: " + rs3.getString("Tid") + "   From: " + rs3.getString("Aid_1") + " " + rs3.getString("TypeTransaction") + "   To:" + rs3.getString("Aid_2") +  " $" + rs3.getString("Amount"));
			          else if(type.equals("interest"))
			           a4.addElement("Tid: " + rs3.getString("Tid") + "      " + rs3.getString("TypeTransaction") + "   To:" + rs3.getString("Aid_2") +  " $" + rs3.getString("Amount"));
			      }
		      }
		      
		      JLabel Transaction = new JLabel("All Transactions:     ");
		      this.add(Transaction);
		      
		      final JList ListType = new JList(a4);
		      ListType.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		      ListType.setSelectedIndex(1);
		      JScrollPane ListTypePane = new JScrollPane(ListType);
		      ListTypePane.setPreferredSize(new Dimension(370, 100));
		      ListTypePane.setAlignmentX(Component.CENTER_ALIGNMENT);
		      ListTypePane.setAlignmentY(Component.CENTER_ALIGNMENT);
		      this.add(ListTypePane);
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
