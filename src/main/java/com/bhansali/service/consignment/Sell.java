package com.bhansali.service.consignment;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.bhansali.entity.Available;
import com.bhansali.entity.Consignment;
import com.bhansali.entity.Inventory;
import com.bhansali.entity.Login;
import com.bhansali.entity.Lot;
import com.bhansali.service.GeneralService;
import com.bhansali.util.DBUtil;
import com.bhansali.util.Lots;

@Service
public class Sell extends ConsignmentProcessor {

	/**
	 * 
	 * @param consignment
	 * @param lots
	 * @return true if Transaction is saved successfully
	 * @throws Exception
	 */
	@Transactional
	public Consignment sendConsignment(Lots lots, Consignment consignment) throws Exception {

		for (Lot lot : lots.getList()) {
			Inventory inventory = getFromAvailable(lot, service, util);
			if (inventory == null)
				throw new Exception("Transaction can not be saved.\nException in sendConsignment()");

			service.save(consignment);

			lot.setConsignment(consignment);
			lot.setInventory(inventory);
			service.save(lot);
		}

		return consignment;
	}

	@Transactional
	public Inventory getFromAvailable(Lot lot, GeneralService service, DBUtil util) throws Exception {
		Inventory inventory = lot.getInventory();
		// Get Persisted Inventory from Database By Item and Detail
		Inventory persistedInventory = service.getInventoryByItemAndDetail(inventory.getItem(),
				util.getInventoryDetail(inventory.getDetail()));

		// Get Available Inventory
		Available available = service.getAvailable(persistedInventory);

		// Inventory Can not be Send for Process if Stock is not Available
		/* Problem When in Lot is Avialble and other is not while Multi-Sell */
		if (available == null || available.getQuantity() < lot.getQuantity())
			throw new Exception("Stock Not Available");

		// Remaining Quantity in Available Stock
		int remaining = available.getQuantity() - lot.getQuantity();

		// Remove From Available if Quantity is 0
		if (remaining == 0) {
			service.removeAvailable(available);
		}
		// Save New Quantity in Available Stock
		else {
			available.setQuantity(remaining);
			service.save(available);
		}

		return persistedInventory;
	}

	@Override
	public Consignment process(Lots lots, Consignment consignment, Login login) throws Exception {
		return sendConsignment(lots, consignment);
	}

}
