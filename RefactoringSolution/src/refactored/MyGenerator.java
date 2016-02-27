package refactored;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyGenerator {
	private List<String> passwords, pins;
	private List<Integer> ids;

	public MyGenerator() {
		super();
		this.passwords = new ArrayList<String>();
		this.pins = new ArrayList<String>();
		this.ids = new ArrayList<Integer>();
		generateValues();
	}
	
	public String getPin(){
		return pins.remove(0);
	}
	
	public String getPassword(String first, String last){
		String vString = passwords.remove(0);
		String value = (last.charAt(0) + vString + first.charAt(0)).toUpperCase();
		return value;
	}
	
	public int getId(){
		return ids.remove(0);
	}
	
	public void generateValues(){
		for(int i = 0; i <= 100; i++) {
			Random rn = new Random();
			int range = 9999 - 1000 + 1;
			int randomNum =  rn.nextInt(range) + 1000;
			String value = String.valueOf(randomNum);
			if(!pins.contains(value)){
				pins.add(value);
			}
		}
		
		for(int i = 0; i <= 100; i++) {
			Random rn = new Random();
			int range = 999999 - 100000 + 1;
			int randomNum =  rn.nextInt(range) + 100000;
			String value = String.valueOf(randomNum);
			if(!passwords.contains(value)){
				passwords.add(value);
			}
		}
		
		for(int i = 1; i <= 100; i++) {
				ids.add(i);
		}
	}
	
}
