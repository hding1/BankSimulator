package GUI.BankTellerMonitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import GUI.BankTellerWindow.*;

public class createCheckMonitor implements ActionListener {
	private enterCheckTransactionWindow ectw;
	
	public createCheckMonitor(enterCheckTransactionWindow ectw){
		this.ectw = ectw;
	}
	
	public void actionPerformed(ActionEvent e) {
		this.ectw.setVisible(false);
		submitCheckWindow scw = new submitCheckWindow();
		scw.launchSubmitCheckWindow(this.ectw.getCheckCustomerName());
		scw.setVisible(true);
		
	}
}
