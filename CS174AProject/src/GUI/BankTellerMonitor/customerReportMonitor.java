package GUI.BankTellerMonitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import GUI.BankTellerWindow.*;

public class customerReportMonitor implements ActionListener{
	private customerReportWindow crw;

	public customerReportMonitor(customerReportWindow crw){
		this.crw = crw;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		this.crw.setVisible(false);
	}
}
