package GUI.monitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import GUI.windows.PocketWindow;
import GUI.windows.SelectWindow;
import GUI.windows.TDWindow;
import GUI.windows.TWWindow;


public class PocketMonitor  implements ActionListener{
	private PocketWindow pw;
	public PocketMonitor(PocketWindow pw) {
		this.pw = pw;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		TDWindow window1;
		TWWindow window2;
		int command = Integer.parseInt(e.getActionCommand());
		switch (command) {
		case 1:
			String status = "";
			if(pw.getAccount().getStatus()=='1') {
				status = "open";
			}else {
				status = "close";
			}
			JOptionPane.showMessageDialog(this.pw, 
					"Account ID: "+this.pw.getAccount().getAccount()+
					"\nBalance: "+this.pw.getAccount().getAmount()+
					"\nBranch: "+this.pw.getAccount().getBranch()+
					"\nPrimary Owner TID: "+this.pw.getAccount().getPname()+
					"\nStatus: "+status+
					"\nLinked Account: "+this.pw.getAccount().getLinkedAccount_ID(), "Account Info",JOptionPane.PLAIN_MESSAGE);
			break;
		case 2:
			this.pw.setVisible(false);
			window1 = new TDWindow(this.pw.getCustomer(),this.pw.getAccount(),3);
			window1.launchWindow();
			break;
		case 3:
			this.pw.setVisible(false);
			window1 = new TDWindow(this.pw.getCustomer(),this.pw.getAccount(),4);
			window1.launchWindow();
			break;
		case 4:
			this.pw.setVisible(false);
			window1 = new TDWindow(this.pw.getCustomer(),this.pw.getAccount(),5);
			window1.launchWindow();
			break;
        case 5:
        	this.pw.setVisible(false);
        	window2 = new TWWindow(this.pw.getCustomer(),this.pw.getAccount(),3);
			window2.launchWindow();
			break;
        case 6:
        	pw.setVisible(false);
        	System.exit(0);
	       break;
		}
	}

}
