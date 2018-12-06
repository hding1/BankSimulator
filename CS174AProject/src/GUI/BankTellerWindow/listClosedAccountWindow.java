package GUI.BankTellerWindow;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.util.*; 
import java.awt.event.*; 
import java.awt.*; 
import javax.swing.*;
import java.sql.*;
import GUI.BankTellerMonitor.*;

public class listClosedAccountWindow extends JFrame{

	public void launchListClosedAccountWindow() {
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
		this.setTitle("Closed Account");
		this.setLayout(new FlowLayout());
		this.setSize(300,280);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
		final String DB_URL = "jdbc:oracle:thin:@cloud-34-133.eci.ucsb.edu:1521:XE";

		final String USERNAME = "fliang";
		final String PASSWORD = "123455";

		Connection conn = null;
		Statement stmt = null;
		

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
		      //String sql = "SELECT * FROM Customer C Where C.TaxID = "+this.loginWindow.getUserid().getText();
		      String sql2 = "SELECT Aid, Open FROM Account WHERE Open = '0'";
		      
		      final DefaultListModel a3 = new DefaultListModel();
		      
		      ResultSet rs = stmt.executeQuery(sql2);
		      while(rs.next()){
		    	  //JLabel ClosedID = new JLabel(rs.getString("Aid"));
		    	  //this.add(ClosedID);
		    	  //a2.add(rs.getString("Aid"));
		    	  a3.addElement("Closed Account ID:       " + rs.getString("Aid"));
		    	  
		    	  System.out.println("Found COMPLETE");
		      }
		      final JList ClosedID = new JList(a3); 
		      ClosedID.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		      ClosedID.setSelectedIndex(0);
		      ClosedID.setVisibleRowCount(3); 
		      JScrollPane ClosedIDScrollPane = new JScrollPane(ClosedID); 
		      ClosedIDScrollPane.setPreferredSize(new Dimension(280, 200));
		      this.add(ClosedIDScrollPane);
		      JButton backButton = new JButton("Back");
		      listClosedAccountMonitor lcam = new listClosedAccountMonitor(this);
		      backButton.addActionListener(lcam);
		      
		      this.add(backButton);
		      
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
		
		this.setVisible(true);
	}
	
	
}
