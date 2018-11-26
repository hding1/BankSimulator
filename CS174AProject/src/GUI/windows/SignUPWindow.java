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

import java.sql.*;

import GUI.monitor.SelectMonitor;
import GUI.monitor.SignUPButtonMonitor;

public class SignUPWindow extends JFrame{
	private JTextField userid;
	private JPasswordField password;	
	private JTextField userName;
	

	
	
	public void launchSignUPWindow(){
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
		this.setTitle("Sign Up");
		this.setSize(300,180);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		SignUPButtonMonitor subm = new SignUPButtonMonitor(this);
		
		JLabel userLabel = new JLabel("Your ID:         ");
		JLabel pwdLabel = new JLabel("Your Password:         ");
		this.add(userLabel);
		Dimension dim = new Dimension(160, 20);
		this.userid = new JTextField();
		this.userid.setPreferredSize(dim);
		this.add(this.userid);
		this.add(pwdLabel);
		this.password = new JPasswordField();
		this.password.setPreferredSize(dim);
		password.addKeyListener(new java.awt.event.KeyAdapter() {
		    public void keyTyped(java.awt.event.KeyEvent e) { 
		        if (password.getText().length() >= 4 ) 
		            e.consume(); 
		    }  
		});
		this.add(this.password);
		JLabel userName = new JLabel("Your Name:         ");
		this.add(userLabel);
		this.userName = new JTextField();
		this.userName.setPreferredSize(dim);
		this.add(this.userName);



		
		JButton Button1 = new JButton("Register");
		Button1.setActionCommand("1");
		Button1.setAlignmentX(Component.CENTER_ALIGNMENT);
		Button1.setAlignmentY(Component.CENTER_ALIGNMENT);
		Button1.setHorizontalAlignment(SwingConstants.CENTER);
		Button1.setMinimumSize(new Dimension(100,20));
		Button1.setMaximumSize(new Dimension(150,30));
		Button1.addActionListener(new MyButton1Listener(this));
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
        		PreparedStatement create = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Account(Aid INTEGER, Primary_owner CHAR(10), Amount DOUBLE, Branch CHAR(11), Open BOOLEAN, PRIMARY KEY(Aid))");
        		create.executeUpdate();	
        	}catch(Exception e){
        		System.out.println(e);
        	}finally {
        		System.out.println("Function complete.");
        	}
        }
        
        public void insert(Connection conn) throws Exception {
        	String new_ID = userid.getText();
        	//try {
        		//PreparedStatement insert = conn.prepareStatement("INSERT INTO Account (Aid, Primary_owner, Amount, Branch, Open) VALUES ()");
        	//} 
        }
	}
	
	
	
}
