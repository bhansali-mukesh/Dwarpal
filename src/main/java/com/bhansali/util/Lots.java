package com.bhansali.util;

import java.util.ArrayList;
import java.util.List;

import com.bhansali.entity.Consignment;
import com.bhansali.entity.Lot;

public class Lots implements Cloneable {

	List<Lot> list;

	public Lots() {
		this.list = new ArrayList<Lot>();
	}

	public Lots(List<Lot> lots) {
		this.list = new ArrayList<Lot>();
		this.list = lots;
	}

	public List<Lot> getList() {
		return list;
	}

	public void setList(ArrayList<Lot> lots) {
		this.list = lots;
	}

	public Lots getClone() {

		List<Lot> clonnedLotList = new ArrayList<>();
		for (Lot lot : getList()) {
			clonnedLotList.add(lot.getClone());
		}
		return new Lots(clonnedLotList);
	}

	public Lots getClone(Consignment consignment) {

		List<Lot> clonnedLotList = new ArrayList<>();
		Lot clonnedLot;
		for (Lot lot : getList()) {
			clonnedLot = lot.getClone();
			clonnedLot.setConsignment(consignment);
			clonnedLotList.add(clonnedLot);
		}
		return new Lots(clonnedLotList);
	}

	public Lot getLotByInventoryItem(int inventoryItem) {

		for (Lot lot : list) {

			if (lot.getInventory().getItem() == inventoryItem)
				return lot;
		}

		return null;
	}
}
