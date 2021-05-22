package com.bhansali.controller.consignments;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.bhansali.controller.ConsignmentController;
import com.bhansali.controller.LoginController;
import com.bhansali.entity.Consignment;
import com.bhansali.entity.Login;
import com.bhansali.entity.Organization;
import com.bhansali.service.GeneralService;
import com.bhansali.service.LoginService;
import com.bhansali.util.CONSTANT;
import com.bhansali.util.Lots;

@Controller
public class ReturnController {

	@Autowired
	GeneralService generalService;

	@Autowired
	ConsignmentController consignmentController;

	public static final String RETURN_GOODS = "return";

	@PostMapping("/" + ConsignmentController.ENTITY + "/" + RETURN_GOODS)
	public String goodsReturn(HttpSession session, Model model, Consignment consignment) {

		System.out.println("Returning Goods");

		Login login = LoginController.getLogin(session);
		if (!LoginService.basicValidation(login, model))
			return CONSTANT.LOGIN_PAGE;

		Organization swayam = login.getOrganization();
		Organization receiver = consignment.getReceiver();
		Organization sender = consignment.getSender();

		if (swayam.getId() != sender.getId() && swayam.getId() != receiver.getId()) {
			String notYours = "This is not your Consignment";
			model.addAttribute(CONSTANT.TITLE, notYours);
			model.addAttribute(CONSTANT.MESSAGE, notYours);
			return ConsignmentController.CREATE;
		}

		String action;
		Consignment.Type Type;
		if (swayam.getId() == receiver.getId()) {

			Type = Consignment.Type.PURCHASE_RETURN;

			// Sell Just Means here is, Consignment is Going Out ( Purchase Return )
			action = CONSTANT.SELL;
		} else {

			Type = Consignment.Type.SALES_RETURN;

			// Purchase Just Means here is, Consignment is Coming in ( SALES Return )
			action = CONSTANT.PURCHASE;
		}

		Lots clonnedLots = new Lots(generalService.getClonnedLots(consignment));
		Consignment clonnedConsignment = null;

		if (clonnedLots.getList().size() > 0) {
			// Get Clonned Consignment From Lots
			// All Lots have the Same Consignment, So It's OK to Take First One
			clonnedConsignment = clonnedLots.getList().get(0).getConsignment();
		}

		// Set New Consignment Type, Calculated Above
		clonnedConsignment.setType(Type);

		// Set History Of Consignment
		clonnedConsignment.setPreviousConsignment(consignment);

		// Change Parties as we are returning goods
		clonnedConsignment.setSender(receiver);
		clonnedConsignment.setReceiver(sender);

		model = consignmentController.setModel(model, session, clonnedConsignment, clonnedLots, RETURN_GOODS, "",
				action);

		model.addAttribute(ConsignmentController.ENTITY, clonnedConsignment);
		model.addAttribute(ConsignmentController.LOTS, clonnedLots);

		return ConsignmentController.CREATE;
	}

}
