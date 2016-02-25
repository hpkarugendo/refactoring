package logic;

import javax.swing.JOptionPane;

public class AlertBuilder {
	public AlertBuilder() {
	}
	
	public void toast(String message){
		JOptionPane.showMessageDialog(null, message);
	}
	
	public int ask(String question){
		int reply  = JOptionPane.showConfirmDialog(null, null, question, JOptionPane.YES_NO_OPTION);
    	return reply;
	}
}
