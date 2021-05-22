package com.bhansali.service.consignment;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.bhansali.entity.Available;
import com.bhansali.entity.Consignment;
import com.bhansali.entity.Login;
import com.bhansali.entity.Lot;
import com.bhansali.entity.Organization;
import com.bhansali.util.Lots;

@Service
public class Delete extends ConsignmentProcessor {

	/**
	 * 
	 * @param lot
	 * @param consignment
	 * @return true if Transaction is completed Successfully
	 * @throws Exception
	 */
	@Transactional
	private Consignment deleteConsignemnt(Lots lots, Consignment consignment, Login login) throws Exception {

		Organization receiver = consignment.getReceiver();
		Organization swayam = login.getOrganization();

		for (Lot lot : lots.getList()) {
			deleteLotVirtually(receiver, lot, swayam);
		}
		consignment.setDeleted(true);
		service.save(consignment);

		return consignment;
	}

	/**
	 * Less Inventory Quantity From Available Inventory
	 * 
	 * @param lot
	 * @return
	 * @throws Exception
	 */
	@Transactional
	private Lot deleteLotVirtually(Organization receiver, Lot lot, Organization swayam) throws Exception {

		Lot persistedLot = service.getLot(lot.getId());

		if (persistedLot == null)
			throw new Exception("Lot not found");

		Integer remaining;
		Available available = service.getAvailable(lot.getInventory());
		if (swayam.getId() == receiver.getId()) {

			if (available == null)
				throw new Exception("Inventory is not Avaiable");

			remaining = available.getQuantity() - persistedLot.getQuantity();
			// Inventory is already Sold/Processed etc.
			if (remaining < 0)
				throw new Exception("Available Quantity is Lesser");

		} else {
			if (available == null) {
				available = new Available();
				available.setInventory(lot.getInventory());
			}
			remaining = available.getQuantity() + persistedLot.getQuantity();
		}

		if (remaining == 0)
			service.removeAvailable(available);
		else {
			available.setQuantity(remaining);
			service.save(available);
		}

		return persistedLot;
	}

	@Override
	public Consignment process(Lots lots, Consignment consignment, Login login) throws Exception {
		return deleteConsignemnt(lots, consignment, login);
	}

}