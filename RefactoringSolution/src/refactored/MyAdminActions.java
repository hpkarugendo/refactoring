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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import entities.ATMCard;
import entities.AccountTransaction;
import entities.Customer;
import entities.CustomerCurrentAccount;
import entities.CustomerDepositAccount;

public class MyAdminActions {
	MyGenerator g = new MyGenerator();
	MyMenu m = new MyMenu();
	MyChecks c = new MyChecks();

	public void transactions(){
;
		
		MyStats.fAdmin = new JFrame("Administrator Menu");
		MyStats.fAdmin.setSize(400, 400);
		MyStats.fAdmin.setLocationRelativeTo(null);
		MyStats.fAdmin.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		MyStats.fAdmin.setVisible(true);

		JPanel deleteCustomerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton deleteCustomer = new JButton("Delete Customer");
		deleteCustomer.setPreferredSize(new Dimension(250, 20));
		deleteCustomerPanel.add(deleteCustomer);

		JPanel deleteAccountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton deleteAccount = new JButton("Delete Account");
		deleteAccount.setPreferredSize(new Dimension(250, 20));
		deleteAccountPanel.add(deleteAccount);

		JPanel bankChargesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton bankChargesButton = new JButton("Apply Bank Charges");
		bankChargesButton.setPreferredSize(new Dimension(250, 20));
		bankChargesPanel.add(bankChargesButton);

		JPanel interestPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton interestButton = new JButton("Apply Interest");
		interestPanel.add(interestButton);
		interestButton.setPreferredSize(new Dimension(250, 20));

		JPanel editCustomerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton editCustomerButton = new JButton("Edit existing Customer");
		editCustomerPanel.add(editCustomerButton);
		editCustomerButton.setPreferredSize(new Dimension(250, 20));

		JPanel navigatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton navigateButton = new JButton("Navigate Customer Collection");
		navigatePanel.add(navigateButton);
		navigateButton.setPreferredSize(new Dimension(250, 20));

		JPanel summaryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton summaryButton = new JButton("Display Summary Of All Accounts");
		summaryPanel.add(summaryButton);
		summaryButton.setPreferredSize(new Dimension(250, 20));

		JPanel accountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton accountButton = new JButton("Add an Account to a Customer");
		accountPanel.add(accountButton);
		accountButton.setPreferredSize(new Dimension(250, 20));

		JPanel returnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		returnPanel.add(MyStats.exitButtonA);

		JLabel label1 = new JLabel("Please select an option");

		MyStats.cAdmin = MyStats.fAdmin.getContentPane();
		MyStats.cAdmin.setLayout(new GridLayout(9, 1));
		MyStats.cAdmin.add(label1);
		MyStats.cAdmin.add(accountPanel);
		MyStats.cAdmin.add(bankChargesPanel);
		MyStats.cAdmin.add(interestPanel);
		MyStats.cAdmin.add(editCustomerPanel);
		MyStats.cAdmin.add(navigatePanel);
		MyStats.cAdmin.add(summaryPanel);
		MyStats.cAdmin.add(deleteCustomerPanel);
		// MyStats.cAdmin.add(deleteAccountPanel);
		MyStats.cAdmin.add(returnPanel);

		bankChargesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				charges();
			}
		});

		interestButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				interest();
			}
		});

		editCustomerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				edit();
			}
		});

		summaryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				summary();
			}
		});

		navigateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				navigate();
			}
		});

		accountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				account();
			}
		});

		deleteCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				deleteCus();
			}
		});

		deleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				deleteAcc();
			}

		});
		MyStats.exitButtonA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				MyStats.fAdmin.setVisible(false);
				m.mainFrame();
			}
		});	
	}
	
	public void charges(){
		MyStats.loop = true;
		MyStats.found = false;

		if (MyStats.customerList.isEmpty()) {
			m.alert(MyStats.fAdmin, "There are no customers yet!", "Oops!");
			MyStats.fAdmin.setVisible(false);
			transactions();

		} else {
			while (MyStats.loop) {
				Object customerID = m.input(MyStats.fAdmin,
						"Customer ID of Customer You Wish to Apply Charges to:");

				for (Customer aCustomer : MyStats.customerList) {

					if (aCustomer.getCustomerID().equals(customerID)) {
						MyStats.found = true;
						MyStats.customer = aCustomer;
						MyStats.loop = false;
					}
				}

				if (MyStats.found == false) {
					int reply = m.confirm("User not MyStats.found. Try again?");
					if (reply == 1) {
						MyStats.loop = true;
					} else if (reply == 2) {
						MyStats.fAdmin.setVisible(false);
						MyStats.loop = false;

						transactions();
					}
				} else {
					MyStats.fAdmin.setVisible(false);
					MyStats.fAdmin = new JFrame("Administrator Menu");
					MyStats.fAdmin.setSize(400, 300);
					MyStats.fAdmin.setLocationRelativeTo(null);
					MyStats.fAdmin.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent we) {
							System.exit(0);
						}
					});
					MyStats.fAdmin.setVisible(true);

					JComboBox<String> box = new JComboBox<String>();
					for (int i = 0; i < MyStats.customer.getAccounts().size(); i++) {

						box.addItem(MyStats.customer.getAccounts().get(i).getNumber());
					}

					box.getSelectedItem();

					JPanel boxPanel = new JPanel();
					boxPanel.add(box);

					JPanel buttonPanel = new JPanel();
					buttonPanel.add(MyStats.continueButtonA);
					buttonPanel.add(MyStats.returnButtonA);
					MyStats.cAdmin = MyStats.fAdmin.getContentPane();
					MyStats.cAdmin.setLayout(new GridLayout(2, 1));

					MyStats.cAdmin.add(boxPanel);
					MyStats.cAdmin.add(buttonPanel);

					if (MyStats.customer.getAccounts().isEmpty()) {
						m.alert(MyStats.fAdmin,
								"This MyStats.customer has no accounts! \n The admin must add acounts to this MyStats.customer.",
								"Oops!");
						MyStats.fAdmin.setVisible(false);
						transactions();
					} else {

						for (int i = 0; i < MyStats.customer.getAccounts().size(); i++) {
							if (MyStats.customer.getAccounts().get(i).getNumber() == box.getSelectedItem()) {
								MyStats.acc = MyStats.customer.getAccounts().get(i);
							}
						}

						MyStats.continueButtonA.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent ae) {
								if (MyStats.acc instanceof CustomerDepositAccount) {

									m.alert(MyStats.fAdmin,
											c.formatMoney(25) + " deposit account fee aplied.", "");
									MyStats.acc.setBalance(MyStats.acc.getBalance() - 25);
									m.alert(MyStats.fAdmin, "New balance = " + c.formatMoney(MyStats.acc.getBalance()),
											"Success!");
								}

								if (MyStats.acc instanceof CustomerCurrentAccount) {

									m.alert(MyStats.fAdmin,
											c.formatMoney(15)+ " current account fee aplied.", "");
									MyStats.acc.setBalance(MyStats.acc.getBalance() - 25);
									m.alert(MyStats.fAdmin, "New balance = " + MyStats.acc.getBalance(),
											"Success!");
								}

								MyStats.fAdmin.setVisible(false);
								transactions();
							}
						});

						MyStats.exitButtonA.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent ae) {
								MyStats.fAdmin.setVisible(false);
								m.mainFrame();
							}
						});

					}
				}
			}
		}
	}
	
	public void interest(){
		MyStats.loop = true;
		MyStats.found = false;

		if (MyStats.customerList.isEmpty()) {
			m.alert(MyStats.fAdmin, "There are no customers yet!", "Oops!");
			MyStats.fAdmin.setVisible(false);
			transactions();

		} else {
			while (MyStats.loop) {
				Object customerID = m.input(MyStats.fAdmin,
						"Customer ID of Customer You Wish to Apply Interest to:");

				for (Customer aCustomer : MyStats.customerList) {

					if (aCustomer.getCustomerID().equals(customerID)) {
						MyStats.found = true;
						MyStats.customer = aCustomer;
						MyStats.loop = false;
					}
				}

				if (MyStats.found == false) {
					int reply = m.confirm("User not MyStats.found. Try again?");
					if (reply == 1) {
						MyStats.loop = true;
					} else if (reply == 2) {
						MyStats.fAdmin.setVisible(false);
						MyStats.loop = false;

						transactions();
					}
				} else {
					MyStats.fAdmin.setVisible(false);
					MyStats.fAdmin = new JFrame("Administrator Menu");
					MyStats.fAdmin.setSize(400, 300);
					MyStats.fAdmin.setLocationRelativeTo(null);
					MyStats.fAdmin.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent we) {
							System.exit(0);
						}
					});
					MyStats.fAdmin.setVisible(true);

					JComboBox<String> box = new JComboBox<String>();
					for (int i = 0; i < MyStats.customer.getAccounts().size(); i++) {

						box.addItem(MyStats.customer.getAccounts().get(i).getNumber());
					}

					box.getSelectedItem();

					JPanel boxPanel = new JPanel();

					JLabel label = new JLabel("Select an account to apply interest to:");
					boxPanel.add(label);
					boxPanel.add(box);
					JPanel buttonPanel = new JPanel();
					buttonPanel.add(MyStats.continueButtonA);
					buttonPanel.add(MyStats.returnButtonA);
					MyStats.cAdmin = MyStats.fAdmin.getContentPane();
					MyStats.cAdmin.setLayout(new GridLayout(2, 1));

					MyStats.cAdmin.add(boxPanel);
					MyStats.cAdmin.add(buttonPanel);

					if (MyStats.customer.getAccounts().isEmpty()) {
						m.alert(MyStats.fAdmin,
								"This MyStats.customer has no accounts! \n The admin must add acounts to this MyStats.customer.",
								"Oops!");
						MyStats.fAdmin.setVisible(false);
						transactions();
					} else {

						for (int i = 0; i < MyStats.customer.getAccounts().size(); i++) {
							if (MyStats.customer.getAccounts().get(i).getNumber() == box.getSelectedItem()) {
								MyStats.acc = MyStats.customer.getAccounts().get(i);
							}
						}

						MyStats.continueButtonA.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent ae) {
								String euro = "\u20ac";
								double interest = 0;
								MyStats.loop = true;

								while (MyStats.loop) {
									String interestString = (String) m.input(MyStats.fAdmin,
											"Enter interest percentage you wish to apply: \n NOTE: Please enter a numerical value. (with no percentage sign) \n E.g: If you wish to apply 8% interest, enter '8'");
									// the isNumeric method tests to see if the string entered was numeric.
									if (c.isMoney(interestString)) {

										interest = Double.parseDouble(interestString);
										MyStats.loop = false;

										MyStats.acc.setBalance(
												MyStats.acc.getBalance() + (MyStats.acc.getBalance() * (interest / 100)));

										m.alert(MyStats.fAdmin,
												interest + "% interest applied. \n new balance = "
														+ c.formatMoney(MyStats.acc.getBalance()),
												"Success!");
									}

									else {
										m.alert(MyStats.fAdmin, "You must enter a numerical value!",
												"Oops!");
									}

								}

								MyStats.fAdmin.setVisible(false);
								transactions();
							}
						});

						MyStats.returnButtonA.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent ae) {
								MyStats.fAdmin.setVisible(false);
								m.mainFrame();
							}
						});

					}
				}
			}
		}
	}
	
	public void edit(){
		MyStats.loop = true;
		MyStats.found = false;

		if (MyStats.customerList.isEmpty()) {
			m.alert(MyStats.fAdmin, "There are no customers yet!", "Oops!");
			MyStats.fAdmin.setVisible(false);
			transactions();

		} else {

			while (MyStats.loop) {
				Object customerID = m.input(MyStats.fAdmin, "Enter Customer ID:");

				for (Customer aCustomer : MyStats.customerList) {

					if (aCustomer.getCustomerID().equals(customerID)) {
						MyStats.found = true;
						MyStats.customer = aCustomer;
					}
				}

				if (MyStats.found == false) {
					int reply = m.confirm("User not MyStats.found. Try again?");
					if (reply == 1) {
						MyStats.loop = true;
					} else if (reply == 2) {
						MyStats.fAdmin.setVisible(false);
						MyStats.loop = false;

						transactions();
					}
				} else {
					MyStats.loop = false;
				}

			}

			MyStats.fAdmin.setVisible(false);
			MyStats.fAdmin = new JFrame("Administrator Menu");
			MyStats.fAdmin.setSize(400, 300);
			MyStats.fAdmin.setLocationRelativeTo(null);
			MyStats.fAdmin.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) {
					System.exit(0);
				}
			});

			JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

			JPanel cancelPanel = new JPanel();

			textPanel.add(MyStats.firstNameLabelA);
			textPanel.add(MyStats.firstNameTextFieldA);
			textPanel.add(MyStats.surnameLabelA);
			textPanel.add(MyStats.surnameTextFieldA);
			textPanel.add(MyStats.pPPSLabelA);
			textPanel.add(MyStats.pPSTextFieldA);
			textPanel.add(MyStats.dOBLabelA);
			textPanel.add(MyStats.dOBTextFieldA);
			textPanel.add(MyStats.customerIDLabelA);
			textPanel.add(MyStats.customerIDTextFieldA);
			textPanel.add(MyStats.passwordLabelA);
			textPanel.add(MyStats.passwordTextFieldA);

			MyStats.firstNameTextFieldA.setText(MyStats.customer.getFirstName());
			MyStats.surnameTextFieldA.setText(MyStats.customer.getSurname());
			MyStats.pPSTextFieldA.setText(MyStats.customer.getPPS());
			MyStats.dOBTextFieldA.setText(MyStats.customer.getDOB());
			MyStats.customerIDTextFieldA.setText(MyStats.customer.getCustomerID());
			MyStats.passwordTextFieldA.setText(MyStats.customer.getPassword());

			// JLabel label1 = new JLabel("Edit MyStats.customer details below.
			// The save");

			JButton saveButton = new JButton("Save");
			JButton cancelButton = new JButton("Exit");

			cancelPanel.add(cancelButton, BorderLayout.SOUTH);
			cancelPanel.add(saveButton, BorderLayout.SOUTH);
			// MyStats.cAdmin.removeAll();
			MyStats.cAdmin = MyStats.fAdmin.getContentPane();
			MyStats.cAdmin.setLayout(new GridLayout(2, 1));
			MyStats.cAdmin.add(textPanel, BorderLayout.NORTH);
			MyStats.cAdmin.add(cancelPanel, BorderLayout.SOUTH);

			MyStats.fAdmin.setContentPane(MyStats.cAdmin);
			MyStats.fAdmin.setSize(340, 350);
			MyStats.fAdmin.setLocationRelativeTo(null);
			MyStats.fAdmin.setVisible(true);
			MyStats.fAdmin.setResizable(false);

			saveButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {

					MyStats.customer.setFirstName(MyStats.firstNameTextFieldA.getText());
					MyStats.customer.setSurname(MyStats.surnameTextFieldA.getText());
					MyStats.customer.setPPS(MyStats.pPSTextFieldA.getText());
					MyStats.customer.setDOB(MyStats.dOBTextFieldA.getText());
					MyStats.customer.setCustomerID(MyStats.customerIDTextFieldA.getText());
					MyStats.customer.setPassword(MyStats.passwordTextFieldA.getText());

					m.alert(MyStats.fAdmin, "Changes Saved.", "Success");
				}
			});

			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					transactions();
				}
			});
		}
	}
	
	public void summary(){
		MyStats.fAdmin.setVisible(false);
		MyStats.fAdmin = new JFrame("Summary of Transactions");
		MyStats.fAdmin.setSize(400, 700);
		MyStats.fAdmin.setLocationRelativeTo(null);
		MyStats.fAdmin.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		MyStats.fAdmin.setVisible(true);

		JLabel label1 = new JLabel("Summary of all transactions: ");

		JPanel returnPanel = new JPanel();
		returnPanel.add(MyStats.returnButtonA);

		JPanel textPanel = new JPanel();

		textPanel.setLayout(new BorderLayout());
		JTextArea textArea = new JTextArea(40, 20);
		textArea.setEditable(false);
		textPanel.add(label1, BorderLayout.NORTH);
		textPanel.add(textArea, BorderLayout.CENTER);
		textPanel.add(MyStats.returnButtonA, BorderLayout.SOUTH);

		JScrollPane scrollPane = new JScrollPane(textArea);
		textPanel.add(scrollPane);

		for (int a = 0; a < MyStats.customerList.size(); a++)
		// For each MyStats.customer, for each account, it displays each
		// transaction.
		{
			for (int b = 0; b < MyStats.customerList.get(a).getAccounts().size(); b++) {
				MyStats.acc = MyStats.customerList.get(a).getAccounts().get(b);
				for (int c = 0; c < MyStats.customerList.get(a).getAccounts().get(b).getTransactionList().size(); c++) {

					textArea.append(MyStats.acc.getTransactionList().get(c).toString());
					/*
					 * Int total =
					 * MyStats.acc.getTransactionList().get(c).getAmount(); I
					 * was going to use this to keep a running total but I
					 * couldnt get it working.
					 */

				}
			}
		}

		textPanel.add(textArea);
		MyStats.cAdmin.removeAll();

		MyStats.cAdmin = MyStats.fAdmin.getContentPane();
		MyStats.cAdmin.setLayout(new GridLayout(1, 1));
		// MyStats.cAdmin.add(label1);
		MyStats.cAdmin.add(textPanel);
		// MyStats.cAdmin.add(returnPanel);

		MyStats.returnButtonA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				MyStats.fAdmin.setVisible(false);
				transactions();
			}
		});
	}
	
	public void navigate(){
		MyStats.fAdmin.setVisible(false);

		if (MyStats.customerList.isEmpty()) {
			m.alert(MyStats.fAdmin, "There are currently no customers to display. ", "Oops");
			transactions();
		} else {

			JButton first, previous, next, last, cancel;
			JPanel gridPanel, buttonPanel, cancelPanel;

			MyStats.cAdmin = MyStats.fAdmin.getContentPane();

			MyStats.cAdmin.setLayout(new BorderLayout());

			buttonPanel = new JPanel();
			gridPanel = new JPanel(new GridLayout(8, 2));
			cancelPanel = new JPanel();

			first = new JButton("First");
			previous = new JButton("Previous");
			next = new JButton("Next");
			last = new JButton("Last");
			cancel = new JButton("Cancel");

			MyStats.firstNameTextFieldA.setText(MyStats.customerList.get(0).getFirstName());
			MyStats.surnameTextFieldA.setText(MyStats.customerList.get(0).getSurname());
			MyStats.pPSTextFieldA.setText(MyStats.customerList.get(0).getPPS());
			MyStats.dOBTextFieldA.setText(MyStats.customerList.get(0).getDOB());
			MyStats.customerIDTextFieldA.setText(MyStats.customerList.get(0).getCustomerID());
			MyStats.passwordTextFieldA.setText(MyStats.customerList.get(0).getPassword());

			MyStats.firstNameTextFieldA.setEditable(false);
			MyStats.surnameTextFieldA.setEditable(false);
			MyStats.pPSTextFieldA.setEditable(false);
			MyStats.dOBTextFieldA.setEditable(false);
			MyStats.customerIDTextFieldA.setEditable(false);
			MyStats.passwordTextFieldA.setEditable(false);

			gridPanel.add(MyStats.firstNameLabelA);
			gridPanel.add(MyStats.firstNameTextFieldA);
			gridPanel.add(MyStats.surnameLabelA);
			gridPanel.add(MyStats.surnameTextFieldA);
			gridPanel.add(MyStats.pPPSLabelA);
			gridPanel.add(MyStats.pPSTextFieldA);
			gridPanel.add(MyStats.dOBLabelA);
			gridPanel.add(MyStats.dOBTextFieldA);
			gridPanel.add(MyStats.customerIDLabelA);
			gridPanel.add(MyStats.customerIDTextFieldA);
			gridPanel.add(MyStats.passwordLabelA);
			gridPanel.add(MyStats.passwordTextFieldA);

			buttonPanel.add(first);
			buttonPanel.add(previous);
			buttonPanel.add(next);
			buttonPanel.add(last);

			cancelPanel.add(cancel);

			MyStats.cAdmin.add(gridPanel, BorderLayout.NORTH);
			MyStats.cAdmin.add(buttonPanel, BorderLayout.CENTER);
			MyStats.cAdmin.add(cancelPanel, BorderLayout.AFTER_LAST_LINE);
			first.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					MyStats.position = 0;
					MyStats.firstNameTextFieldA.setText(MyStats.customerList.get(0).getFirstName());
					MyStats.surnameTextFieldA.setText(MyStats.customerList.get(0).getSurname());
					MyStats.pPSTextFieldA.setText(MyStats.customerList.get(0).getPPS());
					MyStats.dOBTextFieldA.setText(MyStats.customerList.get(0).getDOB());
					MyStats.customerIDTextFieldA.setText(MyStats.customerList.get(0).getCustomerID());
					MyStats.passwordTextFieldA.setText(MyStats.customerList.get(0).getPassword());
				}
			});

			previous.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {

					if (MyStats.position < 1) {
						// don't do anything
					} else {
						MyStats.position = MyStats.position - 1;

						MyStats.firstNameTextFieldA.setText(MyStats.customerList.get(MyStats.position).getFirstName());
						MyStats.surnameTextFieldA.setText(MyStats.customerList.get(MyStats.position).getSurname());
						MyStats.pPSTextFieldA.setText(MyStats.customerList.get(MyStats.position).getPPS());
						MyStats.dOBTextFieldA.setText(MyStats.customerList.get(MyStats.position).getDOB());
						MyStats.customerIDTextFieldA.setText(MyStats.customerList.get(MyStats.position).getCustomerID());
						MyStats.passwordTextFieldA.setText(MyStats.customerList.get(MyStats.position).getPassword());
					}
				}
			});

			next.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {

					if (MyStats.position == MyStats.customerList.size() - 1) {
						// don't do anything
					} else {
						MyStats.position = MyStats.position + 1;

						MyStats.firstNameTextFieldA.setText(MyStats.customerList.get(MyStats.position).getFirstName());
						MyStats.surnameTextFieldA.setText(MyStats.customerList.get(MyStats.position).getSurname());
						MyStats.pPSTextFieldA.setText(MyStats.customerList.get(MyStats.position).getPPS());
						MyStats.dOBTextFieldA.setText(MyStats.customerList.get(MyStats.position).getDOB());
						MyStats.customerIDTextFieldA
								.setText(MyStats.customerList.get(MyStats.position).getCustomerID());
						MyStats.passwordTextFieldA.setText(MyStats.customerList.get(MyStats.position).getPassword());
					}

				}
			});

			last.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {

					MyStats.position = MyStats.customerList.size() - 1;

					MyStats.firstNameTextFieldA.setText(MyStats.customerList.get(MyStats.position).getFirstName());
					MyStats.surnameTextFieldA.setText(MyStats.customerList.get(MyStats.position).getSurname());
					MyStats.pPSTextFieldA.setText(MyStats.customerList.get(MyStats.position).getPPS());
					MyStats.dOBTextFieldA.setText(MyStats.customerList.get(MyStats.position).getDOB());
					MyStats.customerIDTextFieldA.setText(MyStats.customerList.get(MyStats.position).getCustomerID());
					MyStats.passwordTextFieldA.setText(MyStats.customerList.get(MyStats.position).getPassword());
				}
			});

			cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					;
					transactions();
				}
			});
			MyStats.fAdmin.setContentPane(MyStats.cAdmin);
			MyStats.fAdmin.setSize(400, 300);
			MyStats.fAdmin.setVisible(true);
		}
	}
	
	public void account(){
		if (MyStats.customerList.isEmpty()) {
			m.alert(MyStats.fAdmin, "There are no customers yet!", "Oops!");
			MyStats.fAdmin.setVisible(false);
			transactions();
		} else {
			MyStats.loop = true;
			MyStats.found = false;

			while (MyStats.loop) {
				Object customerID = m.input(MyStats.fAdmin,
						"Customer ID of Customer You Wish to Add an Account to:");

				for (Customer aCustomer : MyStats.customerList) {

					if (aCustomer.getCustomerID().equals(customerID)) {
						MyStats.found = true;
						MyStats.customer = aCustomer;
					}
				}

				if (MyStats.found == false) {
					int reply = m.confirm("User not MyStats.found. Try again?");
					if (reply == 1) {
						MyStats.loop = true;
					} else if (reply == 2) {
						MyStats.fAdmin.setVisible(false);
						MyStats.loop = false;

						transactions();
					}
				} else {
					MyStats.loop = false;
					// a combo box in an dialog box that asks the admin
					// what type of account they wish to create
					// (deposit/current)
					String[] choices = { "Current Account", "Deposit Account" };
					String account = (String) JOptionPane.showInputDialog(null, "Please choose account type",
							"Account Type", JOptionPane.QUESTION_MESSAGE, null, choices, choices[1]);

					if (account.equals("Current Account")) {
						// create current account
						boolean valid = true;
						double balance = 0;
						String number = String.valueOf("C" + (MyStats.customerList.indexOf(MyStats.customer) + 1) * 10
								+ (MyStats.customer.getAccounts().size() + 1));
						// this simple algorithm generates the account number
						ArrayList<AccountTransaction> transactionList = new ArrayList<AccountTransaction>();
						int randomPIN = (int) (Math.random() * 9000) + 1000;
						String pin = String.valueOf(randomPIN);

						ATMCard atm = new ATMCard(randomPIN, valid);

						CustomerCurrentAccount current = new CustomerCurrentAccount(atm, number, balance,
								transactionList);

						MyStats.customer.getAccounts().add(current);
						m.alert(MyStats.fAdmin, "Account number = " + number + "\n PIN = " + pin,
								"Account created.");

						MyStats.fAdmin.setVisible(false);
						transactions();
					}

					if (account.equals("Deposit Account")) {
						// create deposit account

						double balance = 0, interest = 0;
						String number = String.valueOf("D" + (MyStats.customerList.indexOf(MyStats.customer) + 1) * 10
								+ (MyStats.customer.getAccounts().size() + 1));
						// this simple algorithm generates the account number
						ArrayList<AccountTransaction> transactionList = new ArrayList<AccountTransaction>();

						CustomerDepositAccount deposit = new CustomerDepositAccount(interest, number, balance,
								transactionList);

						MyStats.customer.getAccounts().add(deposit);
						m.alert(MyStats.fAdmin, "Account number = " + number, "Account created.");

						MyStats.fAdmin.setVisible(false);
						transactions();
					}

				}
			}
		}
	}
	
	public void deleteCus(){
		MyStats.found = true;
		MyStats.loop = true;

		if (MyStats.customerList.isEmpty()) {
			m.alert(MyStats.fAdmin,"There are currently no customers to display. ", "Oops");
			transactions();
		} else {
			{
				Object customerID = m.input(MyStats.fAdmin,
						"Customer ID of Customer You Wish to Delete:");

				for (Customer aCustomer : MyStats.customerList) {

					if (aCustomer.getCustomerID().equals(customerID)) {
						MyStats.found = true;
						MyStats.customer = aCustomer;
						MyStats.loop = false;
					}
				}

				if (MyStats.found == false) {
					int reply = m.confirm("User not MyStats.found. Try again?");
					if (reply == 1) {
						MyStats.loop = true;
					} else if (reply == 2) {
						MyStats.fAdmin.setVisible(false);
						MyStats.loop = false;

						transactions();
					}
				} else {
					if (MyStats.customer.getAccounts().size() > 0) {
						m.alert(MyStats.fAdmin,
								"This MyStats.customer has accounts. \n You must delete a MyStats.customer's accounts before deleting a MyStats.customer ",
								"Oops!");
					} else {
						MyStats.customerList.remove(MyStats.customer);
						m.alert(MyStats.fAdmin, "Customer Deleted ", "Success.");
					}
				}

			}
		}
	}
	
	public void deleteAcc(){
		MyStats.found = true;
		MyStats.loop = true;

		{
			Object customerID = m.input(MyStats.fAdmin,
					"Customer ID of Customer from which you wish to delete an account");

			for (Customer aCustomer : MyStats.customerList) {

				if (aCustomer.getCustomerID().equals(customerID)) {
					MyStats.found = true;
					MyStats.customer = aCustomer;
					MyStats.loop = false;
				}
			}

			if (MyStats.found == false) {
				int reply = m.confirm("User not MyStats.found. Try again?");
				if (reply == 1) {
					MyStats.loop = true;
				} else if (reply == 2) {
					MyStats.fAdmin.setVisible(false);
					MyStats.loop = false;

					transactions();
				}
			} else {
				// Here I would make the user select a an account to
				// delete from a combo box. If the account had a balance
				// of 0 then it would be deleted. (I do not have time to
				// do this)
			}

		}
	}
}
