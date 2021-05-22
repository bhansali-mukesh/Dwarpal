package com.bhansali.installation;

public class KeyMan {

	static final int DISPLAY_MULTI_FACTOR = 3;
	static final int DISPLAY_ADD_FACTOR = 4;
	
	
	
	public static void main(String[] args) {
		
		int rand = (int) ( Math.random() * 100.0 ) + 1;
		System.out.println(rand);
		
		int extra = (int) ( Math.random() * 8.0 ) + 1;
		System.out.println(extra);
		
		int display = (( rand * DISPLAY_MULTI_FACTOR ) + DISPLAY_ADD_FACTOR) * 10 + extra;
		UTTARMALA.uttar(display, (DISPLAY_ADD_FACTOR * 10) + DISPLAY_MULTI_FACTOR); 
		//System.out.println(display);
	}
		

}
