package refactored;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MyListener implements ActionListener{
	MyMenu myMenu = new MyMenu();
	MyCustomerActions cActions = new MyCustomerActions();

	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		 * Actions for the First Welcome JFrame
		 */
		String user = MyStats.userType.getSelection().getActionCommand();
		String action = "";
		if (e.getSource() instanceof JButton) {
			action = e.getActionCommand();
		}
		switch (user) {
		// if user selects NEW
		// CUSTOMER--------------------------------------------------------------------------------------
		case "New Customer":
			myMenu.newCustFrame();
			break;

		// if user select
		// ADMIN----------------------------------------------------------------------------------------------
		case "Administrator":
			myMenu.adminFrame();
			break;

		// if user selects CUSTOMER
		// ----------------------------------------------------------------------------------------
		case "Customer":
			myMenu.oldCustFrame();
			break;
		}
		
		switch(action){
		case "Add Cus":
			cActions.addNewCustomer();
			break;
		case "Cancel Add Cus":
			MyStats.f1 = null;
			myMenu.mainFrame();
			break;
		}
	}

}
