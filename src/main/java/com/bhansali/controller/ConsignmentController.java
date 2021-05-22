package com.bhansali.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.bhansali.entity.Consignment;
import com.bhansali.entity.Lot;
import com.bhansali.entity.Organization;
import com.bhansali.service.ConsignmentService;
import com.bhansali.service.GeneralService;
import com.bhansali.util.CONSTANT;
import com.bhansali.util.Lots;

@Controller
public class ConsignmentController {

	@Autowired
	GeneralService generalService;

	public static final String ENTITY = "consignment";
	public static final String ENTITIES = "consignments";
	public static final String LOTS = "lots";
	public static final String TYPE = "type";
	public static final String TYPES = "types";

	public static final String AGENTS = "agents";
	public static final String RECEIVER = "receiver";

	public static final String HISTORY = "history";

	public static final String RECEIVED_CONSIGNMENTS = "RECEIVED CONSIGNMENTS";
	public static final String SENT_CONSIGNMENTS = "SENT CONSIGNMENTS";

	public static final String RECEIVERS = "receivers";
	public static final String SENDERS = "senders";

	public static final String CREATE = CONSTANT.ENTITY + "/" + ENTITY + "/" + CONSTANT.CREATE + "/" + ENTITY;
	public static final String VIEW = CONSTANT.ENTITY + "/" + ENTITY + "/" + CONSTANT.VIEW + "/" + ENTITY;

	public static final String VIEW_ALL = CONSTANT.ENTITY + "/" + ENTITY + "/" + CONSTANT.VIEW + "/" + ENTITIES;

	/**
	 * 
	 * @param model
	 * @param consignment
	 * @return
	 */
	@PostMapping("/" + ENTITY + "/" + CONSTANT.VIEW)
	public String viewConsignment(Model model, Consignment consignment) {
		List<Lot> lotList = generalService.getLots(consignment);
		Lots lots = new Lots(lotList);

		return viewConsignment(model, consignment, lots, ENTITY, ENTITY);
	}

	/**
	 * 
	 * @param model
	 * @param consignment
	 * @param lots
	 * @param title
	 * @param message
	 * @return
	 */
	public static String viewConsignment(Model model, Consignment consignment, Lots lots, String title,
			String message) {
		model.addAttribute(CONSTANT.TITLE, title);
		model.addAttribute(CONSTANT.MESSAGE, message);

		model.addAttribute(ENTITY, consignment);
		model.addAttribute(LOTS, lots);

		return VIEW;
	}

	/**
	 * Get All Received Consignment Like Purchases, Sales Returns, Processed etc.
	 * 
	 * @param model
	 * @param login
	 * @return
	 */
	@PostMapping("/" + ENTITY + "/" + CONSTANT.RECEIVED + "/" + HISTORY)
	public String getReceivedConsignments(Model model, HttpSession session) {

		List<Consignment> consignments = generalService
				.getReceivedConsignments(LoginController.getOrganization(session));

		model.addAttribute(CONSTANT.TITLE, RECEIVED_CONSIGNMENTS);
		model.addAttribute(ENTITIES, consignments);

		return VIEW_ALL;
	}

	/**
	 * Get All Sent Consignments Like Sell, Sales Return, In Process etc
	 * 
	 * @param model
	 * @param login
	 * @return
	 */
	@PostMapping("/" + ENTITY + "/" + CONSTANT.SENT + "/" + HISTORY)
	public String getSentConsignments(Model model, HttpSession session) {

		List<Consignment> consignments = generalService.getSentConsignments(LoginController.getOrganization(session));

		model.addAttribute(CONSTANT.TITLE, SENT_CONSIGNMENTS);
		model.addAttribute(ENTITIES, consignments);

		return VIEW_ALL;
	}

	/**
	 * Type Like, Purchase, Sell, Edit, Delete, Purchase Return, Sales Return, In
	 * Process, Processed etc.
	 * 
	 * @param model
	 * @param login
	 * @param type
	 * @return
	 */
	@PostMapping("/" + ENTITY + "/" + TYPE)
	public String getConsignmentsOfType(Model model, Consignment.Type type) {

		List<Consignment> consignments = generalService.getConsignmentsOfType(type);

		model.addAttribute(CONSTANT.TITLE, ENTITIES);
		model.addAttribute(ENTITIES, consignments);

		return VIEW_ALL;
	}

	/**
	 * 
	 * @param session
	 * @param model
	 * @param consignment
	 * @param title
	 * @param message
	 * @param action
	 * @return
	 */
	public Model setConsignmentModel(HttpSession session, Model model, Consignment consignment, String title,
			String message, String action) {

		Lots lots;
		if (consignment.getId() == null) {

			// A New Transaction is beginning which may have any details i.e. Purchase or
			// Sell
			// Not Like Edit, Delete, Purchase Return or Sales Return etc.
			consignment = ConsignmentService.getBlankConsignment(consignment);
			lots = new Lots(GeneralService.getBlankLots());
		} else {
			lots = new Lots(generalService.getLots(consignment));
		}

		return setModel(model, session, consignment, lots, title, message, action);
	}

	/**
	 * 
	 * @param model
	 * @param session
	 * @param consignment
	 * @param lots
	 * @param title
	 * @param message
	 * @param action
	 * @return
	 */
	public Model setModel(Model model, HttpSession session, Consignment consignment, Lots lots, String title,
			String message, String action) {

		Organization swayam = LoginController.getOrganization(session);

		List<Organization> receivers = new ArrayList<Organization>();
		List<Organization> senders = new ArrayList<Organization>();

		boolean receiver = false;
		if (consignment.getReceiver() != null) {
			if (swayam.getId() == consignment.getReceiver().getId())
				receiver = true;
		} else {
			Consignment.Type type = consignment.getType();
			if (type == Consignment.Type.PURCHASE)
				receiver = true;
		}

		if (receiver) {
			receivers.add(swayam);
			senders.addAll(generalService.getParties(swayam));
		} else {
			receivers.addAll(generalService.getParties(swayam));
			senders.add(swayam);
		}

		if (action == null || action == "")
			action = consignment.getType().toString();

		model.addAttribute(CONSTANT.ACTION, action);
		model.addAttribute(CONSTANT.TITLE, title);
		model.addAttribute(CONSTANT.MESSAGE, message);

		model.addAttribute(CONSTANT.SWAYAM, swayam);
		model.addAttribute(RECEIVERS, receivers);
		model.addAttribute(SENDERS, senders);
		model.addAttribute(TYPES, generalService.getTypes());
		model.addAttribute(AGENTS, generalService.getAgents());
		model.addAttribute(ENTITY, consignment);
		model.addAttribute(LOTS, lots);

		return model;
	}

}