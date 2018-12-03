package GUI.BankTellerMonitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import GUI.BankTellerWindow.*;

public class createReportMonitor implements ActionListener{
	private createReportWindow caw;
	
	public createReportMonitor(createReportWindow caw) {
		this.caw = caw;
	}
	
	public void actionPerformed(ActionEvent e) {
		this.caw.setVisible(false);
		customerReportWindow crw = new customerReportWindow(this.caw);
		crw.launchCustomerReportWindow();
		
	}

}
