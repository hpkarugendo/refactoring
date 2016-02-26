package views;

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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import entities.AccountTransaction;
import entities.Customer;
import entities.CustomerAccount;
import entities.CustomerCurrentAccount;
import logic.AlertBuilder;
import logic.Generators;
import logic.Validation;

public class ViewCreator implements ActionListener{
	/*
	 * All Variables to be used
	 */
	// Elements
	JFrame f, f1, fNew, fExis, fAdmin;
	JLabel firstNameLabel, surnameLabel, pPPSLabel, dOBLabel;
	JTextField firstNameTextField, surnameTextField, pPSTextField, dOBTextField;
	JLabel customerIDLabel, passwordLabel;
	JTextField customerIDTextField, passwordTextField;
	JButton add, cancel;
	JPanel panel2;
	final ButtonGroup userType = new ButtonGroup();
	Container content;
	// Entities
	List<Customer> customerList = new ArrayList<Customer>();
	Customer e;
	CustomerAccount acc = new CustomerAccount();
	String PPS, firstName, surname, DOB, password, CustomerID, notice;
	AlertBuilder builder;
	Generators generators;
	Validation validate;
	// properties
	boolean loop = true, loop2 = true, cont = false, found = false, on = false;

	public ViewCreator() {
		super();
		builder = new AlertBuilder();
		generators = new Generators();
		validate = new Validation();
	}

	/*
	 * Create the main frame to call upon every time it is needed
	 */

	public JFrame createFrame(String name) {
		JFrame f0 = new JFrame(name);
		f0.setSize(400, 300);
		f0.setLocationRelativeTo(null);
		f0.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		return f0;
	}

	/*
	 * The Main Welcome menu
	 */
	public void menuStart() {
		/*
		 * The menuStart method asks the user if they are a new customer, an
		 * existing customer or an admin. It will then start the create customer
		 * process if they are a new customer, or will ask them to log in if
		 * they are an existing customer or admin.
		 */

		f = createFrame("User Type");

		JPanel userTypePanel = new JPanel();
		//final ButtonGroup userType = new ButtonGroup();
		JRadioButton radioButton;
		userTypePanel.add(radioButton = new JRadioButton("Existing Customer"));
		radioButton.setActionCommand("Customer");
		userType.add(radioButton);

		userTypePanel.add(radioButton = new JRadioButton("Administrator"));
		radioButton.setActionCommand("Administrator");
		userType.add(radioButton);

		userTypePanel.add(radioButton = new JRadioButton("New Customer"));
		radioButton.setActionCommand("New Customer");
		userType.add(radioButton);

		JPanel continuePanel = new JPanel();
		JButton continueButton = new JButton("Continue");
		continuePanel.add(continueButton);

		Container content = f.getContentPane();
		content.setLayout(new GridLayout(2, 1));
		content.add(userTypePanel);
		content.add(continuePanel);

		//Created custom ActionListener Class at bottom
		continueButton.addActionListener(this);
		f.setVisible(true);
	}

