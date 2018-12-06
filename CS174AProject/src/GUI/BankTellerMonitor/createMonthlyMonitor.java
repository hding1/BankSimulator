package GUI.BankTellerMonitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import GUI.BankTellerWindow.*;

public class createMonthlyMonitor implements ActionListener{
	private generateMonthlyStatementWindow gmsw;
	
	public createMonthlyMonitor(generateMonthlyStatementWindow gmsw) {
		this.gmsw = gmsw;
	}
	
	public void actionPerformed(ActionEvent e) {
		this.gmsw.setVisible(false);
		monthlyStatementWindow msw = new monthlyStatementWindow();
		msw.launchMonthlyStatementWindow(this.gmsw);
	}
}
