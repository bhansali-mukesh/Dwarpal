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
import com.bhansali.service.ConsignmentService;
import com.bhansali.service.GeneralService;
import com.bhansali.service.LoginService;
import com.bhansali.util.CONSTANT;
import com.bhansali.util.Lots;

@Controller
public class PurchaseController {

	@Autowired
	ConsignmentService consignmentService;

	@Autowired
	ConsignmentController consignmentController;

	public static final String PURCHASE = "PURCHASE";
	public static final String RECEIVE = "receive";
	public static final String RECEIVED_SUCCESSFULLY = "RECEIVED SUCCESSFULLY";

	@PostMapping("/" + ConsignmentController.ENTITY + "/" + RECEIVE)
	public String receiveFrom(HttpSession session, Model model, Consignment consignment) {

		consignment.setType(Consignment.Type.PURCHASE);
		model = consignmentController.setConsignmentModel(session, model, consignment, RECEIVE, "", null);

		return ConsignmentController.CREATE;
	}

	@PostMapping("/" + ConsignmentController.ENTITY + "/" + CONSTANT.PURCHASE)
	public String receive(HttpSession session, Model model, Consignment consignment, Lots lots) {

		Login login = LoginController.getLogin(session);
		if (!LoginService.basicValidation(login, model) || lots.getList().size() == 0)
			return CONSTANT.LOGIN_PAGE;

		lots = GeneralService.getNonEmptyLots(lots);
		String message = CONSTANT.PURCHASE;
		try {
			Consignment saved = consignmentService.setConsignment(consignment, lots, login);
			if (saved != null) {
				return ConsignmentController.viewConsignment(model, saved, lots, CONSTANT.SUCCESS,
						RECEIVED_SUCCESSFULLY);
			}
		} catch (Exception e) {
			message = e.getMessage();
			e.printStackTrace();
			System.out.println(message);
		}
		model = consignmentController.setConsignmentModel(session, model, consignment, CONSTANT.ERROR, message,
				CONSTANT.PURCHASE);
		model.addAttribute(ConsignmentController.LOTS, lots);

		return ConsignmentController.CREATE;
	}
}
