package com.bhansali.service.consignment;

import java.util.List;

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
public class Purchase extends ConsignmentProcessor {

	/**
	 * 
	 * @param lot
	 * @param consignment
	 * @return true if Transaction is completed Successfully
	 * @throws Exception
	 */
	@Transactional
	private Consignment purchaseInventory(Lots lots, Consignment consignment) throws Exception {

		if (consignment.getType().equals(Consignment.Type.SALES_RETURN)) {

			Lots soldLots = new Lots(service.getLots(consignment.getPreviousConsignment()));

			for (Lot lot : lots.getList()) {

				Lot soldLot = soldLots.getLotByInventoryItem(lot.getInventory().getItem());

				if (lot.getQuantity() > soldLot.getQuantity())
					throw new Exception("Can Not Return More than Sold Quantity");
			}

		}

		for (Lot lot : lots.getList()) {
			int item = lot.getInventory().getItem();
			List<Lot> persistedLots = service.getLots(item);

			for (Lot dbLot : persistedLots) {
				// If Inventory With the Same Item Number already Exist and Consignment is not
				// deleted one and It is a Purchase Transaction ( Not Processed or Returned ),
				// Don't Allow

				boolean purchase = consignment.getType().equals(Consignment.Type.PURCHASE)
						|| consignment.getType().equals(Consignment.Type.EDIT);
				if (purchase && !dbLot.getConsignment().isDeleted()) {
					throw new Exception("Item Already Exist.");
				}
			}

			Inventory inventory = AddAvailableInventory(lot, service, util);
			if (inventory == null)
				throw new Exception("Transaction can not be saved.\nException in purchaseInventory()");

			service.save(consignment);

			lot.setConsignment(consignment);
			lot.setInventory(inventory);
			service.save(lot);
		}
		return consignment;

	}

	/**
	 * 
	 * @param lot
	 * @return true if Transaction is saved successfully
	 */
	@Transactional
	private Inventory AddAvailableInventory(Lot lot, GeneralService service, DBUtil util) {
		// Set Existing Inventory Details to New Inventory
		Inventory inventory = util.getInventory(lot.getInventory());
		// Save Inventory
		service.save(inventory);

		if (AddToAvailable(inventory, lot.getQuantity(), service))
			return inventory;

		return null;
	}

	// Comments TO DO
	@Transactional
	private Inventory UpdateReceivedInventory(Lot lot, GeneralService service) {
		Lot persistedLot = service.getLot(lot.getId());
		if (persistedLot == null)
			return null;

		Inventory inventory = lot.getInventory();
		Available available = service.getAvailable(inventory);
		if (available == null)
			return null;

		Integer difference = persistedLot.getQuantity() - lot.getQuantity();
		if (available.getQuantity() < difference)
			return null;

		Integer remaining = available.getQuantity() - difference;

		service.save(inventory);
		available.setInventory(inventory);
		available.setQuantity(remaining);

		service.save(available);

		return inventory;
	}

	@Transactional
	private boolean AddToAvailable(Inventory inventory, int quantity, GeneralService service) {

		// Calculate Available
		Available available = service.getAvailable(inventory);

		// If Not Available, Create New Entry in Available Inventory/Stock
		if (available == null) {
			available = new Available();
			available.setInventory(inventory);
			available.setQuantity(quantity);
		}
		// Else Add Incoming Quantity in Stock
		else {
			Integer total = available.getQuantity() + quantity;
			available.setQuantity(total);
		}
		service.save(available);

		return true;
	}

	@Override
	public Consignment process(Lots lots, Consignment consignment, Login login) throws Exception {
		return purchaseInventory(lots, consignment);
	}

}
