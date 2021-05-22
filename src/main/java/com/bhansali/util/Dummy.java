package com.bhansali.util;

import com.bhansali.entity.Address;
import com.bhansali.entity.Business;
import com.bhansali.entity.Category;
import com.bhansali.entity.Consignment;
import com.bhansali.entity.Contact;
import com.bhansali.entity.Gender;
import com.bhansali.entity.Inventory;
import com.bhansali.entity.Lot;
import com.bhansali.entity.Organization;
import com.bhansali.entity.Person;
import com.bhansali.entity.Soil;

public class Dummy {

	public static Category CATEGORY = new Category();
	public static Business BUSINESS = new Business();
	
	public static Soil SOIL= new Soil();
	public static Address ADDRESS = new Address();
	public static Contact CONTACT = new Contact();
	
	public static Gender GENDER = new Gender();
	public static Person PERSON = new Person();

	public static Organization PARENT = new Organization();
	public static Organization ORGANIZATION = new Organization();
	
	public static Inventory INVENTORY = new Inventory();
	public static Lot LOT = new Lot();
	public static Consignment CONSIGNMENT = new Consignment();
	
	static {
		
		ADDRESS.setSoil(SOIL);
		CONTACT.setAddress(ADDRESS);

		PERSON.setGender(GENDER);
		
		ORGANIZATION.setBusiness(BUSINESS);
		ORGANIZATION.setContact(CONTACT);
		ORGANIZATION.setOwner(PERSON);
		ORGANIZATION.setParent(PARENT);
		ORGANIZATION.setCategory(CATEGORY);
		ORGANIZATION.setDeleted(false);
		
		LOT.setInventory(INVENTORY);
		LOT.setConsignment(CONSIGNMENT);
	}
}