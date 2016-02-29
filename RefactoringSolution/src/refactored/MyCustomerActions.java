package refactored;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import entities.AccountTransaction;
import entities.Customer;
import entities.CustomerAccount;
import entities.CustomerCurrentAccount;

public class MyCustomerActions {
	MyGenerator g = new MyGenerator();
	MyMenu b = new MyMenu();
	MyChecks mc = new MyChecks();
	
	public void addNewCustomer(){
		MyStats.PPS = MyStats.pPSTextField.getText();
		MyStats.firstName = MyStats.firstNameTextField.getText();
		MyStats.surname = MyStats.surnameTextField.getText();
		MyStats.DOB = MyStats.dOBTextField.getText();
		MyStats.password = g.getPassword(MyStats.firstName, MyStats.surname);
		MyStats.CustomerID = String.valueOf(g.getId());
		List<CustomerAccount> accounts = new ArrayList<CustomerAccount> ();
		Customer customer = new Customer(MyStats.PPS, MyStats.surname, MyStats.firstName, MyStats.DOB, MyStats.CustomerID, MyStats.password, accounts);
		MyStats.customerList.add(customer);
		b.toast("Customer created.\nCustomerID = " + MyStats.CustomerID +"\n Password = " + MyStats.password);
		b.mainFrame();
		MyStats.f1.setVisible(false);
	}
	
