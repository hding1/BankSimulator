package GUI.windows;

import java.awt.BorderLayout;


import java.awt.Component;
import java.awt.Dimension;

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

import GUI.monitor.SelectMonitor;
import GUI.monitor.SignUPButtonMonitor;

public class SignUPWindow extends JFrame{
	private JTextField userid;
	private JPasswordField password;	
	private JTextField pname;
	private JTextField bname;
	private String type;

	

	
	
	public void launchSignUPWindow(){
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
		this.setTitle("Sign Up");
		this.setSize(300,280);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		SignUPButtonMonitor subm = new SignUPButtonMonitor(this);
		
		JLabel userLabel = new JLabel("ID:         ");
		JLabel pwdLabel = new JLabel("Password:         ");
		JLabel userName = new JLabel("Your Name:         ");
		JLabel branchName = new JLabel("Bank Name:         ");
		this.add(userLabel);
		Dimension dim = new Dimension(160, 20);
		this.userid = new JTextField();
		this.userid.setPreferredSize(dim);
		this.add(userLabel);
		this.add(this.userid);
		this.password = new JPasswordField();
		this.password.setPreferredSize(dim);
		password.addKeyListener(new java.awt.event.KeyAdapter() {
		    public void keyTyped(java.awt.event.KeyEvent e) { 
		        if (password.getText().length() >= 4 ) 
		            e.consume(); 
		    }  
		});
		this.add(pwdLabel);
		this.add(this.password);
		this.pname = new JTextField();
		this.pname.setPreferredSize(dim);
		this.add(userName);
		this.add(this.pname);
		this.bname = new JTextField();
		this.bname.setPreferredSize(dim);
		this.add(branchName);
		this.add(this.bname);
		
		
		JLabel TypeName = new JLabel("Select your account type below ");
		this.add(TypeName);
		String[] TypeString = { "Student_check", "Interest_check", "Saving", "Pocket"};
		final JComboBox<String> TypeList = new JComboBox<>(TypeString);
		this.getContentPane().add(TypeList);
		TypeList.addActionListener(new MyJcomboboxListener(this, TypeList));
		
		JButton Button1 = new JButton("Register");
		Button1.setActionCommand("1");
		Button1.setAlignmentX(Component.CENTER_ALIGNMENT);
		Button1.setHorizontalAlignment(SwingConstants.CENTER);
		Button1.setMinimumSize(new Dimension(100,20));
		Button1.setMaximumSize(new Dimension(150,30));
		Button1.addActionListener(new MyButton1Listener(this) );
		this.getContentPane().add(Button1);

		
		
		this.setVisible(true);
	}
	
	
	public class MyButton1Listener implements ActionListener{
		SignUPWindow supw;
		
    	final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    	final String DB_URL = "jdbc:oracle:thin:@cloud-34-133.eci.ucsb.edu:1521:XE";

    	final String USERNAME = "fliang";
    	final String PASSWORD = "123455";
    	
		Connection conn = null;
		Statement stmt = null;
		
		
		MyButton1Listener(SignUPWindow supw){
			this.supw = supw;
		}
	
        public void actionPerformed(ActionEvent a){
        	
        	System.out.println(this.supw.type);
        	
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
  		      insert(conn);

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

      

        	
        	this.supw.setVisible(false);
			loginWindow lw = new loginWindow();
			lw.launchLoginWindow();
        }
	
	
        public void createTable(Connection conn) throws Exception {
        	try {
        		PreparedStatement create = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Account( Aid CHAR(10), Pin CHAR(4), PrimaryOwner CHAR(15), Amount FLOAT, Branch CHAR(11), Open CHAR(1), PRIMARY KEY(Aid));");
        		create.executeUpdate();	
        		System.out.println("Table Created");
        	}catch(Exception e){
        		System.out.println(e);
        	}finally {
        		System.out.println("Function completed");
        	}
        }
        
