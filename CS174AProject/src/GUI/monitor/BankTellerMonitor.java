package GUI.monitor;

import java.awt.event.ActionListener;

import GUI.windows.BankTellerWindow;

import java.awt.event.ActionEvent;

public class BankTellerMonitor implements ActionListener{
	private BankTellerWindow bankTellerWindow;
	
	public BankTellerMonitor(BankTellerWindow bankTellerWindow) {
		this.bankTellerWindow = bankTellerWindow;
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		int command = Integer.parseInt(e.getActionCommand());
		switch (command) {
		case 1:
			
			break;
		case 2:
			
			break;
		case 3:
			
			break;
		case 4:
			
			break;
        case 5:
        	
			break;
        case 6:

        	break;
        case 7:
        	
			break;
        case 8:
        	
			break;
        case 9:
        	
			break;
        case 10:
        	bankTellerWindow.setVisible(false);
        	System.exit(0);
        	break;
		}
	}
}
