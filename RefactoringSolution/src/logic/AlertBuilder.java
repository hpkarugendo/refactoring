package logic;

import javax.swing.JOptionPane;

public class AlertBuilder {
	public AlertBuilder() {
	}
	
	public void toast(String message){
		JOptionPane.showMessageDialog(null, message);
	}
}
