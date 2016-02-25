package views;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import entities.Customer;
import entities.CustomerAccount;
import logic.AlertBuilder;
import logic.Generators;

public class ViewCreator {
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
	ButtonGroup userType = new ButtonGroup();
	Container content;
	// Entities
	List<Customer> customerList = new ArrayList<Customer>();
	Customer cus;
	String PPS, firstName, surname, DOB, password, CustomerID;
	AlertBuilder builder;
	Generators generators;
	// properties
	boolean loop = true, loop2 = true, cont = false, found = false;

	public ViewCreator() {
		super();
		builder = new AlertBuilder();
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
		final ButtonGroup userType = new ButtonGroup();
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

		continueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String user = userType.getSelection().getActionCommand();

				// if user selects NEW
				// CUSTOMER--------------------------------------------------------------------------------------
				if (user.equals("New Customer")) {
					newCustomer();

				}

				// ------------------------------------------------------------------------------------------------------------------

				// if user select
				// ADMIN----------------------------------------------------------------------------------------------
				if (user.equals("Administrator")) {
					admin();
				}
				// ----------------------------------------------------------------------------------------------------------------

				// if user selects CUSTOMER
				// ----------------------------------------------------------------------------------------
				if (user.equals("Customer")) {
					exisitingCustomer();
				}
				// -----------------------------------------------------------------------------------------------------------------------
			}
		});
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
				generators = new Generators(firstName, surname);
				password = generators.getPassword();
				CustomerID = String.valueOf(generators.getId());

				List<CustomerAccount> accounts = new ArrayList<CustomerAccount>();
				Customer customer = new Customer(PPS, surname, firstName, DOB, CustomerID, password, accounts);

				customerList.add(customer);

				JOptionPane.showMessageDialog(f, "CustomerID = " + CustomerID + "\n Password = " + password,
						"Customer created.", JOptionPane.INFORMATION_MESSAGE);
				resetFields();
				menuStart();
				f.dispose();
			}
		});
		JButton cancel = new JButton("Cancel");
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
			Object customerID = JOptionPane.showInputDialog(f, "Enter Customer ID:");

			for (Customer aCustomer : customerList) {

				if (aCustomer.getCustomerID().equals(customerID))
					// search customer list for matching customer ID
				{
					found = true;
					customer = aCustomer;
				}
			}

			if (found == false) {
				int reply = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?",
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					loop = true;
				} else if (reply == JOptionPane.NO_OPTION) {
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
			Object customerPassword = JOptionPane.showInputDialog(f, "Enter Customer Password;");

			if (!customer.getPassword().equals(customerPassword))
				// check if custoemr password is correct
			{
				int reply = JOptionPane.showConfirmDialog(null, null, "Incorrect password. Try again?",
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {

				} else if (reply == JOptionPane.NO_OPTION) {
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
			//customer(customer);
		}
	}

	/*
	 * For Adminstrators
	 */
	public void admin() {
		while(loop)
	    {
	    Object adminUsername = JOptionPane.showInputDialog(f, "Enter Administrator Username:");

	    if(!adminUsername.equals("admin"))//search admin list for admin with matching admin username
	    {
	    	int reply  = JOptionPane.showConfirmDialog(null, null, "Incorrect Username. Try again?", JOptionPane.YES_NO_OPTION);
	    	if (reply == JOptionPane.YES_OPTION) {
	    		loop = true;
	    	}
	    	else if(reply == JOptionPane.NO_OPTION)
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
	    	Object adminPassword = JOptionPane.showInputDialog(f, "Enter Administrator Password;");
	    	
	    	   if(!adminPassword.equals("admin11"))//search admin list for admin with matching admin password
			    {
			    	int reply  = JOptionPane.showConfirmDialog(null, null, "Incorrect Password. Try again?", JOptionPane.YES_NO_OPTION);
			    	if (reply == JOptionPane.YES_OPTION) {
			    		
			    	}
			    	else if(reply == JOptionPane.NO_OPTION){
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
	 * Small methos to reset fields
	 */
	public void resetFields(){
		pPSTextField.setText("");;
		firstNameTextField.setText("");
		surnameTextField.setText("");
		dOBTextField.setText("");
	}

	/*
	 * Separate ActionListener Class
	 */
	class MyActionListener implements ActionListener {
		boolean loop = true;

		@Override
		public void actionPerformed(ActionEvent e) {
			builder = new AlertBuilder();
			/*
			 * Actions for the First Welcome JFrame
			 */
			String user = userType.getSelection().getActionCommand();
			String action = e.getActionCommand();

			// if user selects NEW
			// CUSTOMER--------------------------------------------------------------------------------------
			if (user.equals("New Customer")) {
				newCustomer();
			}

			// if user select
			// ADMIN----------------------------------------------------------------------------------------------
			if (user.equals("Administrator")) {
				builder.toast("Hello Admin");
			}

			// if user selects CUSTOMER
			// ----------------------------------------------------------------------------------------
			if (user.equals("Customer")) {
				builder.toast("Hello Customer");
			}
			
			//For Adding New Customer 'Add Button'
			switch (action) {
			case "Add":
				
				break;

			//For Adding New Customer 'Cancel Button'
			case "Cancel":
				
				break;
			}
			
		}

	}
}
