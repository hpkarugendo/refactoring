package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MyListener implements ActionListener{
	Builder builder = new Builder();
	CustomerActions cActions = new CustomerActions();

	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		 * Actions for the First Welcome JFrame
		 */
		String user = StaticO.userType.getSelection().getActionCommand();
		String action = "";
		if (e.getSource() instanceof JButton) {
			action = e.getActionCommand();
		}
		switch (user) {
		// if user selects NEW
		// CUSTOMER--------------------------------------------------------------------------------------
		case "New Customer":
			builder.newCustFrame();
			break;

		// if user select
		// ADMIN----------------------------------------------------------------------------------------------
		case "Administrator":
			builder.toast("Hello Admin");
			break;

		// if user selects CUSTOMER
		// ----------------------------------------------------------------------------------------
		case "Customer":
			builder.toast("Hello Customer");
			break;
		}
		
		switch(action){
		case "Add Cus":
			cActions.addNewCustomer();
			break;
		case "Cancel Add Cus":
			StaticO.f1 = null;
			builder.mainFrame();
			break;
		}
	}

}
