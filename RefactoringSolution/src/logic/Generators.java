package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generators {
	private List<String> passwords, pins;
	private List<Integer> ids;
	private String fName, lName;

	public Generators(String first, String last) {
		super();
		this.passwords = new ArrayList<String>();
		this.pins = new ArrayList<String>();
		this.ids = new ArrayList<Integer>();
		this.fName = first;
		this.lName = last;
		generateValues();
	}
	
	public String getPin(){
		return pins.remove(0);
	}
	
	public String getPassword(){
		return passwords.remove(0);
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
			String value = (this.lName.charAt(0) +  String.valueOf(randomNum) + this.fName.charAt(0)).toUpperCase();
			if(!passwords.contains(value)){
				passwords.add(value);
			}
		}
		
		for(int i = 1; i <= 100; i++) {
				ids.add(i);
		}
	}
	
}
