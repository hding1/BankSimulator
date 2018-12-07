package GUI.BankTellerMonitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import GUI.BankTellerWindow.*;

public class DTERWindowMonitor implements ActionListener{
	private DTERWindow dw;
	
	public DTERWindowMonitor(DTERWindow dw) {
		this.dw = dw;
	}
	public void actionPerformed(ActionEvent e) {
		this.dw.setVisible(false);

	}
}
