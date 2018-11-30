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
import GUI.windows.PocketWindow;
import GUI.windows.SelectAccountWindow;
import GUI.windows.SelectWindow;
import GUI.windows.SignUPWindow;

import java.sql.*;

public class SelectAccountMonitor implements ActionListener {
	private SelectAccountWindow saw;
	private Customer c;

	final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	final String DB_URL = "jdbc:oracle:thin:@cloud-34-133.eci.ucsb.edu:1521:XE";

	final String USERNAME = "fliang";
	final String PASSWORD = "123455";

	Connection conn = null;
	Statement stmt = null;
	boolean flag = true;

	public SelectAccountMonitor(SelectAccountWindow saw) {
		this.saw = saw;
	}

	/**
	 * 
	 * @param loginWindow
	 */

	public void actionPerformed(ActionEvent a) {
		int command = Integer.parseInt(a.getActionCommand());
		switch (command) {
		case 1:
			if(saw.getAccount().getAccount().charAt(0)!='4') {
				SelectWindow sw = new SelectWindow(saw.getCustomer(),saw.getAccount());
				sw.launchSelectWindow();
				this.saw.setVisible(false);
			}else {
				PocketWindow pw = new PocketWindow(saw.getCustomer(),saw.getAccount());
				pw.launchSelectWindow();
				this.saw.setVisible(false);
			}
			break;
		case 2:
			loginWindow lw = new loginWindow();
			lw.launchLoginWindow();
			this.saw.setVisible(false);
			break;
		}
		

	}


}
