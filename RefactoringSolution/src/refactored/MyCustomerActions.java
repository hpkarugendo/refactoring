package refactored;

import java.util.ArrayList;
import java.util.List;

import entities.Customer;
import entities.CustomerAccount;

public class MyCustomerActions {
	MyGenerator g = new MyGenerator();
	MyMenu b = new MyMenu();
	
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
}
