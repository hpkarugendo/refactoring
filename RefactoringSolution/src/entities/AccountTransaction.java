package entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AccountTransaction {

	String date;
	String type;
	double amount;
	
	//Blank constructor
	public AccountTransaction()
	{
		this.date = "";
		this.type = "";	
		this.amount = 0;
		
	}
	
	//Constructor with details
	public AccountTransaction(String date, String type, double amount)
	{
		this.date = date;
		this.type = type;
		
		
		this.amount = amount;
		
	}
	
	public void deposit(double amount) {
		Date d = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		this.date = f.format(d);
		this.type = "lodgement";
		this.amount = amount;
	}
	
	public void withdraw(double amount){
		Date d = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		this.date = f.format(d);
		this.type = "withdraw";
		this.amount = amount;
	}
	
	//Accessor methods
	public String getDate()
	{
		return this.date;
	}
	
	public String getType()
	{
		return this.type;
	}

	public double getAmount()
	{
		return this.amount;
	}

	//mutator methods
	public void setDate(String date)
	{
		this.date = date;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	public void setAmount(double amount)
	{
		this.amount = amount;
	}
	
	public String toString()
	{
		return  "\n" + "Date = " + this.date + "\n"
				+ "Type = " + this.type + "\n"
				+ "Amount = " + this.amount + "\n";
			
	}
	
}
