package GUI.BankTellerWindow;

import java.awt.Component;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import GUI.BankTellerMonitor.*;

public class submitCheckWindow extends JFrame{
	
	private String CustomerName;
	private JList ListAccount;
	private JTextField enterCheckAid;
	private JTextField enterCheckAmount;

	final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	final String DB_URL = "jdbc:oracle:thin:@cloud-34-133.eci.ucsb.edu:1521:XE";

	final String USERNAME = "fliang";
	final String PASSWORD = "123455";
	public static String selectedAccount = "";

	Connection conn = null;
	Statement stmt = null;
	
	public String getSelectedAccount() {
		return selectedAccount;
	}
	
	public String getCheckAid() {
		return this.enterCheckAid.getText();
	}
	
	public String getCheckAmount() {
		return this.enterCheckAmount.getText(); 
	}
	
	public void launchSubmitCheckWindow(String CustomerName) {	
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
		//this.setLayout(new FlowLayout());
		this.setTitle("Enter Check Transaction");
		this.setSize(370,260);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		this.CustomerName = CustomerName;

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

		      

		      String sql = "SELECT A.Aid FROM Account A, Customer C WHERE A.TaxID = C.TaxID AND C.Name = '" + CustomerName + "'";
		      final DefaultListModel a3 = new DefaultListModel();
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      while(rs.next()){
		    	  if(rs.getString("Aid").substring(0, 1).equals("1") || (rs.getString("Aid").substring(0, 1).equals("2")) || (rs.getString("Aid").substring(0, 1).equals("3")))
		    	  a3.addElement(rs.getString("Aid"));
		    	  
		      }


		      
		      ListAccount = new JList(a3); 
		      /*ListSelectionListener listSelectionListener =  new ListSelectionListener(){
		    	  
		    	  public void valueChanged(ListSelectionEvent listSelectionEvent) {
		    	        boolean adjust = listSelectionEvent.getValueIsAdjusting();
		    	        if (!adjust) {
		    	          JList list = (JList) listSelectionEvent.getSource();
		    	          int selections[] = list.getSelectedIndices();
		    	          Object selectionValues[] = list.getSelectedValues();
		    	          for (int i = 0, n = selections.length; i < n; i++) {
		    	        	submitCheckWindow.selectedAccount = selectionValues[i].toString();
		    	            System.out.println(submitCheckWindow.selectedAccount + "jjj");
		    	          }
		    	        }
		    	    }
		      };
		      ListAccount.addListSelectionListener(listSelectionListener);*/
		      ListAccount.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		      ListAccount.setSelectedIndex(0);
		      JScrollPane ListAccountPane = new JScrollPane(ListAccount);
		      ListAccountPane.setPreferredSize(new Dimension(300, 100));
		      ListAccountPane.setMaximumSize(new Dimension(300, 100));
		      ListAccountPane.setAlignmentX(Component.CENTER_ALIGNMENT);
		      ListAccountPane.setAlignmentY(Component.CENTER_ALIGNMENT);
		      
		      
		      this.add(ListAccountPane);
		      
		      
		      JLabel checkAid = new JLabel("Enter the Account You Want to Make Check Showned Above:");
		      checkAid.setAlignmentX(Component.CENTER_ALIGNMENT);
		      checkAid.setAlignmentY(Component.CENTER_ALIGNMENT);
		      this.add(checkAid);
		      
		      enterCheckAid = new JTextField("");
		      enterCheckAid.setMaximumSize(new Dimension(300, 30));
		      enterCheckAid.setAlignmentX(Component.CENTER_ALIGNMENT);
		      enterCheckAid.setAlignmentY(Component.CENTER_ALIGNMENT);
		      this.add(enterCheckAid);	

		      JLabel checkAmount = new JLabel("Enter the Check Amount: ");
		      checkAmount.setAlignmentX(Component.CENTER_ALIGNMENT);
		      checkAmount.setAlignmentY(Component.CENTER_ALIGNMENT);
		      this.add(checkAmount);
		      
		      enterCheckAmount = new JTextField("");
		      enterCheckAmount.setMaximumSize(new Dimension(300, 30));
		      enterCheckAmount.setAlignmentX(Component.CENTER_ALIGNMENT);
		      enterCheckAmount.setAlignmentY(Component.CENTER_ALIGNMENT);
		      this.add(enterCheckAmount);		     
		      
		      
		      JButton submit = new JButton("Submit");
		      submitCheckMonitor crm = new submitCheckMonitor(this);
		      submit.setAlignmentX(Component.CENTER_ALIGNMENT);
		      submit.setAlignmentY(Component.CENTER_ALIGNMENT);
		      submit.addActionListener(crm);
		      this.add(submit);
		      

		      
		      
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