	/*
	 * For Creating New Customer
	 */
	public void newCustomer() {
		f.dispose();
		f1 = createFrame("Create New Customer");
		Container content = f1.getContentPane();
		content.setLayout(new BorderLayout());

		firstNameLabel = new JLabel("First Name:", SwingConstants.RIGHT);
		surnameLabel = new JLabel("Surname:", SwingConstants.RIGHT);
		pPPSLabel = new JLabel("PPS Number:", SwingConstants.RIGHT);
		dOBLabel = new JLabel("Date of birth", SwingConstants.RIGHT);
		firstNameTextField = new JTextField(20);
		surnameTextField = new JTextField(20);
		pPSTextField = new JTextField(20);
		dOBTextField = new JTextField(20);
		JPanel panel = new JPanel(new GridLayout(6, 2));
		panel.add(firstNameLabel);
		panel.add(firstNameTextField);
		panel.add(surnameLabel);
		panel.add(surnameTextField);
		panel.add(pPPSLabel);
		panel.add(pPSTextField);
		panel.add(dOBLabel);
		panel.add(dOBTextField);

		panel2 = new JPanel();
		add = new JButton("Add");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				PPS = pPSTextField.getText();
				firstName = firstNameTextField.getText();
				surname = surnameTextField.getText();
				DOB = dOBTextField.getText();
				
				//Changed from asking users for password to auto passwords and IDs
				password = generators.getPassword(firstName, surname);
				CustomerID = String.valueOf(generators.getId());

				List<CustomerAccount> accounts = new ArrayList<CustomerAccount>();
				Customer customer = new Customer(PPS, surname, firstName, DOB, CustomerID, password, accounts);

				customerList.add(customer);

//				//Replaced original JOP with this!
				String mString = "CustomerID = " + customer.getCustomerID() + "\n Password = " + customer.getPassword();
				builder.toast(mString);
				resetFields();
				menuStart();
				f.dispose();
			}
		});
		cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f1.dispose();
				menuStart();
			}
		});

		panel2.add(add);
		panel2.add(cancel);

		content.add(panel, BorderLayout.CENTER);
		content.add(panel2, BorderLayout.SOUTH);

		f1.setVisible(true);
	}

	/*
	 * For Existing Customer
	 */
	public void exisitingCustomer() {
		Customer customer = null;
		while (loop) {
			Object customerID = builder.input(f, "Enter Customer ID:");

			for (Customer aCustomer : customerList) {

				if (aCustomer.getCustomerID().equals(customerID))
					// search customer list for matching customer ID
				{
					found = true;
					customer = aCustomer;
				}
			}

			if (found == false) {
				int reply = builder.confirm("User not found. Try again?");
				if (reply == 1) {
					loop = true;
				} else if (reply == 2) {
					f.dispose();
					loop = false;
					loop2 = false;
					menuStart();
				}
			} else {
				loop = false;
			}

		}

		while (loop2) {
			Object customerPassword = builder.input(f, "Enter Customer Password;");

			if (!customer.getPassword().equals(customerPassword))
				// check if custoemr password is correct
			{
				int reply = builder.confirm("Incorrect password. Try again?");
				if (reply == 1) {

				} else if (reply == 2) {
					f.dispose();
					loop2 = false;
					menuStart();
				}
			} else {
				loop2 = false;
				cont = true;
			}
		}

		if (cont) {
			f.dispose();
			loop = false;
			customer(customer);
		}
	}

	/*
	 * For Administrators
	 */
	public void admin() {
		loop = true; loop2 = true; cont = false;
		while(loop)
	    {
	    Object adminUsername = builder.input(f, "Enter Administrator Username:");

	    if(!adminUsername.equals("admin"))//search admin list for admin with matching admin username
	    {
	    	int reply  = builder.confirm("Incorrect Username. Try again?");
	    	if (reply == 1) {
	    		loop = true;
	    	}
	    	else if(reply == 2)
	    	{
	    		f1.dispose();
	    		loop = false;
	    		loop2 = false;
	    		menuStart();
	    	}
	    }
	    else
	    {
	    	loop = false;
	    }				    
	    }
	    
	    while(loop2)
	    {
	    	Object adminPassword = builder.input(f, "Enter Administrator Password;");
	    	
	    	   if(!adminPassword.equals("admin11"))//search admin list for admin with matching admin password
			    {
			    	int reply  = builder.confirm("Incorrect Password. Try again?");
			    	if (reply == 1) {
			    		
			    	}
			    	else if(reply == 2){
			    		f1.dispose();
			    		loop2 = false;
			    		menuStart();
			    	}
			    }
	    	   else
	    	   {
	    		   loop2 =false;
	    		   cont = true;
	    	   }
	    }
	    	
	    if(cont)
	    {
		f1.dispose();
	    	loop = false;
	    admin();					    
	    }	
	}
	
	/*
	 * Transactions for Existing Customer
	 */
	public void customer(Customer e1) {
		f = createFrame("Customer Menu");
		e = e1;
		f.setVisible(true);

		if (e.getAccounts().size() == 0) {
			//Replaced the AlertDialog here with a custom one
			notice = "This customer does not have any accounts yet. \n An admin must create an account for this customer \n for them to be able to use customer functionality\nOops!";
			builder.toast(notice);
			f.dispose();
			menuStart();
		} else {
			JPanel buttonPanel = new JPanel();
			JPanel boxPanel = new JPanel();
			JPanel labelPanel = new JPanel();

			JLabel label = new JLabel("Select Account:");
			labelPanel.add(label);

			JButton returnButton = new JButton("Return");
			buttonPanel.add(returnButton);
			JButton continueButton = new JButton("Continue");
			buttonPanel.add(continueButton);

			JComboBox<String> box = new JComboBox<String>();
			for (int i = 0; i < e.getAccounts().size(); i++) {
				box.addItem(e.getAccounts().get(i).getNumber());
			}

			for (int i = 0; i < e.getAccounts().size(); i++) {
				if (e.getAccounts().get(i).getNumber() == box.getSelectedItem()) {
					acc = e.getAccounts().get(i);
				}
			}

			boxPanel.add(box);
			content = f.getContentPane();
			content.setLayout(new GridLayout(3, 1));
			content.add(labelPanel);
			content.add(boxPanel);
			content.add(buttonPanel);

			returnButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					f.dispose();
					menuStart();
				}
			});

			continueButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {

					f.dispose();

					f = createFrame("Customer Menu");
					f.setVisible(true);

					JPanel statementPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
					JButton statementButton = new JButton("Display Bank Statement");
					statementButton.setPreferredSize(new Dimension(250, 20));

					statementPanel.add(statementButton);

					JPanel lodgementPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
					JButton lodgementButton = new JButton("Lodge money into account");
					lodgementPanel.add(lodgementButton);
					lodgementButton.setPreferredSize(new Dimension(250, 20));

					JPanel withdrawalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
					JButton withdrawButton = new JButton("Withdraw money from account");
					withdrawalPanel.add(withdrawButton);
					withdrawButton.setPreferredSize(new Dimension(250, 20));

					JPanel returnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
					JButton returnButton = new JButton("Exit Customer Menu");
					returnPanel.add(returnButton);

					JLabel label1 = new JLabel("Please select an option");

					content = f.getContentPane();
					content.setLayout(new GridLayout(5, 1));
					content.add(label1);
					content.add(statementPanel);
					content.add(lodgementPanel);
					content.add(withdrawalPanel);
					content.add(returnPanel);

					statementButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							f.dispose();
							
							//Replaced new frame creation code
							f = createFrame("Customer Menu");
							f.setVisible(true);

							JLabel label1 = new JLabel("Summary of account transactions: ");

							JPanel returnPanel = new JPanel();
							JButton returnButton = new JButton("Return");
							returnPanel.add(returnButton);

							JPanel textPanel = new JPanel();

							textPanel.setLayout(new BorderLayout());
							JTextArea textArea = new JTextArea(40, 20);
							textArea.setEditable(false);
							textPanel.add(label1, BorderLayout.NORTH);
							textPanel.add(textArea, BorderLayout.CENTER);
							textPanel.add(returnButton, BorderLayout.SOUTH);

							JScrollPane scrollPane = new JScrollPane(textArea);
							textPanel.add(scrollPane);

							for (int i = 0; i < acc.getTransactionList().size(); i++) {
								textArea.append(acc.getTransactionList().get(i).toString());

							}

							textPanel.add(textArea);
							content.removeAll();

							Container content = f.getContentPane();
							content.setLayout(new GridLayout(1, 1));
							// content.add(label1);
							content.add(textPanel);
							// content.add(returnPanel);

							returnButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent ae) {
									f.dispose();
									customer(e);
								}
							});
						}
					});

					lodgementButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							lodgeMoney();

						}
					});

					withdrawButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							withdrawMoney();
						}
					});

					returnButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							f.dispose();
							menuStart();
						}
					});
				}
			});
		}
	}
	
	/*
	 * Transactions for Customer - Lodgement
	 */
	public void lodgeMoney(){
		loop = true;
		on = true;
		double balance = 0;

		if (acc instanceof CustomerCurrentAccount) {
			int count = 3;
			int checkPin = ((CustomerCurrentAccount) acc).getAtm().getPin();
			loop = true;

			while (loop) {
				if (count == 0) {
					//Replaced another dialog here
					notice = "Pin entered incorrectly 3 times\nATM card locked.";
					builder.toast(notice);
					((CustomerCurrentAccount) acc).getAtm().setValid(false);
					customer(e);
					loop = false;
					on = false;
				}

				String Pin = JOptionPane.showInputDialog(f, "Enter 4 digit PIN;");
				int i = Integer.parseInt(Pin);

				if (on) {
					if (checkPin == i) {
						loop = false;
						//Replaced another dialog here
						notice = "Pin entry successful";
						builder.toast(notice);

					} else {
						count--;
						//Replaced another dialog here
						notice = "Incorrect pin. " + count + " attempts remaining.";
						builder.toast(notice);
					}

				}
			}

		}
		if (on == true) {
			String balanceTest = JOptionPane.showInputDialog(f, "Enter amount you wish to lodge:");
			/*
			 * Replaced the isNumeric method with a better one which tests to see if the string entered was numeric (with decimals).
			 */
			if (validate.isMoney(balanceTest)) {

				balance = Double.parseDouble(balanceTest);
				loop = false;

			} else {
				notice = "You must enter a numerical value!";
				builder.toast(notice);
			}

			acc.setBalance(acc.getBalance() + balance);
			// String date = new
			// SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			Date date = new Date();
			String date2 = date.toString();
			String type = "Lodgement";
			double amount = balance;

			AccountTransaction transaction = new AccountTransaction(date2, type, amount);
			acc.getTransactionList().add(transaction);

			builder.alert(f, validate.formatMoney(balance) + " added do you account!", "Lodgement");
			builder.alert(f, "New balance = " + validate.formatMoney(acc.getBalance()), "Lodgement");
		}
	}
	
	/*
	 * Transactions for Customer - Withdraw
	 */
	public void withdrawMoney(){
		loop = true;
		on = true;
		double withdraw = 0;

		if (acc instanceof CustomerCurrentAccount) {
			int count = 3;
			int checkPin = ((CustomerCurrentAccount) acc).getAtm().getPin();
			loop = true;

			while (loop) {
				if (count == 0) {
					JOptionPane.showMessageDialog(f,
							"Pin entered incorrectly 3 times. ATM card locked.", "Pin",
							JOptionPane.INFORMATION_MESSAGE);
					((CustomerCurrentAccount) acc).getAtm().setValid(false);
					customer(e);
					loop = false;
					on = false;
				}

				String Pin = JOptionPane.showInputDialog(f, "Enter 4 digit PIN;");
				int i = Integer.parseInt(Pin);

				if (on) {
					if (checkPin == i) {
						loop = false;
						JOptionPane.showMessageDialog(f, "Pin entry successful", "Pin",
								JOptionPane.INFORMATION_MESSAGE);

					} else {
						count--;
						JOptionPane.showMessageDialog(f,
								"Incorrect pin. " + count + " attempts remaining.", "Pin",
								JOptionPane.INFORMATION_MESSAGE);

					}

				}
			}

		}
		if (on == true) {
			String balanceTest = JOptionPane.showInputDialog(f,
					"Enter amount you wish to withdraw (max 500):");
			/*
			 * the isNumeric method tests to see if the string entered was numeric.
			 */
			if (validate.isMoney(balanceTest)) {

				withdraw = Double.parseDouble(balanceTest);
				loop = false;

			} else {
				JOptionPane.showMessageDialog(f, "You must enter a numerical value!", "Oops!",
						JOptionPane.INFORMATION_MESSAGE);
			}
			if (withdraw > 500) {
				JOptionPane.showMessageDialog(f, "500 is the maximum you can withdraw at a time.",
						"Oops!", JOptionPane.INFORMATION_MESSAGE);
				withdraw = 0;
			}
			if (withdraw > acc.getBalance()) {
				JOptionPane.showMessageDialog(f, "Insufficient funds.", "Oops!",
						JOptionPane.INFORMATION_MESSAGE);
				withdraw = 0;
			}

			String euro = "\u20ac";
			acc.setBalance(acc.getBalance() - withdraw);
			// recording transaction:
			// String date = new
			// SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			Date date = new Date();
			String date2 = date.toString();

			String type = "Withdraw";
			double amount = withdraw;

			AccountTransaction transaction = new AccountTransaction(date2, type, amount);
			acc.getTransactionList().add(transaction);

			builder.alert(f, validate.formatMoney(withdraw)+ " withdrawn.", "Withdraw");
			builder.alert(f, "New balance = " + validate.formatMoney(acc.getBalance()), "Withdraw");
		}
	}
	
	/*
	 * Transactions for Administrators
	 */
	
	
	/*
	 * Small method to reset fields after creating Customer
	 */
	public void resetFields(){
		pPSTextField.setText("");;
		firstNameTextField.setText("");
		surnameTextField.setText("");
		dOBTextField.setText("");
	}

	/*
	 * General Listener method for Main Menu
	 * Was planning to use it for other actions but I ran into problems
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		/*
		 * Actions for the First Welcome JFrame
		 */
		String user = userType.getSelection().getActionCommand();
		String action = "";
		if(e.getSource() instanceof JButton){
			action = e.getActionCommand();
		}
		switch(user){
		// if user selects NEW
		// CUSTOMER--------------------------------------------------------------------------------------
		case "New Customer":
			newCustomer();
			break;

		// if user select
		// ADMIN----------------------------------------------------------------------------------------------
		case "Administrator":
			admin();
			break;

		// if user selects CUSTOMER
		// ----------------------------------------------------------------------------------------
		case "Customer":
			exisitingCustomer();
			break;
		}
		
		switch(action){
		case "Add Cus":
			
			break;
		}
	}
}
