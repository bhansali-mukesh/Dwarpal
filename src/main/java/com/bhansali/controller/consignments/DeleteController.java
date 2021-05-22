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
public class DeleteController {

	@Autowired
	GeneralService generalService;

	@Autowired
	ConsignmentService consignmentService;

	@Autowired
	ConsignmentController consignmentController;

	public static final String DELETE = CONSTANT.delete;
	public static final String DELETED_SUCCESSFULLY = "DELETED SUCCESSFULLY";

	@PostMapping("/" + ConsignmentController.ENTITY + "/" + DELETE)
	public String deleteForm(HttpSession session, Model model, Consignment consignment) {

		consignment.setType(Consignment.Type.DELETE);
		model = consignmentController.setConsignmentModel(session, model, consignment, DELETE, "", null);

		return ConsignmentController.CREATE;
	}

	@PostMapping("/" + ConsignmentController.ENTITY + "/" + CONSTANT.DELETE)
	public String delete(HttpSession session, Model model, Consignment consignment) {

		System.out.println("Deleting Consignment");

		Login login = LoginController.getLogin(session);
		if (!LoginService.basicValidation(login, model))
			return CONSTANT.LOGIN_PAGE;

		String message = consignment.getType().toString();
		Lots lots = new Lots(generalService.getLots(consignment));
		try {
			Consignment saved = consignmentService.setConsignment(consignment, lots, login);
			if (saved != null) {
				return ConsignmentController.viewConsignment(model, saved, lots, DELETED_SUCCESSFULLY,
						DELETED_SUCCESSFULLY);
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