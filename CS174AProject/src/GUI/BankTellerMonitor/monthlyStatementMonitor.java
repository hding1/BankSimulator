package GUI.BankTellerMonitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import GUI.BankTellerWindow.*;

public class monthlyStatementMonitor implements ActionListener{
	private monthlyStatementWindow msw;

	public monthlyStatementMonitor(monthlyStatementWindow msw){
		this.msw = msw;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		this.msw.setVisible(false);
	}
}
