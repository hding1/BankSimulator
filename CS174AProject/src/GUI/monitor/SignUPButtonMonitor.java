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
	
	public SignUPButtonMonitor(SignUPWindow SignUPWindow) {
		this.SignUPWindow = SignUPWindow;
	}
	
	/**

	 * @param loginWindow
	 */
	
	
	public SignUPButtonMonitor(loginWindow loginWindow) {
		this.loginWindow = loginWindow;
	}
	
	
	
	public void actionPerformed(ActionEvent a) {
		this.loginWindow.setVisible(false);
		SignUPWindow SignUPWindow=new SignUPWindow();
		SignUPWindow.launchSignUPWindow();
	}
}
