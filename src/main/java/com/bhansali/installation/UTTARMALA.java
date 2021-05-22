package com.bhansali.installation;

import java.util.Date;

public class UTTARMALA {

	static final int ALP_SANKHYAK_ALPHA_FACTOR = 5;
	
	static final double KEY_MULTI_FACTOR = 0.11;
	static final int KEY_ADD_FACTOR = 1;
	
	static final int DATE_MULTI_FACTOR = 10;
	static final int MONTH_MULTI_FACTOR = 100;
	static final int YEAR_MULTI_FACTOR = 1;

	public static String uttar(int prashn, int koti) {
		
		//System.out.println(prashn);
		prashn = prashn/10;
		//System.out.println(prashn);
		//System.out.println(koti);
		
		Date date = new Date();
		
		int koti_add_factor = koti / 10;
		int koti_multi_factor = koti % 10;
		
		int original = ( prashn - koti_add_factor ) / koti_multi_factor;
		
		int by10;
		int mod10;
		System.out.println(original);
		if( original > 10 )
		{
			by10 = original / 10;
			mod10 = original % 10;
			//System.out.println(numberBy10 + "<>" + numberBy10);
		}
		else
		{
			by10 =  prashn / ALP_SANKHYAK_ALPHA_FACTOR;
			mod10 = prashn % ALP_SANKHYAK_ALPHA_FACTOR;
			//System.out.println(numberBy10 + "<>" + numberBy10);
		}
		
		int square = original * original;
		//System.out.println(square);
		
		int param_mitra = square % 10 == 0 ? 0 : 10 - ( square % 10 );
		//System.out.println(param_mitra);
		
		double KeyNum = ( ( square + param_mitra ) * KEY_MULTI_FACTOR ) + KEY_ADD_FACTOR;
		//System.out.println(KeyNum);

		int keyDate = date.getDate() * DATE_MULTI_FACTOR + ( date.getMonth() + 1 ) * MONTH_MULTI_FACTOR +  date.getYear() * YEAR_MULTI_FACTOR;
		//System.out.println(keyDate);
		
		int dateMod100 = keyDate % 100;
		int dateBy100 = keyDate / 100;
		//System.out.println(dateMod100 + "" + dateBy100);
		
		String keyAlpha = "";
		for(int i=0; i<by10; i++) {
			if(i%2 == 0)
				keyAlpha += (char) ( mod10 + 65 + i+1) ;
			else
				keyAlpha += (char) ( mod10 + 97 + i-1) ;
		}
		//System.out.println(keyAlpha);
		
		String KEY = dateMod100 + keyAlpha + KeyNum + "" + dateBy100; 
		System.out.println(KEY);
		
		return KEY;
	}

}
