package refactored;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class MyMain{
	
	public MyMain() {
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
	
	public void mainFrame() {
		if(MyStats.f == null){
			MyStats.f = new JFrame("User Type");
			MyStats.f.setSize(400, 300);
			MyStats.f.setLocationRelativeTo(null);
			MyStats.f.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) {
					System.exit(0);
				}
			});
		JPanel userTypePanel = new JPanel();
		// final ButtonGroup userType = new ButtonGroup();
		JRadioButton radioButton;
		userTypePanel.add(radioButton = new JRadioButton("Existing Customer"));
		radioButton.setActionCommand("Customer");
		MyStats.userType.add(radioButton);

		userTypePanel.add(radioButton = new JRadioButton("Administrator"));
		radioButton.setActionCommand("Administrator");
		MyStats.userType.add(radioButton);

		userTypePanel.add(radioButton = new JRadioButton("New Customer"));
		radioButton.setActionCommand("New Customer");
		MyStats.userType.add(radioButton);

		JPanel continuePanel = new JPanel();
		JButton continueButton = new JButton("Continue");
		continuePanel.add(continueButton);

		Container content = MyStats.f.getContentPane();
		content.setLayout(new GridLayout(2, 1));
		content.add(userTypePanel);
		content.add(continuePanel);

		// Created custom ActionListener Class at bottom
		continueButton.addActionListener(new MyListener());
		MyStats.f.setVisible(true);
		}
	}
	
	public void adminFrame(){
		
	}
	
	public void newCustFrame(){		
		if(MyStats.f1 == null){
			MyStats.f1 = new JFrame("Create New Customer");
			MyStats.f1.setSize(400, 300);
			MyStats.f1.setLocationRelativeTo(null);
			MyStats.f1.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) { System.exit(0); }
			});
				Container content = MyStats.f1.getContentPane();
				content.setLayout(new BorderLayout());
				JPanel panel = new JPanel(new GridLayout(6, 2));
				panel.add(MyStats.firstNameLabel);
				panel.add(MyStats.firstNameTextField);
				panel.add(MyStats.surnameLabel);
				panel.add(MyStats.surnameTextField);
				panel.add(MyStats.pPPSLabel);
				panel.add(MyStats.pPSTextField);
				panel.add(MyStats.dOBLabel);
				panel.add(MyStats.dOBTextField);
				MyStats.add.setActionCommand("Add Cus");
				MyStats.add.addActionListener(new MyListener());
				MyStats.cancel.setActionCommand("Cancel Add Cus");
				MyStats.cancel.addActionListener(new MyListener());
				MyStats.panel2.add(MyStats.add);
				MyStats.panel2.add(MyStats.cancel);
				content.add(panel, BorderLayout.CENTER);
				content.add(MyStats.panel2, BorderLayout.SOUTH);
		}
			MyStats.f1.setVisible(true);		
	}
	
	public void oldCustFrame(){
		
	}
}