        public void insert(Connection conn) throws Exception {
        	String new_id = this.supw.userid.getText();
        	String new_pin = String.valueOf(this.supw.password.getPassword());
        	String new_name = this.supw.pname.getText();
        	String new_branch = this.supw.bname.getText();
        	String initialAmount = "0";
        	String initialStatus = "1";
        	String type1 = this.supw.type;
        	
        	switch(type1) {
        	
        		case "Student_check":
        			try {
        				PreparedStatement insert = conn.prepareStatement("INSERT INTO Account (Aid, Pin, PrimaryOwner, Amount, Branch, Open) VALUES ('" + new_id + "', '" + new_pin + "', '" + new_name + "', '" + initialAmount + "', '" + new_branch + "', '" + initialStatus + "')");
        		
        				insert.executeUpdate();
        				System.out.println("Insert completed!");
        		
        			} catch(Exception e) {System.out.println(e);}
        			finally {
        				System.out.println("funcion completed!");
        			}
        			break;
        			
        		case "Interest_check":
        			try {
        				PreparedStatement insert = conn.prepareStatement("INSERT INTO Account (Aid, Pin, PrimaryOwner, Amount, Branch, Open) VALUES ('" + new_id + "', '" + new_pin + "', '" + new_name + "', '" + initialAmount + "', '" + new_branch + "', '" + initialStatus + "')");
        		
        				insert.executeUpdate();
        				System.out.println("Insert completed!");
        		
        			} catch(Exception e) {System.out.println(e);}
        			finally {
        				System.out.println("funcion completed!");
        			}
        			break;
        			
        		case "Saving":
        			try {
        				PreparedStatement insert = conn.prepareStatement("INSERT INTO Account (Aid, Pin, PrimaryOwner, Amount, Branch, Open) VALUES ('" + new_id + "', '" + new_pin + "', '" + new_name + "', '" + initialAmount + "', '" + new_branch + "', '" + initialStatus + "')");
        		
        				insert.executeUpdate();
        				System.out.println("Insert completed!");
        		
        			} catch(Exception e) {System.out.println(e);}
        			finally {
        				System.out.println("funcion completed!");
        			}
        			break;
        			
        		case "Pocket":
        			try {
        				PreparedStatement insert = conn.prepareStatement("INSERT INTO Account (Aid, Pin, PrimaryOwner, Amount, Branch, Open) VALUES ('" + new_id + "', '" + new_pin + "', '" + new_name + "', '" + initialAmount + "', '" + new_branch + "', '" + initialStatus + "')");
        		
        				insert.executeUpdate();
        				System.out.println("Insert completed!");
        		
        			} catch(Exception e) {System.out.println(e);}
        			finally {
        				System.out.println("funcion completed!");
        			}
        			break;
        	}
        	
        }
	}
        
        public class MyJcomboboxListener extends JFrame implements ActionListener {
        	private SignUPWindow suw;
        	private JComboBox<String> TypeList;
        	
        	MyJcomboboxListener(SignUPWindow suw, JComboBox<String> TypeList){
        		this.suw = suw;
        		this.TypeList = TypeList;
        	}
        	
        	
        	public void actionPerformed(ActionEvent a){  
				String s = (String) TypeList.getSelectedItem();

				switch (s) {
				case "Student_check":
					System.out.println("selected Student_check");
					type = "Student_check";
					break;
				case "Interest_check":
					System.out.println("selected Interest_check");
					type = "Interest_check";
					break;
				case "Saving":
					System.out.println("selected Saving");
					type = "Saving";
					break;
				case "Pocket":
					System.out.println("selected Pocket");
					type = "Pocket";
					JLabel LinkedID = new JLabel("Your Linked Account ID:         ");
					this.suw.add(LinkedID);
					JButton Button1 = new JButton("aaaaa");
					Button1.setActionCommand("1");
					Button1.setAlignmentX(Component.CENTER_ALIGNMENT);
					Button1.setHorizontalAlignment(SwingConstants.CENTER);
					Button1.setMinimumSize(new Dimension(100,20));
					Button1.setMaximumSize(new Dimension(150,30));
					this.getContentPane().add(Button1);
					

					
					break;

				}
        	}
        }
	
	
	
	
}
