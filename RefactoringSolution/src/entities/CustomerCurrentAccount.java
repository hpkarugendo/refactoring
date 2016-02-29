package entities;

import java.util.ArrayList;

public class CustomerCurrentAccount extends CustomerAccount 
{
	
public CustomerCurrentAccount()
{
	super();
	
}

public CustomerCurrentAccount(ATMCard atm, String number, double balance, ArrayList<AccountTransaction> transactionList)
{
	super(number, balance, transactionList);	
	setAtm(atm);
}

@Override
public void setAtm(ATMCard atm) {
	// TODO Auto-generated method stub
	this.atm = atm;
}

}