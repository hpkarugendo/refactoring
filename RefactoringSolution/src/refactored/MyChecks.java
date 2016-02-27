package refactored;

import java.text.NumberFormat;

public class MyChecks {

	public MyChecks() {
		super();
	}
	
	public String formatMoney(double amount){
		String ans = "";
			double d = amount;
			NumberFormat f = NumberFormat.getCurrencyInstance();
			ans = f.format(d);
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
