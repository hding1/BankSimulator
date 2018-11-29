package GUI.monitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

//
import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.sound.midi.*;
import java.util.*;
import java.awt.event.*;
import java.net.*;
import javax.swing.event.*;
import javax.swing.JOptionPane;
//

import GUI.windows.loginWindow;
import GUI.windows.SelectWindow;
import GUI.windows.SignUPWindow;

import java.sql.*;

public class SignUPButtonMonitor implements ActionListener{
	private loginWindow loginWindow;
	private SignUPWindow SignUPWindow;
	
	final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	final String DB_URL = "jdbc:oracle:thin:@cloud-34-133.eci.ucsb.edu:1521:XE";

	final String USERNAME = "fliang";
	final String PASSWORD = "123455";
	
	Connection conn = null;
	Statement stmt = null;
	
	public SignUPButtonMonitor(SignUPWindow SignUPWindow) {
		this.SignUPWindow = SignUPWindow;
	}
	
	/**

	 * @param loginWindow
	 */
	
	
	public SignUPButtonMonitor(loginWindow loginWindow) {
		this.loginWindow = loginWindow;
	}
	
	
	
	public void actionPerformed(ActionEvent a){ 	
    	//TO DO:
    	//INSERT INFORMATION INTO SQL TABLE
    	
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
		      
		      createTable(conn);
		      insertCustomer(conn);

		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		}catch(Exception ea){
		      //Handle errors for Class.forName
		      ea.printStackTrace();
		}finally{
		      //finally block used to close resources
			try{
		         if(stmt!=null)
		            conn.close();
		    }catch(SQLException se){
		    }// do nothing
		    try{
		         if(conn!=null)
		            conn.close();
		    }catch(SQLException se){
		         se.printStackTrace();
		    }//end finally try
		}
    	this.SignUPWindow.setVisible(false);
		loginWindow lw = new loginWindow();
		lw.launchLoginWindow();
    }
	public void createTable(Connection conn) throws Exception {
    	try {
    		PreparedStatement create = conn.prepareStatement(
    				"CREATE TABLE IF NOT EXIST Customer(Name CHAR[10], TaxID CHAR[10], Address CHAR[100], Address CHAR[100],PIN CHAR[4], PRIMARY KEY(TaxID))");
    		create.executeUpdate();
    		create = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Account( Aid CHAR(10), Pin CHAR(4), PrimaryOwner CHAR(15), Amount FLOAT, Branch CHAR(11), Open CHAR(1), PRIMARY KEY(Aid));");
    		create.executeUpdate();
    		System.out.println("Tables Created");
    	}catch(Exception e){
    		System.out.println(e);
    	}finally {
    		System.out.println("Function completed");
    	}
    }
    
    public void insertCustomer(Connection conn) throws Exception {
    	String new_id = this.SignUPWindow.getUserID();
    	String new_pin = String.valueOf(this.SignUPWindow.getPIN());
    	String new_name = this.SignUPWindow.getPname();
    	String new_address = this.SignUPWindow.getaddress();
    	try {
    		PreparedStatement insert = conn.prepareStatement("INSERT INTO Customer (Name, TaxID, Address, PIN) VALUES ('"+new_name+"', '"+ new_id+"','"+ new_address+ "','"+ new_pin+")");
    		insert.executeUpdate();
    	} catch(Exception e) {System.out.println(e);}
    	finally {
    		System.out.println("insert completed!");
    	}
    }
    
//    public void insertAccount(Connection conn) throws Exception {
//    	String new_id = this.SignUPWindow.getUserID();
//    	String new_branch = this.SignUPWindow.getBranch();
//    	float initialAmount = this.SignUPWindow.getAmount();
//    	String initialStatus = "1";
//    	String type1 = this.SignUPWindow.getAccountType();
//    	
//    	switch(type1) {
//    	
//    		case "Student_check":
//    			try {
//    				PreparedStatement insert = conn.prepareStatement("INSERT INTO Account (Aid, Pin, PrimaryOwner, Amount, Branch, Open) VALUES ('" + new_id + "', '" + new_pin + "', '" + new_name + "', '" + initialAmount + "', '" + new_branch + "', '" + initialStatus + "')");
//    		
//    				insert.executeUpdate();
//    				System.out.println("Insert completed!");
//    		
//    			} catch(Exception e) {System.out.println(e);}
//    			finally {
//    				System.out.println("funcion completed!");
//    			}
//    			break;
//    			
//    		case "Interest_check":
//    			try {
//    				PreparedStatement insert = conn.prepareStatement("INSERT INTO Account (Aid, Pin, PrimaryOwner, Amount, Branch, Open) VALUES ('" + new_id + "', '" + new_pin + "', '" + new_name + "', '" + initialAmount + "', '" + new_branch + "', '" + initialStatus + "')");
//    		
//    				insert.executeUpdate();
//    				System.out.println("Insert completed!");
//    		
//    			} catch(Exception e) {System.out.println(e);}
//    			finally {
//    				System.out.println("funcion completed!");
//    			}
//    			break;
//    			
//    		case "Saving":
//    			try {
//    				PreparedStatement insert = conn.prepareStatement("INSERT INTO Account (Aid, Pin, PrimaryOwner, Amount, Branch, Open) VALUES ('" + new_id + "', '" + new_pin + "', '" + new_name + "', '" + initialAmount + "', '" + new_branch + "', '" + initialStatus + "')");
//    		
//    				insert.executeUpdate();
//    				System.out.println("Insert completed!");
//    		
//    			} catch(Exception e) {System.out.println(e);}
//    			finally {
//    				System.out.println("funcion completed!");
//    			}
//    			break;
//    	}
//    	
//    }
    
    
}
