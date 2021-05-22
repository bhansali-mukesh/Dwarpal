package com.bhansali.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bhansali.entity.Inventory;
import com.bhansali.entity.Lot;

public class GeneralUtility {

	public static boolean isValid(String value) {
		return value != null && !value.isEmpty();
	}
	
	public List<Inventory> getInventories(List<Lot> lots) {
		
		List<Inventory> inventories = new ArrayList<Inventory>();
		for ( Lot lot : lots) {
			inventories.add(lot.getInventory());
		}
		return inventories;
	}
	
	public static boolean isExpired() {
		Date trial = new Date(119, 10, 30);
    	Date aaj = new Date();
    	
    	int isTrial = trial.compareTo(aaj);
    	
    	return isTrial < 0;
	}
}
