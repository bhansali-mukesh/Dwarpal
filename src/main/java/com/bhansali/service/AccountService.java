package com.bhansali.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bhansali.dao.GeneralDao;
import com.bhansali.entity.Consignment;
import com.bhansali.entity.Inventory;
import com.bhansali.entity.Lot;
import com.bhansali.entity.Organization;

@Service
public class AccountService {

    @Autowired
    GeneralDao generalDao;

	public List<Lot> getReceivedInventories(Organization organization) {

		try 
		{
			List<Consignment> receivedConsignments = generalDao.getReceivedConsignment(organization);
			return generalDao.getLots(receivedConsignments);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return null;
    }
	
	public List<Lot> getSentInventories(Organization organization) {

		try 
		{
			List<Consignment> sentConsignments = generalDao.getSentConsignment(organization);
			return generalDao.getLots(sentConsignments);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return null;
    }
	
	public List<Lot> getInventory(Inventory inventory) {

		try 
		{
			return generalDao.getLots(inventory);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return null;
    }
}