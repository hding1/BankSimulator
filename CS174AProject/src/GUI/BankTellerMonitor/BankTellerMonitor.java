package GUI.BankTellerMonitor;

import java.awt.event.ActionListener;

import GUI.windows.BankTellerWindow;
import User.Interest_check_account;
import User.Saving_account;
import User.Student_check_account;
import User.Transaction;
import GUI.BankTellerWindow.*;
import GUI.windows.*;

import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;

import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;


public class BankTellerMonitor extends JFrame implements ActionListener{
	private BankTellerWindow bankTellerWindow;
	private JTextField check_transaction;
	private JTextField cname;
	static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	static final String DB_URL = "jdbc:oracle:thin:@cloud-34-133.eci.ucsb.edu:1521:XE";

	static final String USERNAME = "fliang";
	static final String PASSWORD = "123455";
	Connection conn = null;
	Statement stmt = null;
	String otherAccount;
	
	public BankTellerMonitor(BankTellerWindow bankTellerWindow) {
		this.bankTellerWindow = bankTellerWindow;
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		int command = Integer.parseInt(e.getActionCommand());
		String m = e.getActionCommand();
		switch (m) {
		case "1":
			enterCheckTransactionWindow ectw =new enterCheckTransactionWindow();
			ectw.launchEnterCheckTransactionWindow();
			System.out.println("1");
			
			break;
		case "2":
			generateMonthlyStatementWindow gmsw = new generateMonthlyStatementWindow();
			gmsw.launchMonthlyStatementWindow();
			System.out.println("2");

			break;
		case "3":
			listClosedAccountWindow lcaw = new listClosedAccountWindow();
			lcaw.launchListClosedAccountWindow();
			System.out.println("List Closed Account");

			break;
		case "4":
			System.out.println("4");
			DTERWindow dw = new DTERWindow();
			dw.launchWindow();

			break;
        case "5":
			System.out.println("5");
			createReportWindow crw = new createReportWindow();
			crw.launchCreateReportWindow();
			
			//JButton createReport = new JButton("Create Report");
			//createReportMonitor crm = createReportMonitor(this);
			//this.add(createReport);
			

			
			break;
        case "6":
        	ArrayList<Saving_account> savinglist = new ArrayList<Saving_account>();
			ArrayList<Student_check_account> studentlist = new ArrayList<Student_check_account>();
			ArrayList<Interest_check_account> checkinglist = new ArrayList<Interest_check_account>();
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

//			    //1. saving case
				String sql = "SELECT * FROM Account A, Saving B WHERE A.aid = B.aid AND A.open = 1";
				PreparedStatement update = conn.prepareStatement(sql);
				ResultSet rs = update.executeQuery();
			    boolean added = false;
			    while(rs.next()){
			    	String aid = rs.getString("Aid");
			    	String taxID = rs.getString("TaxID");
			    	float amount = rs.getFloat("Amount");
			    	String branch = rs.getString("Branch");
			    	char open = rs.getString("Open").charAt(0);
			    	float ir = rs.getFloat("Interest_rate");
			    	savinglist.add(new Saving_account(aid,taxID,amount,branch,open,ir));
			    }
			    
			    sql = "SELECT * FROM Account A, Student_Checking B WHERE A.aid = B.aid AND A.open = 1";
				update = conn.prepareStatement(sql);
				rs = update.executeQuery();
				
				while(rs.next()){
			    	String aid = rs.getString("Aid");
			    	String taxID = rs.getString("TaxID");
			    	float amount = rs.getFloat("Amount");
			    	String branch = rs.getString("Branch");
			    	char open = rs.getString("Open").charAt(0);
			    	float ir = rs.getFloat("Interest_rate");
			    	studentlist.add(new Student_check_account(aid,taxID,amount,branch,open,ir));
			    }
				
			    sql = "SELECT * FROM Account A, Interest_Checking B WHERE A.aid = B.aid AND A.open = 1";
				update = conn.prepareStatement(sql);
				rs = update.executeQuery();
				
				while(rs.next()){
			    	String aid = rs.getString("Aid");
			    	String taxID = rs.getString("TaxID");
			    	float amount = rs.getFloat("Amount");
			    	String branch = rs.getString("Branch");
			    	char open = rs.getString("Open").charAt(0);
			    	float ir = rs.getFloat("Interest_rate");
			    	checkinglist.add(new Interest_check_account(aid,taxID,amount,branch,open,ir));
			    }
				boolean flag = true;
				for(int i=0; i<savinglist.size();i++) {
					for(int j=0;j<savinglist.get(i).getList().size();j++) {
						
						if(savinglist.get(i).getList().get(j).getType().replaceAll(" ", "").equals("interest")) {
							flag = false;
						}
					}
					System.out.println(savinglist.get(i).getInitial()+" "+savinglist.get(i).getAccount());
					if(flag) {
						float[] result = new float[30];
						ArrayList<Transaction> transactions = new ArrayList<Transaction>(); 
						transactions = savinglist.get(i).getList();
						for(int x = 0; x < transactions.size() ; x++) {
							Transaction tran = transactions.get(x);
							String date = transactions.get(x).getDate().replaceAll(" ","").substring(6);
							int day = Integer.parseInt(date);
							if(day<=0) {
								day = 1;
							}
							if(day>30) {
								day=30;
							}
							if(tran.getType()=="Deposit") {
								result[day]+=tran.getAmount();
							}
							if(tran.getType()=="Withdraw") {
								result[day]-=tran.getAmount();
							}
							if(tran.getType()=="Transfer") {
								if(tran.getAid1().equals(savinglist.get(i).getAccount())) {
									result[day]-=tran.getAmount();
								}else {
									result[day]+=tran.getAmount();
								}
							}
							if(tran.getType()=="Wire") {
								if(tran.getAid1().equals(savinglist.get(i).getAccount())) {
									result[day]-=tran.getAmount();
								}else {
									result[day]+=tran.getAmount();
								}
							}
						}
						float interest = 0;
						float amount = savinglist.get(i).getInitial();
						for(int j = 0;j<30;j++) {
							System.out.println(amount);
							amount += result[j];
							interest += amount*savinglist.get(i).getIntere_rate()/100/30/12;
						}
						amount += interest;
						sql = "UPDATE Account SET Amount = "+amount+"WHERE Aid = '"+savinglist.get(i).getAccount()+"'";
						update = conn.prepareStatement(sql);
						update.executeUpdate();
						String tid=TIDGenerator(9);
						String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
						update = conn.prepareStatement("INSERT INTO Record_Transaction (Tid, TransactionDate, Aid_1, Aid_2, TypeTransaction, Amount ) VALUES ('" + tid + "','" + timeStamp + "','"+savinglist.get(i).getAccount()+"','" +savinglist.get(i).getAccount()+"','interest'," + interest+")");
						update.executeUpdate();
					}else {
						added = true;
						System.out.println("Already Added");
					}
					flag = true;
				}
				for(int i=0; i<studentlist.size();i++) {

					for(int j=0;j<studentlist.get(i).getList().size();j++) {
						
						if(studentlist.get(i).getList().get(j).getType().replaceAll(" ", "").equals("interest")) {
							flag = false;
							added = false;
						}

					}
					if(flag) {
						float[] result = new float[30];
						ArrayList<Transaction> transactions = new ArrayList<Transaction>(); 
						transactions = studentlist.get(i).getList();
						for(int x = 0; x < transactions.size() ; x++) {
							Transaction tran = transactions.get(x);
							String date = transactions.get(x).getDate().replaceAll(" ","").substring(6);
							int day = Integer.parseInt(date);
							if(day<=0) {
								day = 1;
							}
							if(day>30) {
								day=30;
							}
							if(tran.getType()=="Deposit") {
								result[day]+=tran.getAmount();
							}
							if(tran.getType()=="Check") {
								result[day]-=tran.getAmount();
							}
							if(tran.getType()=="Withdraw") {
								result[day]-=tran.getAmount();
							}
							if(tran.getType()=="Transfer") {
								if(tran.getAid1().equals(studentlist.get(i).getAccount())) {
									result[day]-=tran.getAmount();
								}else {
									result[day]+=tran.getAmount();
								}
							}
							if(tran.getType()=="Wire") {
								if(tran.getAid1().equals(studentlist.get(i).getAccount())) {
									result[day]-=tran.getAmount();
								}else {
									result[day]+=tran.getAmount();
								}
							}
						}
						float interest = 0;
						float amount = studentlist.get(i).getInitial();
						
						for(int j = 0;j<30;j++) {
							amount += result[j];
							
							interest += amount*studentlist.get(i).getIntere_rate()/100/30/12;
						}
						amount += interest;
						
						sql = "UPDATE Account SET Amount = "+amount+"WHERE Aid = '"+studentlist.get(i).getAccount()+"'";
						update = conn.prepareStatement(sql);
						update.executeUpdate();
						String tid=TIDGenerator(9);
						String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
						update = conn.prepareStatement("INSERT INTO Record_Transaction (Tid, TransactionDate, Aid_1, Aid_2, TypeTransaction, Amount ) VALUES ('" + tid + "','" + timeStamp + "','"+studentlist.get(i).getAccount()+"','" +studentlist.get(i).getAccount()+"','interest'," + interest+")");
						update.executeUpdate();
						
					}else {
						added = true;
						System.out.println("Already Added");
					}
					flag = true;
				}
				for(int i=0; i<checkinglist.size();i++) {
					
					for(int j=0;j<checkinglist.get(i).getList().size();j++) {
						
						if(checkinglist.get(i).getList().get(j).getType().replaceAll(" ", "").equals("interest")) {
							flag = false;
						}
					}
					if(flag) {
						float[] result = new float[30];
						ArrayList<Transaction> transactions = new ArrayList<Transaction>(); 
						transactions = checkinglist.get(i).getList();
						for(int x = 0; x < transactions.size() ; x++) {
							Transaction tran = transactions.get(x);
							String date = transactions.get(x).getDate().replaceAll(" ","").substring(6);
							System.out.println(date);
							int day = Integer.parseInt(date);
							if(day<=0) {
								day = 1;
							}
							if(day>30) {
								day=30;
							}
							if(tran.getType()=="Deposit") {
								result[day]+=tran.getAmount();
							}
							if(tran.getType()=="Withdraw") {
								result[day]-=tran.getAmount();
							}
							if(tran.getType()=="Check") {
								result[day]-=tran.getAmount();
							}
							if(tran.getType()=="Transfer") {
								if(tran.getAid1().equals(checkinglist.get(i).getAccount())) {
									result[day]-=tran.getAmount();
								}else {
									result[day]+=tran.getAmount();
								}
							}
							if(tran.getType()=="Wire") {
								if(tran.getAid1().equals(checkinglist.get(i).getAccount())) {
									result[day]-=tran.getAmount();
								}else {
									result[day]+=tran.getAmount();
								}
							}
						}
						float interest = 0;
						float amount = checkinglist.get(i).getInitial();
						for(int j = 0;j<30;j++) {
							amount += result[j];
							interest += amount*checkinglist.get(i).getInterest_rate()/100/30/12;
						}
						amount += interest;
						sql = "UPDATE Account SET Amount = "+amount+"WHERE Aid = '"+checkinglist.get(i).getAccount()+"'";
						update = conn.prepareStatement(sql);
						update.executeUpdate();
						String tid=TIDGenerator(9);
						String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
						update = conn.prepareStatement("INSERT INTO Record_Transaction (Tid, TransactionDate, Aid_1, Aid_2, TypeTransaction, Amount ) VALUES ('" + tid + "','" + timeStamp + "','"+checkinglist.get(i).getAccount()+"','" +checkinglist.get(i).getAccount()+"','interest'," + interest+")");
						update.executeUpdate();
					}else {
						System.out.println("Already Added");
					}
					flag = true;
				}
				if(added) {
					JOptionPane.showMessageDialog(null, "Interest has already added!", "Action Failed!", JOptionPane.PLAIN_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "Interest added successfully", "Action Suceed", JOptionPane.PLAIN_MESSAGE);
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
			System.out.println("6");

        	break;
        case "7":
        	createAccountWindow kk = new createAccountWindow();
        	kk.launchCreateAccountWindow();
			System.out.println("7");

        	
			break;
        case "8":
        	deleteClosedAccountsAndCustomers dcaac = new deleteClosedAccountsAndCustomers();
        	dcaac.actionPerformed();
			break;
        case "9":
        	deleteTransaction dt = new deleteTransaction();
        	dt.actionPerformed();
        	System.out.println("9999999");
        	
			break;
        case "10":
        	bankTellerWindow.setVisible(false);
			System.out.println("10");

        	System.exit(0);
        	break;
		}
	}
	public String TIDGenerator(int type) {
		String result = Integer.toString(type);
		Random rand = new Random();
		for (int i = 0; i < 9; i++) {
			int rand_int = rand.nextInt(10);
			result += Integer.toString(rand_int);
		}

		return result;
	}
}