package logic;

import java.util.ArrayList;
import java.util.List;

import entities.Customer;
import entities.CustomerAccount;

public class CustomerActions {
	Generators g = new Generators();
	Builder b = new Builder();
	
	public void addNewCustomer(){
		StaticO.PPS = StaticO.pPSTextField.getText();
		StaticO.firstName = StaticO.firstNameTextField.getText();
		StaticO.surname = StaticO.surnameTextField.getText();
		StaticO.DOB = StaticO.dOBTextField.getText();
		StaticO.password = g.getPassword(StaticO.firstName, StaticO.surname);
		StaticO.CustomerID = String.valueOf(g.getId());
		List<CustomerAccount> accounts = new ArrayList<CustomerAccount> ();
		Customer customer = new Customer(StaticO.PPS, StaticO.surname, StaticO.firstName, StaticO.DOB, StaticO.CustomerID, StaticO.password, accounts);
		StaticO.customerList.add(customer);
		b.toast("Customer created.\nCustomerID = " + StaticO.CustomerID +"\n Password = " + StaticO.password);
		b.mainFrame();
		StaticO.f1.setVisible(false);
	}
}
