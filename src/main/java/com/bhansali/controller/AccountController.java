package com.bhansali.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.bhansali.dao.GeneralDao;
import com.bhansali.entity.Available;
import com.bhansali.entity.Consignment;
import com.bhansali.entity.Inventory;
import com.bhansali.entity.Lot;
import com.bhansali.entity.Organization;
import com.bhansali.service.AccountService;
import com.bhansali.util.CONSTANT;

@Controller
public class AccountController {

	@Autowired
	AccountService accountService;

	@Autowired
	GeneralDao generalDao;

	@Autowired
	LoginController loginController;

	public static final String ENTITY = "account";
	public static final String RECEIVED = "received";
	public static final String SENT = "sent";

	public static final String TIME = "time";
	public static final String DATE = "date";
	public static final String START_DATE = "startDate";
	public static final String END_DATE = "endDate";

	@PostMapping("/" + ENTITY + "/" + CONSTANT.HOME)
	public String home(Model model, HttpSession session) {

		if (!(loginController.isAccountant(session, model)))
			return CONSTANT.HOME_PAGE;

		model.addAttribute(CONSTANT.SWAYAM, LoginController.getOrganization(session));
		model.addAttribute(DATE, new Date());
		return ENTITY + "/" + CONSTANT.HOME;
	}

	@PostMapping("/" + ENTITY + "/" + CONSTANT.INVENTORIES)
	public String getInventories(Model model, HttpSession session) {

		Organization swayam = LoginController.getOrganization(session);

		if (!(loginController.isAccountant(session, model)))
			return CONSTANT.HOME_PAGE;

		List<Lot> receivedLots = accountService.getReceivedInventories(swayam);
		model.addAttribute(RECEIVED, receivedLots);

		List<Lot> sentLots = accountService.getSentInventories(swayam);
		model.addAttribute(SENT, sentLots);
		return ENTITY + "/" + CONSTANT.INVENTORIES;
	}

	@PostMapping("/" + ENTITY + "/" + CONSTANT.INVENTORY)
	public String getInventory(Model model, Inventory inventory) {

		List<Lot> lots = accountService.getInventory(inventory);
		model.addAttribute(CONSTANT.LOTS, lots);

		Available available = generalDao.getAvailable(inventory);
		model.addAttribute(CONSTANT.AVAILABLE, available);

		return ENTITY + "/" + CONSTANT.INVENTORY;
	}

	@PostMapping("/" + ENTITY + "/" + CONSTANT.ORGANIZATIONS)
	public String getOrganizations(Model model, HttpSession session) {

		if (!(loginController.isAccountant(session, model)))
			return CONSTANT.HOME_PAGE;

		model.addAttribute(CONSTANT.ORGANIZATIONS, generalDao.getAllOrganizations());
		return CONSTANT.VIEW + "/" + CONSTANT.ORGANIZATIONS;
	}

	@PostMapping("/" + ENTITY + "/" + CONSTANT.ORGANIZATION)
	public String getOrganization(Model model, Organization organization) {

		model.addAttribute(CONSTANT.ORGANIZATION, organization);

		List<Consignment> receivedConsignments = generalDao.getReceivedConsignment(organization);
		List<Lot> receivedLots = generalDao.getLots(receivedConsignments);
		model.addAttribute(RECEIVED, receivedLots);

		List<Consignment> sentConsignments = generalDao.getSentConsignment(organization);
		List<Lot> sentLots = generalDao.getLots(sentConsignments);
		model.addAttribute(SENT, sentLots);

		return ENTITY + "/" + CONSTANT.ORGANIZATION;
	}

	@PostMapping("/" + ENTITY + "/" + CONSTANT.ORGANIZATION + "/" + TIME)
	public String getConsignmentByDate(Model model, HttpSession session, Date startDate, Date endDate) {

		if (!(loginController.isAccountant(session, model)))
			return CONSTANT.HOME_PAGE;

		model.addAttribute(START_DATE, startDate);
		model.addAttribute(END_DATE, endDate);

		List<Consignment> consignments = generalDao.getConsignmentBetweenDate(startDate, endDate);
		List<Lot> lots = generalDao.getLots(consignments);
		model.addAttribute(CONSTANT.LOTS, lots);

		return ENTITY + "/" + TIME + "/" + CONSTANT.ORGANIZATION;
	}

	// Need Changes
	@PostMapping("/" + ENTITY + "/" + CONSTANT.INVENTORY + "/" + TIME)
	public String getConsignmentByDateFrom(Model model, HttpSession session, String start, String end)
			throws ParseException {
		String[] starts = start.split("-");

		if (starts[0].length() > 3)
			return getConsignmentByDate(model, session, new SimpleDateFormat("yyyy-MM-dd").parse(start),
					new SimpleDateFormat("yyyy-MM-dd").parse(end));

		return getConsignmentByDate(model, session, new SimpleDateFormat("dd-MM-yyyy").parse(start),
				new SimpleDateFormat("dd-MM-yyyy").parse(end));
	}
}