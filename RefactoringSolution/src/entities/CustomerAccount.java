package entities;

import java.util.ArrayList; 

public class CustomerAccount  {
   
	String number;
	double balance;
	ArrayList<AccountTransaction> transactionList = new ArrayList<AccountTransaction>();
	ATMCard atm;

	//Blank Constructor
	public CustomerAccount()
	{
		this.number = "";
		this.balance = 0;
		this.transactionList = null;
		this.atm = null;
	}
	
	//Constructor with Details
	public CustomerAccount(String number, double balance, ArrayList<AccountTransaction> transactionList)
	{
		this.number = number;
		this.balance = balance;
		this.transactionList = transactionList;
	}
	
	//Accessor methods
	
	
	public String getNumber()
	{
		return this.number;
	}
	
	

	
	public ATMCard getAtm() {
		return atm;
	}

	public void setAtm(ATMCard atm) {
		this.atm = atm;
	}

	public double getBalance()
	{
		return this.balance;
	}
	
	public ArrayList getTransactionList()
	{
		return this.transactionList;
	}

	//Mutator methods
	public void setNumber(String number)
	{
		this.number = number;
	}
	
	public void setBalance(double balance)
	{
		this.balance = balance;
	}
	
	public void setTransactionList(ArrayList transactionList)
	{
		this.transactionList = transactionList;
	}
	
	
	
}
