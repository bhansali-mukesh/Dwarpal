package com.bhansali.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bhansali.dao.GeneralDao;
import com.bhansali.entity.Available;
import com.bhansali.entity.Business;
import com.bhansali.entity.Category;
import com.bhansali.entity.Consignment;
import com.bhansali.entity.Gender;
import com.bhansali.entity.Inventory;
import com.bhansali.entity.InventoryDetail;
import com.bhansali.entity.Login;
import com.bhansali.entity.Lot;
import com.bhansali.entity.Organization;
import com.bhansali.entity.Person;
import com.bhansali.entity.Role;
import com.bhansali.entity.Soil;
import com.bhansali.entity.Type;
import com.bhansali.entity.WIPInventory;
import com.bhansali.util.Lots;

@Service
@Transactional
public class GeneralService implements com.bhansali.service.Service {

	@Autowired
	GeneralDao generalDao;

	// Available
	public void save(Available available) {
		generalDao.saveOrUpdate(available);
	}

	public List<Available> getAvailables() {
		return generalDao.getAllAvailable();
	}

	public void removeAvailable(Available available) {
		generalDao.removeAvailable(available);
	}

	// Business
	public Business getBusiness(Integer id) {
		return generalDao.getBusiness(id);
	}

	public void save(Business business) {
		generalDao.saveOrUpdate(business);
	}

	public List<Business> getBusinesses() {
		return generalDao.getBusinesses();
	}

	public void delete(Business business) {
		generalDao.deleteBusiness(business);
	}

	// Category
	public Category getCategory(Integer id) {
		return generalDao.getCategory(id);
	}

	public void save(Category category) {
		generalDao.saveOrUpdate(category);
	}

	public void delete(Category category) {
		generalDao.deleteCategory(category);
	}

	public List<Category> getCategories() {
		return generalDao.getAllCategories();
	}

	// Consignment

	public void save(Consignment consignment) {
		generalDao.saveOrUpdate(consignment);
	}

	public Consignment getConsignment(Integer id) {
		return generalDao.getConsignment(id);
	}

	public List<Consignment> getReceivedConsignments(Organization organization) {

		if (organization == null)
			return null;
		return generalDao.getReceivedConsignment(organization);
	}

	public List<Consignment> getSentConsignments(Organization organization) {

		if (organization == null)
			return null;
		return generalDao.getSentConsignment(organization);
	}

	public List<Consignment> getConsignmentsOfType(Consignment.Type type) {

		return generalDao.getConsignmentsOfType(type);
	}

	// Gender
	public List<Gender> getGenders() {
		return generalDao.getAllGenders();
	}

	public void save(Gender gender) {
		generalDao.saveOrUpdate(gender);
	}

	public Gender getGender(Integer id) {
		return generalDao.findGenderById(id);
	}

	public Gender getGender(String name) {
		return generalDao.findGenderByName(name);
	}

	public void deleteGender(Integer id) {
		generalDao.deleteGender(id);
	}

	// Inventory

	public void save(Inventory inventory) {
		generalDao.saveOrUpdate(inventory);
	}

	public Inventory getInventory(Integer id) {
		return generalDao.getInventory(id);
	}

	public Available getAvailable(Inventory inventory) {
		return inventory == null ? null : generalDao.getAvailable(inventory);
	}

	public List<Inventory> getInventoriesByItem(Integer item) {
		return generalDao.getInventoriesByItem(item);
	}

	public List<Inventory> getInventoriesByDetail(InventoryDetail detail) {
		return generalDao.getInventoriesByDetail(detail);
	}

	public Inventory getInventoryByItemAndDetail(Integer item, InventoryDetail detail) {
		return generalDao.getInventoryByItemAndDetail(item, detail);
	}

	public List<Inventory> getInventoryByDetail(InventoryDetail detail) {
		return generalDao.getInventoriesByDetail(detail);
	}

	// Lot
	public void save(Lot lot) {
		generalDao.saveOrUpdate(lot);
	}

	public Lot getLot(Integer id) {
		return generalDao.getLot(id);
	}

	public List<Lot> getLots(int inventoryItem) {
		return generalDao.getLots(inventoryItem);
	}

	public List<Lot> getLots(Inventory inventory) {
		return generalDao.getLots(inventory);
	}

