package GUI.monitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import GUI.windows.SelectWindow;
import GUI.windows.TDWindow;
import GUI.windows.TWWindow;


public class SelectMonitor  implements ActionListener{
	private SelectWindow selectwindow;
	public SelectMonitor(SelectWindow selectwindow) {
		this.selectwindow = selectwindow;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		TDWindow window1;
		TWWindow window2;
		int command = Integer.parseInt(e.getActionCommand());
		switch (command) {
		case 1:
			String status = "";
			if(selectwindow.getAccount().getStatus()=='1') {
				status = "open";
			}else {
				status = "close";
			}
			JOptionPane.showMessageDialog(this.selectwindow, 
					"Account ID: "+this.selectwindow.getAccount().getAccount()+
					"\nBalance: "+this.selectwindow.getAccount().getAmount()+
					"\nBranch: "+this.selectwindow.getAccount().getBranch()+
					"\nPrimary Owner TID: "+this.selectwindow.getAccount().getPname()+
					"\nStatus: "+status, "Account Info",JOptionPane.PLAIN_MESSAGE);
			break;
		case 2:
			this.selectwindow.setVisible(false);
			window1 = new TDWindow(this.selectwindow.getCustomer(),this.selectwindow.getAccount(),1);
			window1.launchWindow();
			break;
		case 3:
			this.selectwindow.setVisible(false);
			window1 = new TDWindow(this.selectwindow.getCustomer(),this.selectwindow.getAccount(),2);
			window1.launchWindow();
			break;
		case 4:
			this.selectwindow.setVisible(false);
			window2 = new TWWindow(this.selectwindow.getCustomer(),this.selectwindow.getAccount(),1);
			window2.launchWindow();
			break;
        case 5:
        	this.selectwindow.setVisible(false);
        	window2 = new TWWindow(this.selectwindow.getCustomer(),this.selectwindow.getAccount(),2);
			window2.launchWindow();
			break;
        case 6:
        	selectwindow.setVisible(false);
        	System.exit(0);
	       break;
		}
	}

}
