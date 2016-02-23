package logic;

import java.text.NumberFormat;

public class Validation {

	public Validation() {
		super();
	}
	
	public String formatMoney(String amount){
		String ans = "";
		if(isMoney(amount)){
			double d = Double.parseDouble(amount);
			NumberFormat f = NumberFormat.getCurrencyInstance();
			ans = f.format(d);
		}
		
		return ans;
	}

	public boolean isMoney(String value) {
		double amount = -1;
		
		try {
			amount = Double.parseDouble(value);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		if(value.matches("\\d+(\\.\\d+)*") && amount >=0){
			return true;
		} else {
			return false;
		}
	}

}
