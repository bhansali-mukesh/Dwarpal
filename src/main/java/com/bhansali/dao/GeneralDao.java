package com.bhansali.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.bhansali.repository.AvailableRepository;
import com.bhansali.repository.BusinessRepository;
import com.bhansali.repository.CategoryRepository;
import com.bhansali.repository.ConsignmentRepository;
import com.bhansali.repository.GenderRepository;
import com.bhansali.repository.InventoryDetailRepository;
import com.bhansali.repository.InventoryRepository;
import com.bhansali.repository.LoginRepository;
import com.bhansali.repository.LotRepository;
import com.bhansali.repository.OrganizationRepository;
import com.bhansali.repository.PersonRepository;
import com.bhansali.repository.RoleRepository;
import com.bhansali.repository.SoilRepository;
import com.bhansali.repository.TypeRepository;
import com.bhansali.repository.WIPRepository;

@Service
@org.springframework.transaction.annotation.Transactional
public class GeneralDao {

	@Autowired
	AvailableRepository availableRepository;

	@Autowired
	BusinessRepository businessRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ConsignmentRepository consignmentRepository;

	@Autowired
	GenderRepository genderRepository;

	@Autowired
	InventoryRepository inventoryRepository;

	@Autowired
	InventoryDetailRepository inventoryDetailRepository;

	@Autowired
	LotRepository lotRepository;

	@Autowired
	LoginRepository loginRepository;

	@Autowired
	OrganizationRepository organizationRepository;

	@Autowired
	PersonRepository personRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	SoilRepository soilRepository;

	@Autowired
	TypeRepository typeRepository;

	@Autowired
	WIPRepository wipRepository;

	// Available
	public void saveOrUpdate(Available available) {
		availableRepository.save(available);
	}

	public List<Available> getAllAvailable() {
		return availableRepository.findAll();
	}

	public Available getAvailable(Inventory inventory) {
		if (inventory.getId() == null)
			return null;
		return availableRepository.findByInventory(inventory);
	}

	public void removeAvailable(Available available) {
		availableRepository.delete(available);
	}

	// Business
	public void saveOrUpdate(Business business) {
		businessRepository.save(business);
	}

	public Business getBusiness(Integer id) {
		return businessRepository.findOne(id);
	}

	public List<Business> getBusinesses() {
		return businessRepository.findAll();
	}

	public void deleteBusiness(Business business) {
		businessRepository.delete(business);
	}

	// Category
	public void saveOrUpdate(Category category) {
		categoryRepository.save(category);
	}

	public Category getCategory(Integer id) {
		return categoryRepository.findOne(id);
	}

	public void deleteCategory(Category category) {
		categoryRepository.delete(category);
	}

	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	// Consignment
	public void saveOrUpdate(Consignment consignment) {
		consignmentRepository.save(consignment);
	}

	public Consignment getConsignment(Integer id) {
		return consignmentRepository.findOne(id);
	}

	public List<Consignment> getReceivedConsignment(Organization organization) {
		return consignmentRepository.findByReceiverAndDeleted(organization, false);
	}

	public List<Consignment> getSentConsignment(Organization organization) {
		return consignmentRepository.findBySenderAndDeleted(organization, false);
	}

	public List<Consignment> getConsignmentsOfType(Consignment.Type type) {
		return consignmentRepository.findByType(type);
	}

	public List<Consignment> getConsignmentBetweenDate(Date startDate, Date endDate) {
		return consignmentRepository.findByDateBetween(startDate, endDate);
	}

	// Gender
	public void saveOrUpdate(Gender gender) {
		genderRepository.save(gender);
	}

	public List<Gender> getAllGenders() {
		return genderRepository.findAll();
	}

	public Gender getGender(Integer id) {
		return genderRepository.findOne(id);
	}

	public Gender findGenderByName(String name) {
		return genderRepository.findByName(name);
	}

	public List<Gender> findAllGenders() {
		return genderRepository.findAll();
	}

	public void deleteGender(Integer id) {
		genderRepository.delete(id);
	}

	public Gender findGenderById(Integer id) {
		return genderRepository.findOne(id);
	}

	// Inventory
	public void saveOrUpdate(Inventory inventory) {
		inventoryRepository.save(inventory);
	}

	public Inventory getInventory(Integer id) {
		return inventoryRepository.findOne(id);
	}

	public List<Inventory> getInventoriesByItem(Integer item) {
		return inventoryRepository.findByItem(item);
	}

	public List<Inventory> getInventoriesByDetail(InventoryDetail detail) {
		return inventoryRepository.findByDetail(detail);
	}

	public Inventory getInventoryByItemAndDetail(Integer item, InventoryDetail detail) {
		return inventoryRepository.findByItemAndDetail(item, detail);
	}

	public List<Inventory> getInventoryByDetail(InventoryDetail inventoryDetail) {
		return inventoryRepository.findByDetail(inventoryDetail);
	}

	// InventoryDetail

	public InventoryDetail getInventoryDetail(String name, Type type, String color, Double size) {
		return inventoryDetailRepository.findTopByAndNameAndTypeAndColorAndSize(name, type, color, size);
	}

