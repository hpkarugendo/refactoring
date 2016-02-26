package logic;

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

public class Builder{
	
	public Builder() {
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
		if(StaticO.f == null){
			StaticO.f = new JFrame("User Type");
			StaticO.f.setSize(400, 300);
			StaticO.f.setLocationRelativeTo(null);
			StaticO.f.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) {
					System.exit(0);
				}
			});
		JPanel userTypePanel = new JPanel();
		// final ButtonGroup userType = new ButtonGroup();
		JRadioButton radioButton;
		userTypePanel.add(radioButton = new JRadioButton("Existing Customer"));
		radioButton.setActionCommand("Customer");
		StaticO.userType.add(radioButton);

		userTypePanel.add(radioButton = new JRadioButton("Administrator"));
		radioButton.setActionCommand("Administrator");
		StaticO.userType.add(radioButton);

		userTypePanel.add(radioButton = new JRadioButton("New Customer"));
		radioButton.setActionCommand("New Customer");
		StaticO.userType.add(radioButton);

		JPanel continuePanel = new JPanel();
		JButton continueButton = new JButton("Continue");
		continuePanel.add(continueButton);

		Container content = StaticO.f.getContentPane();
		content.setLayout(new GridLayout(2, 1));
		content.add(userTypePanel);
		content.add(continuePanel);

		// Created custom ActionListener Class at bottom
		continueButton.addActionListener(new MyListener());
		StaticO.f.setVisible(true);
		}
	}
	
	public void adminFrame(){
		
	}
	
	public void newCustFrame(){		
		if(StaticO.f1 == null){
			StaticO.f1 = new JFrame("Create New Customer");
			StaticO.f1.setSize(400, 300);
			StaticO.f1.setLocationRelativeTo(null);
			StaticO.f1.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) { System.exit(0); }
			});
				Container content = StaticO.f1.getContentPane();
				content.setLayout(new BorderLayout());
				JPanel panel = new JPanel(new GridLayout(6, 2));
				panel.add(StaticO.firstNameLabel);
				panel.add(StaticO.firstNameTextField);
				panel.add(StaticO.surnameLabel);
				panel.add(StaticO.surnameTextField);
				panel.add(StaticO.pPPSLabel);
				panel.add(StaticO.pPSTextField);
				panel.add(StaticO.dOBLabel);
				panel.add(StaticO.dOBTextField);
				StaticO.add.setActionCommand("Add Cus");
				StaticO.add.addActionListener(new MyListener());
				StaticO.cancel.setActionCommand("Cancel Add Cus");
				StaticO.cancel.addActionListener(new MyListener());
				StaticO.panel2.add(StaticO.add);
				StaticO.panel2.add(StaticO.cancel);
				content.add(panel, BorderLayout.CENTER);
				content.add(StaticO.panel2, BorderLayout.SOUTH);
		}
			StaticO.f1.setVisible(true);		
	}
	
	public void oldCustFrame(){
		
	}
}
