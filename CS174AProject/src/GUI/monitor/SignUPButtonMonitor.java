package GUI.monitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JOptionPane;


import GUI.windows.loginWindow;
import GUI.windows.SelectWindow;

import java.sql.*;

public class SignUPButtonMonitor implements ActionListener{
	private loginWindow loginWindow;
	
	/**

	 * @param loginWindow
	 */
	
	static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	static final String DB_URL = "jdbc:oracle:thin:@cloud-34-133.eci.ucsb.edu:1521:XE";

	static final String USERNAME = "fliang";
	static final String PASSWORD = "123455";
	
	public SignUPButtonMonitor(loginWindow loginWindow) {
		this.loginWindow = loginWindow;
	}
	
	
	
	public void actionPerformed(ActionEvent a) {
		
		Connection conn = null;
		Statement stmt = null;
		
	}
}