	// Lot
	public void saveOrUpdate(Lot lot) {
		lotRepository.save(lot);
	}

	public Lot getLot(Integer id) {
		return lotRepository.findOne(id);
	}

	public List<Lot> getLots(int inventoryItem) {
		return lotRepository.findLotByInventoryItem(inventoryItem);
	}

	public List<Lot> getLots(Inventory inventory) {
		return lotRepository.findLotByInventory(inventory);
	}

	public List<Lot> getLots(Consignment consignment) {
		return lotRepository.findLotByConsignment(consignment);
	}

	public List<Lot> getLots(List<Consignment> consignments) {
		return lotRepository.findLotByConsignmentIn(consignments);
	}

	// public List<Inventory> getInventories(List<Consignment> consignments) {
	// return lotRepository.findInventoryByConsignmentIn(consignments);
	// }

	public List<Inventory> getInventories(List<Consignment> consignments) {
		List<Lot> lots = getLots(consignments);

		List<Inventory> inventories = new ArrayList<Inventory>();

		for (Lot lot : lots) {
			inventories.add(lot.getInventory());
		}

		return inventories;
	}

	// Login

	public void saveOrUpdate(Login login) {
		loginRepository.save(login);
	}

	public Login getLogin(Integer id) {
		return loginRepository.findOne(id);
	}

	public Login getLogin(String username, String password, Organization organization) {
		return loginRepository.findLoginByUsernameAndPasswordAndOrganization(username, password, organization);
	}

	// Organization
	public void saveOrUpdate(Organization organization) {
		organizationRepository.save(organization);
	}

	public Organization getOrgnization(Integer id) {
		return organizationRepository.findOne(id);
	}

	public void deleteOrgnization(Organization organization) {
		organizationRepository.delete(organization);
	}

	public List<Organization> getAllOrganizations() {
		return organizationRepository.findAll();
	}

	// Person
	public void saveOrUpdate(Person person) {
		personRepository.save(person);
	}

	public Person getPerson(Integer id) {
		return personRepository.findOne(id);
	}

	public List<Person> getAgents() {
		return personRepository.findPersonByAgent(true);
	}

	public void deletePerson(Person person) {
		personRepository.delete(person);
	}

	public List<Person> getPersons() {
		return personRepository.findAll();
	}

	// Role

	public void saveOrUpdate(Role role) {
		roleRepository.save(role);
	}

	public Role getRole(Integer id) {
		return roleRepository.findOne(id);
	}

	public Role getRole(String name) {
		return roleRepository.findRoleByName(name);
	}

	public List<Role> getRoles() {
		return roleRepository.findAll();
	}

	// Soil
	public void saveOrUpdate(Soil soil) {
		soilRepository.save(soil);
	}

	public Soil getSoil(Integer id) {
		return soilRepository.findOne(id);
	}

	public Soil findSoilByName(String name) {
		return soilRepository.findByName(name);
	}

	public List<Soil> getAllSoils() {
		return soilRepository.findAll();
	}

	public void deleteSoil(Soil soil) {
		soilRepository.delete(soil);
	}

	// Type
	public void saveOrUpdate(Type type) {
		typeRepository.save(type);
	}

	public Type getType(Integer id) {
		return typeRepository.findOne(id);
	}

	public void deleteType(Type type) {
		typeRepository.delete(type);
	}

	public List<Type> getTypes() {
		return typeRepository.findAll();
	}

	// WIP
	public void saveOrUpdate(WIPInventory wipInventory) {
		wipRepository.save(wipInventory);
	}

	public List<WIPInventory> getAllWIP() {
		return wipRepository.findAll();
	}

	public WIPInventory getWIPByInventory(Inventory inventory) {
		return wipRepository.findByInventory(inventory);
	}

	public WIPInventory getWIPByInventoryItem(Integer item) {
		return wipRepository.findByInventoryItem(item);
	}

	public void removeWIP(WIPInventory wip) {
		wipRepository.delete(wip);
	}

	// Getters
	public AvailableRepository getAvailableRepository() {
		return availableRepository;
	}

	public BusinessRepository getBusinessRepository() {
		return businessRepository;
	}

	public CategoryRepository getCategoryRepository() {
		return categoryRepository;
	}

	public ConsignmentRepository getConsignmentRepository() {
		return consignmentRepository;
	}

	public GenderRepository getGenderRepository() {
		return genderRepository;
	}

	public InventoryRepository getInventoryRepository() {
		return inventoryRepository;
	}

	public InventoryDetailRepository getInventoryDetailRepository() {
		return inventoryDetailRepository;
	}

	public LotRepository getLotRepository() {
		return lotRepository;
	}

	public OrganizationRepository getOrganizationRepository() {
		return organizationRepository;
	}

	public PersonRepository getPersonRepository() {
		return personRepository;
	}

	public SoilRepository getSoilRepository() {
		return soilRepository;
	}

	public TypeRepository getTypeRepository() {
		return typeRepository;
	}

	public WIPRepository getWipRepository() {
		return wipRepository;
	}
}