	public void transactions(){
		MyStats.fCustomer = new JFrame("Customer Menu");
		MyStats.fCustomer.setSize(400, 300);
		MyStats.fCustomer.setLocation(200, 200);
		MyStats.fCustomer.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		MyStats.fCustomer.setVisible(true);

		if (MyStats.customer.getAccounts().size() == 0) {
			b.alert(MyStats.fCustomer,
					"This customer does not have any accounts yet. \n An admin must create an account for this customer \n for them to be able to use customer functionality. ",
					"Oops!");
			MyStats.fCustomer.setVisible(false);
			b.mainFrame();
		} else {
			JPanel buttonPanel = new JPanel();
			JPanel boxPanel = new JPanel();
			JPanel labelPanel = new JPanel();
			MyStats.exitButtonC.setActionCommand("exitC");
			MyStats.continueButtonC.setActionCommand("continueC");

			JLabel label = new JLabel("Select Account:");
			labelPanel.add(label);
			buttonPanel.add(MyStats.exitButtonC);
			buttonPanel.add(MyStats.continueButtonC);

			JComboBox<String> box = new JComboBox<String>();
			for (int i = 0; i < MyStats.customer.getAccounts().size(); i++) {
				box.addItem(MyStats.customer.getAccounts().get(i).getNumber());
			}

			for (int i = 0; i < MyStats.customer.getAccounts().size(); i++) {
				if (MyStats.customer.getAccounts().get(i).getNumber() == box.getSelectedItem()) {
					MyStats.acc = MyStats.customer.getAccounts().get(i);
				}
			}

			boxPanel.add(box);
			MyStats.content = MyStats.fCustomer.getContentPane();
			MyStats.content.setLayout(new GridLayout(3, 1));
			MyStats.content.add(labelPanel);
			MyStats.content.add(boxPanel);
			MyStats.content.add(buttonPanel);

			MyStats.exitButtonC.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					MyStats.fCustomer.setVisible(false);
					b.mainFrame();
				}
			});

			MyStats.continueButtonC.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {

					MyStats.fCustomer = null;

					MyStats.fCustomer = new JFrame("Customer Menu");
					MyStats.fCustomer.setSize(400, 300);
					MyStats.fCustomer.setLocation(200, 200);
					MyStats.fCustomer.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent we) {
							System.exit(0);
						}
					});
					MyStats.fCustomer.setVisible(true);

					MyStats.statementButtonC.setActionCommand("statementsC");
					MyStats.withdrawButtonC.setActionCommand("withdrawsC");
					MyStats.lodgementButtonC.setActionCommand("lodgesC");
					MyStats.returnButtonC.setActionCommand("returnsC");

					JPanel statementPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
					MyStats.statementButtonC.setPreferredSize(new Dimension(250, 20));

					statementPanel.add(MyStats.statementButtonC);

					JPanel lodgementPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
					lodgementPanel.add(MyStats.lodgementButtonC);
					MyStats.lodgementButtonC.setPreferredSize(new Dimension(250, 20));

					JPanel withdrawalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
					withdrawalPanel.add(MyStats.withdrawButtonC);
					MyStats.withdrawButtonC.setPreferredSize(new Dimension(250, 20));

					JPanel returnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
					returnPanel.add(MyStats.exitButtonC);

					JLabel label1 = new JLabel("Please select an option");

					MyStats.content = MyStats.fCustomer.getContentPane();
					MyStats.content.setLayout(new GridLayout(5, 1));
					MyStats.content.add(label1);
					MyStats.content.add(statementPanel);
					MyStats.content.add(lodgementPanel);
					MyStats.content.add(withdrawalPanel);
					MyStats.content.add(returnPanel);

					MyStats.statementButtonC.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							statement();
						}
					});

					MyStats.lodgementButtonC.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							lodge();
						}
					});

					MyStats.withdrawButtonC.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							withdraw();
						}
					});

					MyStats.exitButtonC.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							MyStats.fCustomer.setVisible(false);
							b.mainFrame();
						}
					});
				}
			});
		}
	}
	
	public void statement(){
		MyStats.fCustomer = null;
		MyStats.fCustomer = new JFrame("Customer Menu");
		MyStats.fCustomer.setSize(400, 600);
		MyStats.fCustomer.setLocation(200, 200);
		MyStats.fCustomer.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		MyStats.fCustomer.setVisible(true);

		JLabel label1 = new JLabel("Summary of account transactions: ");

		JPanel returnPanel = new JPanel();
		returnPanel.add(MyStats.returnButtonC);

		JPanel textPanel = new JPanel();

		textPanel.setLayout(new BorderLayout());
		JTextArea textArea = new JTextArea(40, 20);
		textArea.setEditable(false);
		textPanel.add(label1, BorderLayout.NORTH);
		textPanel.add(textArea, BorderLayout.CENTER);
		textPanel.add(MyStats.returnButtonC, BorderLayout.SOUTH);

		JScrollPane scrollPane = new JScrollPane(textArea);
		textPanel.add(scrollPane);

		for (int i = 0; i < MyStats.acc.getTransactionList().size(); i++) {
			textArea.append(MyStats.acc.getTransactionList().get(i).toString());

		}

		textPanel.add(textArea);
		MyStats.content.removeAll();

		Container content = MyStats.fCustomer.getContentPane();
		content.setLayout(new GridLayout(1, 1));
		// content.add(label1);
		content.add(textPanel);
		// content.add(returnPanel);

		MyStats.returnButtonC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				MyStats.fCustomer = null;
				transactions();
			}
		});
	}
	
	public void lodge(){
		MyStats.loop = true;
		MyStats.on = true;
		MyStats.balanceC = 0;

		if (MyStats.acc instanceof CustomerCurrentAccount) {
			int count = 3;
			int checkPin = (int) MyStats.acc.getAtm().getPin();
			MyStats.loop = true;

			while (MyStats.loop) {
				if (count == 0) {
					b.alert(MyStats.fCustomer,"Pin entered incorrectly 3 times. ATM card locked.", "Pin");
					((CustomerCurrentAccount) MyStats.acc).getAtm().setValid(false);
					transactions();
					MyStats.loop = false;
					MyStats.on = false;
				}

				String Pin = JOptionPane.showInputDialog(MyStats.fCustomer, "Enter 4 digit PIN;");
				int i = Integer.parseInt(Pin);

				if (MyStats.on) {
					if (checkPin == i) {
						MyStats.loop = false;
						b.alert(MyStats.fCustomer, "Pin entry successful", "Pin");

					} else {
						count--;
						b.alert(MyStats.fCustomer,"Incorrect pin. " + count + " attempts remaining.", "Pin");
					}

				}
			}

		}
		if (MyStats.on == true) {
			String balanceTest = JOptionPane.showInputDialog(MyStats.fCustomer, "Enter amount you wish to lodge:");
			// the isNumeric method tests to see if the string entered was
			// numeric.
			if (mc.isMoney(balanceTest)) {

				MyStats.balanceC = Double.parseDouble(balanceTest);
				MyStats.loop = false;

			} else {
				b.alert(MyStats.fCustomer, "You must enter a numerical value!", "Oops!");
			}

			MyStats.acc.setBalance(MyStats.acc.getBalance() + MyStats.balanceC);
			// String date = new
			// SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			Date date = new Date();
			String date2 = date.toString();
			String type = "Lodgement";
			double amount = MyStats.balanceC;

			AccountTransaction transaction = new AccountTransaction(date2, type, amount);
			MyStats.acc.getTransactionList().add(transaction);

			b.alert(MyStats.fCustomer, mc.formatMoney(MyStats.balanceC) + " added do you account!",
					"Lodgement");
			b.alert(MyStats.fCustomer, "New balance = " + mc.formatMoney(MyStats.acc.getBalance()),
					"Lodgement");
		}
	}
	
	public void withdraw(){
		MyStats.loop = true;
		MyStats.on = true;
		MyStats.withdrawC = 0;

		if (MyStats.acc instanceof CustomerCurrentAccount) {
			int count = 3;
			int checkPin = ((CustomerCurrentAccount) MyStats.acc).getAtm().getPin();
			MyStats.loop = true;

			while (MyStats.loop) {
				if (count == 0) {
					b.alert(MyStats.fCustomer,"Pin entered incorrectly 3 times. ATM card locked.", "Pin");
					((CustomerCurrentAccount) MyStats.acc).getAtm().setValid(false);
					transactions();
					MyStats.loop = false;
					MyStats.on = false;
				}

				String Pin = JOptionPane.showInputDialog(MyStats.fCustomer, "Enter 4 digit PIN;");
				int i = Integer.parseInt(Pin);

				if (MyStats.on) {
					if (checkPin == i) {
						MyStats.loop = false;
						b.alert(MyStats.fCustomer, "Pin entry successful", "Pin");

					} else {
						count--;
						b.alert(MyStats.fCustomer,"Incorrect pin. " + count + " attempts remaining.", "Pin");

					}

				}
			}

		}
		if (MyStats.on == true) {
			String balanceTest = JOptionPane.showInputDialog(MyStats.fCustomer,
					"Enter amount you wish to withdraw (max 500):");
			// the isNumeric method tests to see if the string entered was
			// numeric.
			if (mc.isMoney(balanceTest)) {

				MyStats.withdrawC = Double.parseDouble(balanceTest);
				MyStats.loop = false;

			} else {
				b.alert(MyStats.fCustomer, "You must enter a numerical value!", "Oops!");
			}
			if (MyStats.withdrawC > 500) {
				b.alert(MyStats.fCustomer, "500 is the maximum you can withdraw at a time.", "Notice");
				MyStats.withdrawC = 0;
			}
			if (MyStats.withdrawC > MyStats.acc.getBalance()) {
				b.alert(MyStats.fCustomer, "Insufficient funds.", "Oops!");
				MyStats.withdrawC = 0;
			}

			String euro = "\u20ac";
			MyStats.acc.setBalance(MyStats.acc.getBalance() - MyStats.withdrawC);
			// recording transaction:
			// String date = new
			// SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			Date date = new Date();
			String date2 = date.toString();

			String type = "Withdraw";
			double amount = MyStats.withdrawC;

			AccountTransaction transaction = new AccountTransaction(date2, type, amount);
			MyStats.acc.getTransactionList().add(transaction);

			b.alert(MyStats.fCustomer, mc.formatMoney(MyStats.withdrawC) + " withdrawn.", "Withdraw");
			b.alert(MyStats.fCustomer, "New balance = " + mc.formatMoney(MyStats.acc.getBalance()),"Withdraw");
		}
	}
}
