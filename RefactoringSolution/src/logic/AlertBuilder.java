package logic;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AlertBuilder {
	
	public AlertBuilder() {
	}
	
	public void toast(String message){
		JOptionPane.showMessageDialog(null, message);
	}
	
	public int confirm(String question){
		int reply = JOptionPane.showConfirmDialog(null, question, "Confirmation",JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
			return 1;
		} else if (reply == JOptionPane.NO_OPTION) {
			return 2;
		}
		else {
			return 0;
		}
	}
	
	public Object input(JFrame frame, String question){
		Object obj = JOptionPane.showInputDialog(frame, question);
		return obj;
	}
	
	public void alert(JFrame f, String message, String title) {
		JOptionPane.showMessageDialog(f, message, title,
				JOptionPane.INFORMATION_MESSAGE);
	}
}
