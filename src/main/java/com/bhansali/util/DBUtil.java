package com.bhansali.util;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bhansali.dao.GeneralDao;
import com.bhansali.entity.Address;
import com.bhansali.entity.Available;
import com.bhansali.entity.Business;
import com.bhansali.entity.Category;
import com.bhansali.entity.Gender;
import com.bhansali.entity.Inventory;
import com.bhansali.entity.InventoryDetail;
import com.bhansali.entity.Type;
import com.bhansali.repository.AddressRepository;

@Service
@Transactional
public class DBUtil implements Utility {

	@Autowired
	private GeneralDao generalDao;

	@Autowired
	private AddressRepository addressRepository;

	public Available getAvailable(Inventory inventory) {

		if (inventory != null) {
			if (inventory.getId() != null)
				return generalDao.getAvailableRepository().findByInventory(inventory);
		}
		return null;
	}

	/**
	 * Get Inventory with some existing fields from Database
	 * 
	 * @param inventory
	 * @return
	 */
	public Inventory getInventory(Inventory inventory) {

		// ********** Need to Improve While Making Process Consignment
		// ******************
		// As Processed goods may have same inventory item with different inventory
		// details

		if (inventory != null) {
			// Get Inventory With Same Detail ( Item Number May Differ )
			InventoryDetail persistedInventoryDetail = getInventoryDetail(inventory.getDetail());

			// Set The Existing Details to New Invenory ( Just Detail, Not Item Number )
			if (persistedInventoryDetail != null)
				inventory.setDetail(persistedInventoryDetail);
			else
				inventory.setId(null); // Setting id to to null will cause a new Inventory, Intended.
			// May need to change while implementing Consignment PROCESS
		}

		return inventory;
	}

	/**
	 * Get Existing Inventory Details from Database
	 * 
	 * @param detail
	 * @return
	 */
	public InventoryDetail getInventoryDetail(InventoryDetail detail) {

		InventoryDetail persistedInventoryDetail = null;
		if (detail != null) {
			persistedInventoryDetail = generalDao.getInventoryDetailRepository().findTopByAndNameAndTypeAndColorAndSize(
					detail.getName(), detail.getType(), detail.getColor(), detail.getSize());
		}
		return persistedInventoryDetail;
	}

	/**
	 * Get Existing Address From Database
	 * 
	 * @param address
	 * @return
	 */
	public Address getAddress(Address address) {

		if (address != null) {
			Address persistedAddress = addressRepository.findTopBySoilAndZipAndDescription(address.getSoil(),
					address.getZip(), address.getDescription());

			if (persistedAddress != null)
				return persistedAddress;
		}

		return address;
	}

	public Business getBusiness(Business business) {

		if (business != null) {
			Business persistedBusiness = generalDao.getBusinessRepository().findByName(business.getName());

			if (persistedBusiness != null)
				return persistedBusiness;
		}
		return business;
	}

	public Category getCategory(Category category) {

		if (category != null) {
			Category persistedCategory = generalDao.getCategoryRepository().findByName(category.getName());

			if (persistedCategory != null)
				return persistedCategory;
		}
		return category;
	}

	public Gender getGender(Gender gender) {

		if (gender != null) {
			Gender persistedGender = generalDao.getGenderRepository().findByName(gender.getName());

			if (persistedGender != null)
				return persistedGender;
		}
		return gender;
	}

	public Type getType(Type type) {

		if (type != null) {
			Type persistedType = generalDao.getTypeRepository().findByName(type.getName());

			if (persistedType != null)
				return persistedType;
		}
		return type;
	}

}