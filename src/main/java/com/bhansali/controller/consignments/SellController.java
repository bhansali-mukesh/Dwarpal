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
public class SellController {

	@Autowired
	ConsignmentService consignmentService;

	@Autowired
	ConsignmentController consignmentController;

	@Autowired
	LoginController loginController;

	public static final String SEND = "send";
	public static final String SENT_SUCCESSFULLY = "SENT SUCCESSFULLY";

	@PostMapping("/" + ConsignmentController.ENTITY + "/" + SEND)
	public String sendFrom(HttpSession session, Model model, Consignment consignment, Integer consignmentType) {

		Login login = LoginController.getLogin(session);
		if (!LoginService.basicValidation(login, model))
			return CONSTANT.LOGIN_PAGE;

		if (!(loginController.isWatchMan(login, model)))
			return CONSTANT.HOME_PAGE;

		consignment.setType(Consignment.Type.SELL);
		model = consignmentController.setConsignmentModel(session, model, consignment, SEND, "", "");
		return ConsignmentController.CREATE;
	}

	@PostMapping("/" + ConsignmentController.ENTITY + "/" + CONSTANT.SELL)
	public String send(HttpSession session, Model model, Consignment consignment, Lots lots) {

		Login login = (Login) session.getAttribute(CONSTANT.LOGIN);
		if (!LoginService.basicValidation(login, model) || lots.getList().size() == 0)
			return CONSTANT.LOGIN_PAGE;

		String message = CONSTANT.SELL;
		lots = GeneralService.getNonEmptyLots(lots);
		try {
			Consignment saved = consignmentService.setConsignment(consignment, lots, null);
			if (saved != null) {
				return ConsignmentController.viewConsignment(model, saved, lots, SENT_SUCCESSFULLY, SENT_SUCCESSFULLY);
			}
		} catch (Exception e) {
			message = e.getMessage();
			e.printStackTrace();
			System.out.println(message);
		}

		model = consignmentController.setConsignmentModel(session, model, consignment, CONSTANT.ERROR, message, null);
		model.addAttribute(ConsignmentController.ENTITY, consignment);
		model.addAttribute(ConsignmentController.LOTS, lots);

		return ConsignmentController.CREATE;
	}
}
