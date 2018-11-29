package GUI.windows;

import java.awt.BorderLayout;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;

import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import java.sql.*;
import java.text.NumberFormat;

import GUI.monitor.SelectMonitor;
import GUI.monitor.SignUPButtonMonitor;

public class SignUPWindow extends JFrame{
	private JTextField userid;
	private IntTextField password;	
	private JTextField pname;
	private JTextField address;
	private JTextField branch;
	private String type;
	private JFormattedTextField amount;
	private String[] TypeString = { "Student_check", "Interest_check", "Saving"};
	private final JComboBox<String> TypeList = new JComboBox<>(TypeString);

	public void launchSignUPWindow(){
//		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
		this.setLayout(new FlowLayout());
		this.setTitle("Sign Up");
		this.setSize(240,260);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		SignUPButtonMonitor subm = new SignUPButtonMonitor(this);
		
		JLabel userLabel = new JLabel("Tax ID:          ");
		JLabel pwdLabel = new JLabel("PIN:               ");
		JLabel userName = new JLabel("Your Name:   ");
		JLabel addressName = new JLabel("Address:        ");
		JLabel firstAccount = new JLabel("----Create Your First Account----");
		JLabel branchName = new JLabel("Branch:          ");
		JLabel depositAmount = new JLabel("Amount:        ");
		JLabel typeName = new JLabel("Type:             ");
		
		Dimension dim = new Dimension(120, 20);
		//Tax ID
		this.userid = new JTextField();
		this.userid.setPreferredSize(dim);
		this.add(userLabel);
		this.add(this.userid);
		
		//PIN
		this.password = new IntTextField();
		this.password.setPreferredSize(dim);
		password.addKeyListener(new java.awt.event.KeyAdapter() {
		    public void keyTyped(java.awt.event.KeyEvent e) { 
		        if (password.getText().length() >= 4 ) 
		            e.consume(); 
		    }  
		});
		this.add(pwdLabel);
		this.add(this.password);
		
		//Name
		this.pname = new JTextField();
		this.pname.setPreferredSize(dim);
		this.add(userName);
		this.add(this.pname);
		
		//address
		this.address = new JTextField();
		this.address.setPreferredSize(dim);
		this.add(addressName);
		this.add(this.address);
		
		this.add(firstAccount);
		//branch
		this.branch = new JTextField();
		this.branch.setPreferredSize(dim);
		this.add(branchName);
		this.add(this.branch);
		
		//amount
		this.amount = new JFormattedTextField();
		amount.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
		amount.getDocument().addDocumentListener(new DocumentListener() {
		    @Override
		    public void insertUpdate(DocumentEvent e) {
		        Runnable format = new Runnable() {
		            @Override
		            public void run() {
		                String text = amount.getText();
		                if(!text.matches("\\d*(\\.\\d{0,2})?")){
		                    amount.setText(text.substring(0,text.length()-1));
		                }
		            }
		        };
		        SwingUtilities.invokeLater(format);
		    }

		    @Override
		    public void removeUpdate(DocumentEvent e) {

		    }

		    @Override
		    public void changedUpdate(DocumentEvent e) {

		    }
		});
		this.amount.setPreferredSize(dim);
		this.add(depositAmount);
		this.add(this.amount);
		
		//Type
		TypeList.setPreferredSize(dim);
		this.add(typeName);
		this.getContentPane().add(TypeList);
		
		//Register Button
		JButton Button1 = new JButton("Register");
		Button1.setActionCommand("1");
		Button1.setAlignmentX(Component.CENTER_ALIGNMENT);
		Button1.setHorizontalAlignment(SwingConstants.CENTER);
		Button1.setMinimumSize(new Dimension(100,20));
		Button1.setMaximumSize(new Dimension(150,30));
		Button1.addActionListener(subm);
		this.getContentPane().add(Button1);


		this.setVisible(true);
	}
	public String getUserID() {
		return this.userid.getText();
	}
	public String getPIN() {
		return this.password.getText();
	}
	public String getPname() {
		return this.pname.getText();
	}
	public String getaddress() {
		return this.address.getText();
	}
	public String getBranch() {
		return this.branch.getText();
	}
	public float getAmount() {
		float myAmount = 0;
		try {
			myAmount = Float.parseFloat(this.amount.getText());
		}catch (NumberFormatException e) {
			System.out.print("Incorrect Format!");
		}
		return myAmount;
	}
	public String getAccountType(){
		return type;
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

			}
    	}
    }
}

class IntTextField extends JPasswordField {
	  public IntTextField() {
	    super();
	  }

	  protected Document createDefaultModel() {
	    return new IntTextDocument();
	  }

//	  public boolean isValid() {
//	    try {
//	      Integer.parseInt(this.getText());
//	      return true;
//	    } catch (NumberFormatException e) {
//	      return false;
//	    }
//	  }

	  public int getValue() {
	    try {
	      return Integer.parseInt(getText());
	    } catch (NumberFormatException e) {
	      return 0;
	    }
	  }
	  class IntTextDocument extends PlainDocument {
	    public void insertString(int offs, String str, AttributeSet a)
	        throws BadLocationException {
	      if (str == null)
	        return;
	      String oldString = getText(0, getLength());
	      String newString = oldString.substring(0, offs) + str
	          + oldString.substring(offs);
	      try {
	        Integer.parseInt(newString + "0");
	        super.insertString(offs, str, a);
	      } catch (NumberFormatException e) {
	      }
	    }
	  }
}
