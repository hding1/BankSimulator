package GUI.BankTellerWindow;

import java.util.*; 
import java.awt.event.*; 
import java.awt.*; 
import javax.swing.*;
import java.sql.*;
import GUI.BankTellerMonitor.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DTERWindow extends JFrame{
 
 final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
 final String DB_URL = "jdbc:oracle:thin:@cloud-34-133.eci.ucsb.edu:1521:XE";

 final String USERNAME = "fliang";
 final String PASSWORD = "123455";
 
 Connection conn = null;
 Statement stmt = null;
 Statement stmt2 = null;
 Statement stmt3 = null;

 
 public void launchWindow() {
  this.getContentPane().setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
  this.setTitle("DTER Customer Name: ");
  this.setSize(370,200);
  this.setResizable(false);
  this.setLayout(new FlowLayout());
  this.setDefaultCloseOperation(EXIT_ON_CLOSE);
  this.setLocationRelativeTo(null);
  
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

        String sql_getName = "SELECT C.Name, C.TaxID FROM Customer C";
        
        ResultSet rs = stmt.executeQuery(sql_getName);
        
        final DefaultListModel a3 = new DefaultListModel();
      
        
        while(rs.next()){
         

         float amount = 0;
         
         String sql_getAmount = "SELECT T.Tid, T.Amount, T.TypeTransaction FROM Record_Transaction T INNER JOIN Own_by O ON T.Aid_1 = O.Aid AND O.TaxID = '" + rs.getString("TaxID") + "'";
         String sql_getAmount2 = "SELECT T.Tid, T.Amount, T.TypeTransaction FROM Record_Transaction T INNER JOIN Own_by O ON T.Aid_2 = O.Aid AND O.TaxID = '" + rs.getString("TaxID") + "'";
         
         ResultSet rs2 = stmt2.executeQuery(sql_getAmount);
         ResultSet rs3 = stmt3.executeQuery(sql_getAmount2);
         
         while(rs3.next()) {
          String type = rs3.getString("TypeTransaction").replaceAll(" ", "");
          if(type.equals("Deposit")) {// || type.equals("Transfer") || type.equals("Wire"))  {
           amount += Float.parseFloat(rs3.getString("Amount"));
          }
         }
         
         while(rs2.next()) {
          String type = rs2.getString("TypeTransaction").replaceAll(" ", "");
          if(type.equals("Transfer") || type.equals("Wire")) {
           amount += Float.parseFloat(rs2.getString("Amount"));
          }
         }
         
         while(rs3.next()) {
          String type = rs.getString("type").replaceAll(" ", "");
         }
         
        
         
         if(amount > 10000) {
          a3.addElement(rs.getString("Name") );
         }
        }
        rs.close();
        
        final JList ListCustomer = new JList(a3); 
        ListCustomer.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListCustomer.setSelectedIndex(0);
        JScrollPane ListCustomerPane = new JScrollPane(ListCustomer);
        ListCustomerPane.setPreferredSize(new Dimension(200, 150));
        ListCustomerPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        ListCustomerPane.setAlignmentY(Component.CENTER_ALIGNMENT);
        this.add(ListCustomerPane);
        
        JButton back = new JButton("back");
        DTERWindowMonitor dw = new DTERWindowMonitor(this);
        back.addActionListener(dw);
        this.add(back);
        
        
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