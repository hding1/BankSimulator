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
import User.Customer;
import GUI.windows.SelectAccountWindow;
import GUI.windows.SelectWindow;
import GUI.windows.SignUPWindow;

import java.sql.*;

public class SignUPButtonMonitor implements ActionListener {
	private SignUPWindow SignUPWindow;
	private Customer c;

	final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	final String DB_URL = "jdbc:oracle:thin:@cloud-34-133.eci.ucsb.edu:1521:XE";

	final String USERNAME = "fliang";
	final String PASSWORD = "123455";

	Connection conn = null;
	Statement stmt = null;
	boolean flag = true;

	public SignUPButtonMonitor(SignUPWindow SignUPWindow) {
		this.SignUPWindow = SignUPWindow;
	}

	/**
	 * 
	 * @param loginWindow
	 */

	public void actionPerformed(ActionEvent a) {
		int command = Integer.parseInt(a.getActionCommand());
		switch (command) {
		case 1:
			// TO DO:
			// INSERT INFORMATION INTO SQL TABLE
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

//			      createTable(conn);

				insertCustomer(conn);
				if (flag) {
					insertAccount(conn);
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
			break;
		case 2:
			loginWindow lw = new loginWindow();
			lw.launchLoginWindow();
			this.SignUPWindow.setVisible(false);
			break;
		}
		

	}

	public void createTable(Connection conn) throws Exception {
		try {
			PreparedStatement create = conn.prepareStatement(
					"CREATE TABLE IF NOT EXIST Customer(Name CHAR[10], TaxID CHAR[10], Address CHAR[100], Address CHAR[100],PIN CHAR[4], PRIMARY KEY(TaxID))");
			create.executeUpdate();
			create = conn.prepareStatement(
					"CREATE TABLE IF NOT EXISTS Account( Aid CHAR(10), Pin CHAR(4), PrimaryOwner CHAR(15), Amount FLOAT, Branch CHAR(11), Open CHAR(1), PRIMARY KEY(Aid));");
			create.executeUpdate();
			System.out.println("Tables Created");
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("Function completed");
		}
	}

	public void insertCustomer(Connection conn) throws Exception {
		String new_id = this.SignUPWindow.getUserID();
		String new_pin = String.valueOf(this.SignUPWindow.getPIN());
		String new_name = this.SignUPWindow.getPname();
		String new_address = this.SignUPWindow.getaddress();
		try {
			PreparedStatement insert = conn
					.prepareStatement("INSERT INTO Customer (Name, TaxID, Address, PIN) VALUES ('" + new_name + "', '"
							+ new_id + "','" + new_address + "','" + new_pin + "')");
			insert.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);

			SignUPWindow lw1 = new SignUPWindow();
			lw1.launchSignUPWindow();
			this.flag = false;
			JOptionPane.showMessageDialog(this.SignUPWindow, "Invalid Input", "Invalid Input",
					JOptionPane.ERROR_MESSAGE);
			this.SignUPWindow.setVisible(false);
		} finally {
			System.out.println("insert Customer completed!");
		}
	}

