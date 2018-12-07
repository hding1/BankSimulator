package GUI.BankTellerWindow;

import java.awt.Component;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import java.sql.*;

import GUI.BankTellerMonitor.*;

public class customerReportWindow extends JFrame{
	private JLabel CustomerName;
	private createReportWindow crw;
	
	public customerReportWindow(createReportWindow crw){
		this.crw = crw;
	}

	public void launchCustomerReportWindow() {
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
		this.setLayout(new FlowLayout());
		this.setTitle("");
		this.setSize(370, 400);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		CustomerName = new JLabel("Customer Name:      " + this.crw.getCname().getText());
		this.add(CustomerName);
		
		JLabel Text = new JLabel("Account ID:            Account Type:          Status:");
		this.add(Text);
		
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

		      System.out.println(this.crw.getCname().getText());

		      //String sql = "SELECT A.Aid, A.Type, A.Open FROM Account A, Customer C WHERE A.TaxID = C.TaxID AND C.Name = '" + this.crw.getCname().getText() + "'";
		      String sql = "SELECT O.Aid, A.Type, A.Open FROM Own_by O INNER JOIN Customer C ON C.TaxID = '" + this.crw.getCname().getText() + "' AND O.TaxID = C.TaxID INNER JOIN Account A ON O.Aid = A.Aid";
		      final DefaultListModel a3 = new DefaultListModel();
		      System.out.println("succeed");
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      while(rs.next()){
		    	  String type = "";
		    	  type = rs.getString("Type");
		    	  
		    	  a3.addElement(rs.getString("Aid") + "            " + type + "                      " + rs.getString("Open"));
		    	  
		      }
		      
		      final JList ClosedID = new JList(a3); 
		      ClosedID.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		      ClosedID.setSelectedIndex(0);
		      JScrollPane ClosedIDScrollPane = new JScrollPane(ClosedID);
		      ClosedIDScrollPane.setPreferredSize(new Dimension(300, 300));
		      ClosedIDScrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
		      ClosedIDScrollPane.setAlignmentY(Component.CENTER_ALIGNMENT);
		      this.add(ClosedIDScrollPane);
		      
		      JButton back = new JButton("Back");
		      customerReportMonitor crm = new customerReportMonitor(this);
		      back.setAlignmentX(Component.CENTER_ALIGNMENT);
		      back.setAlignmentY(Component.CENTER_ALIGNMENT);
		      back.addActionListener(crm);
		      this.add(back);
		      
		      
		}catch (SQLException se) {
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
