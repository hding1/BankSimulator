package GUI.BankTellerMonitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import GUI.BankTellerWindow.*;

public class listClosedAccountMonitor implements ActionListener{
	private listClosedAccountWindow lcaw;
	
	public listClosedAccountMonitor(listClosedAccountWindow lcaw){
		this.lcaw = lcaw;
	}
	
	//@Override
	public void actionPerformed(ActionEvent e) {
		this.lcaw.setVisible(false);

	}
}
