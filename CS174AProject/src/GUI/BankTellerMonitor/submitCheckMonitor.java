package GUI.BankTellerMonitor;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Random;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;



import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import GUI.BankTellerWindow.*;

public class submitCheckMonitor implements ActionListener {
 private submitCheckWindow scw;
 private String Tid;

 
 final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
 final String DB_URL = "jdbc:oracle:thin:@cloud-34-133.eci.ucsb.edu:1521:XE";

 final String USERNAME = "fliang";
 final String PASSWORD = "123455";

 Connection conn = null;
 Statement stmt = null;
 
 public submitCheckMonitor(submitCheckWindow scw) {
  this.scw = scw;

 }
 
 
 
 
 public void actionPerformed(ActionEvent a) {
   
  
  try{
        //STEP 2: Register JDBC driver
        Class.forName(JDBC_DRIVER);

        //STEP 3: Open a connection
        System.out.println("Connecting to a selected database...");
        conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        System.out.println("Connected database successfully...");
        
        //STEP 4 Execute a query
        System.out.println("Creating statement...");
        stmt = conn.createStatement();       

        
        ArrayList<String> TList = new ArrayList<String>();
        
        String sql = "SELECT Tid FROM Record_Transaction";
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()) {
         if(rs.getString("Tid").substring(0, 1).equals("0")) {
          TList.add(rs.getString("Tid"));
         }
        }

        //generate Tid;
        do {
         Tid = "0";
         Random rand = new Random();
         for(int i = 0; i < 9; i++)
          Tid += rand.nextInt(10);  
         System.out.println(Tid);       
       
        }while(TList.contains(Tid) == true);
       
        
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date()); 
        System.out.println(date);
        date = date.substring(6, 10) + date.substring(3, 5) + date.substring(0, 2);
        System.out.println(date);
        
        String sql_insert= "INSERT INTO Record_Transaction(Tid, TransactionDate, Aid_1, Aid_2, TypeTransaction, Amount) VALUES ('" + Tid + "', '" + date + "', '" + this.scw.getCheckAid() + "', '" + this.scw.getCheckAid() + "' , 'Check', '" + this.scw.getCheckAmount() + "' )";
        PreparedStatement insert = conn.prepareStatement(sql_insert);
        insert.executeUpdate();
        String sql_subtract = "UPDATE Account A SET A.Amount = A.Amount - " + this.scw.getCheckAmount() + "WHERE A.Aid = '" + this.scw.getCheckAid() + "'";
        PreparedStatement subtract = conn.prepareStatement(sql_subtract);
        subtract.executeUpdate();
        String sql_insertCheck = "INSERT INTO CheckTransaction( Tid, Aid, CheckNum) VALUES ( '" + Tid + "', '" + this.scw.getCheckAid() + "', '" + this.scw.getCheckNum() + "')";                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
        PreparedStatement insertCheck = conn.prepareStatement(sql_insertCheck);
        insertCheck.executeUpdate();
        System.out.println("check succeeded! ");
       
        
        JOptionPane.showMessageDialog(null, "Check Transaction Completed! ", "", JOptionPane.PLAIN_MESSAGE);
        this.scw.setVisible(false);
  }catch (SQLException se) {
      JOptionPane.showMessageDialog(null, "Fields are Invalid! ", "", JOptionPane.PLAIN_MESSAGE);

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
 
 

 
 public boolean checkUnique(ArrayList<String> a) {
  
  return true;
 }
 
 
}