	public List<Lot> getLots(Consignment consignment) {

		if (consignment == null)
			return null;
		return generalDao.getLots(consignment);
	}

	// Login
	public void save(Login login) {
		generalDao.saveOrUpdate(login);
	}

	public Login getLogin(Integer id) {
		return generalDao.getLogin(id);
	}

	public Login getLogin(String username, String password, Organization organization) {
		return generalDao.getLogin(username, password, organization);
	}

	// Organization
	public void save(Organization organization) {
		generalDao.saveOrUpdate(organization);
	}

	public Organization getOrganization(Integer id) {
		return generalDao.getOrgnization(id);
	}

	public void delete(Organization organization) {
		generalDao.deleteOrgnization(organization);
	}

	public List<Organization> getOrganizations() {
		return generalDao.getAllOrganizations();
	}

	// Person
	public void save(Person person) {
		generalDao.saveOrUpdate(person);
	}

	public List<Person> getPersons() {
		return generalDao.getPersons();
	}

	public List<Person> getAgents() {
		return generalDao.getAgents();
	}

	public Person getPerson(Integer id) {
		return generalDao.getPerson(id);
	}

	public void delete(Person person) {
		generalDao.deletePerson(person);
	}

	// Role

	public void save(Role role) {
		generalDao.saveOrUpdate(role);
	}

	public Role getRole(Integer id) {
		return generalDao.getRole(id);
	}

	public Role getRole(String name) {
		return generalDao.getRole(name);
	}

	public List<Role> getRoles() {
		return generalDao.getRoles();
	}

	// Soil
	public void save(Soil soil) {
		generalDao.saveOrUpdate(soil);
	}

	public List<Soil> getSoils() {
		return generalDao.getAllSoils();
	}

	public Soil getSoil(Integer id) {
		return generalDao.getSoil(id);
	}

	public void delete(Soil soil) {
		generalDao.deleteSoil(soil);
	}

	public Soil getSoil(String name) {
		return generalDao.findSoilByName(name);
	}

	// Type
	public Type getType(Integer id) {
		return generalDao.getType(id);
	}

	public void delete(Type type) {
		generalDao.deleteType(type);
	}

	public void save(Type type) {
		generalDao.saveOrUpdate(type);
	}

	public List<Type> getTypes() {
		return generalDao.getTypes();
	}

	// WIP
	public void save(WIPInventory wip) {
		generalDao.saveOrUpdate(wip);
	}

	public List<WIPInventory> getWIPs() {
		return generalDao.getAllWIP();
	}

	public WIPInventory getWIPByInventory(Inventory inventory) {
		return generalDao.getWIPByInventory(inventory);
	}

	public WIPInventory getWIPByInventoryItem(Integer item) {
		return generalDao.getWIPByInventoryItem(item);
	}

	public void removeWIP(WIPInventory wip) {
		generalDao.removeWIP(wip);
	}

	// Getter
	public GeneralDao getGeneralDao() {
		return generalDao;
	}

	public static List<Lot> getBlankLots() {
		ArrayList<Lot> lotList = new ArrayList<Lot>();
		lotList.add(com.bhansali.util.Dummy.LOT);
		return lotList;
	}

	public List<Organization> getParties(Organization swayam) {

		// Get All Organizations
		List<Organization> organizations = getOrganizations();

		List<Organization> parties = new ArrayList<>();
		for (Organization organization : organizations) {
			// Remove self
			if (organization.getId() != swayam.getId()) {
				parties.add(organization);
			}
		}
		return parties;
	}

	public static Lots getNonEmptyLots(Lots lots) {
		List<Lot> list = new ArrayList<>();

		// Get Not Null List Of Lots
		for (Lot lot : lots.getList()) {
			if (lot != null && lot.getInventory() != null)
				list.add(lot);
		}

		return new Lots(list);
	}

	public List<Lot> getClonnedLots(Consignment consignment) {

		Consignment clonnedConsignment = consignment.getClone();
		List<Lot> clonnedLots = new ArrayList<>();

		List<Lot> lots = getLots(consignment);
		Lot clonnedLot;
		for (Lot lot : lots) {
			clonnedLot = lot.getClone();
			clonnedLot.setConsignment(clonnedConsignment);
			clonnedLots.add(clonnedLot);
		}
		return clonnedLots;
	}

}