	public void insertAccount(Connection conn) throws Exception {
		String new_id = this.SignUPWindow.getUserID();
		String new_branch = this.SignUPWindow.getBranch();
		float initialAmount = this.SignUPWindow.getAmount();
		String type1 = this.SignUPWindow.getAccountType();
		String new_pin = String.valueOf(this.SignUPWindow.getPIN());
		String new_name = this.SignUPWindow.getPname();
		String new_address = this.SignUPWindow.getaddress();
		System.out.print(type1);
		if (!(initialAmount > 0)) {
			JOptionPane.showMessageDialog(this.SignUPWindow, "You must have a positive initial deposit.",
					"Initial Deposit", JOptionPane.ERROR_MESSAGE);
		} else {
			boolean unique = true;
			switch (type1) {
			case "Student-Checking":

				while (unique) {
					try {
						String account_id = AIDGenerator(1);
						PreparedStatement insert = conn.prepareStatement(
								"INSERT INTO Account(Aid, TaxID, Amount, Branch, Open, Type) VALUES ('" + account_id + "', '"
										+ new_id + "', " + initialAmount + ", '" + new_branch + "', '1','Student-Checking')");
						insert.executeUpdate();
						System.out.println("Insert Account completed!");
						insert = conn.prepareStatement(
								"INSERT INTO Own_by (TaxID, Aid) VALUES ('" + new_id + "', '" + account_id + "')");
						insert.executeUpdate();
						System.out.println("Insert Own_by completed!");
						insert = conn
								.prepareStatement("INSERT INTO Student_Checking (Aid, Interest_rate) VALUES ('" + account_id + "',0)");
						insert.executeUpdate();
						System.out.println("Insert Student_Checking completed!");
						insert = conn.prepareStatement("INSERT INTO initialAmount(Aid, Amount) VALUES('"+ account_id +"',"+initialAmount+")");
						insert.executeUpdate();
						c = new Customer(new_name,new_id,new_address,new_pin);
						SelectAccountWindow lw = new SelectAccountWindow(c);
						lw.launchSelectwindow();
						unique = false;
						this.SignUPWindow.setVisible(false);
					} catch (java.sql.SQLIntegrityConstraintViolationException e) {
						System.out.println(e);
					} finally {
						System.out.println("funcion completed!");
					}
				}
				break;

			case "Interest-Checking":
				while (unique) {
					try {

						String account_id = AIDGenerator(2);
						PreparedStatement insert = conn.prepareStatement(
								"INSERT INTO Account(Aid, TaxID, Amount, Branch, Open, Type) VALUES ('" + account_id + "', '"
										+ new_id + "', " + initialAmount + ", '" + new_branch + "', '1','Interest-Checking')");

						insert.executeUpdate();
						insert = conn.prepareStatement(
								"INSERT INTO Own_by (TaxID, Aid) VALUES ('" + new_id + "', '" + account_id + "')");
						insert.executeUpdate();
						insert = conn.prepareStatement("INSERT INTO Interest_Checking (Aid,Interest_rate) VALUES ('"
								+ account_id + "'," + 5.5 + ")");
						
						insert.executeUpdate();
						System.out.println("Insert completed!");
						insert = conn.prepareStatement("INSERT INTO initialAmount(Aid, Amount) VALUES('"+ account_id +"',"+initialAmount+")");
						insert.executeUpdate();
						unique = false;
						c = new Customer(new_name,new_id,new_address,new_pin);
						SelectAccountWindow lw = new SelectAccountWindow(c);
						lw.launchSelectwindow();
						this.SignUPWindow.setVisible(false);
					} catch (Exception e) {
						System.out.println(e);
					} finally {
						System.out.println("funcion completed!");
					}
				}
				break;

			case "Savings":
				while (unique) {
					try {
						String account_id = AIDGenerator(3);
						PreparedStatement insert = conn.prepareStatement(
								"INSERT INTO Account(Aid, TaxID, Amount, Branch, Open,Type) VALUES ('" + account_id + "', '"
										+ new_id + "', " + initialAmount + ", '" + new_branch + "', '1','Savings')");

						insert.executeUpdate();
						insert = conn.prepareStatement(
								"INSERT INTO Own_by (TaxID, Aid) VALUES ('" + new_id + "', '" + account_id + "')");
						insert.executeUpdate();
						insert = conn.prepareStatement(
								"INSERT INTO Saving (Aid,Interest_rate) VALUES ('" + account_id + "'," + 7.5 + ")");
						insert.executeUpdate();
						insert = conn.prepareStatement("INSERT INTO initialAmount(Aid, Amount) VALUES('"+ account_id +"',"+initialAmount+")");
						insert.executeUpdate();
						System.out.println("Insert completed!");
						
						c = new Customer(new_name,new_id,new_address,new_pin);
						SelectAccountWindow lw = new SelectAccountWindow(c);
						lw.launchSelectwindow();
						unique = false;
						this.SignUPWindow.setVisible(false);
					} catch (Exception e) {
						System.out.println(e);
					} finally {
						System.out.println("funcion completed!");
					}
				}
				break;

			}

		}
	}

	public String AIDGenerator(int type) {
		String result = Integer.toString(type);
		Random rand = new Random();
		for (int i = 0; i < 4; i++) {
			int rand_int = rand.nextInt(10);
			result += Integer.toString(rand_int);
		}

		return result;
	}

}
