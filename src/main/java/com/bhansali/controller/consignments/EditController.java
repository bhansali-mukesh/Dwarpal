package com.bhansali.controller.consignments;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.bhansali.controller.ConsignmentController;
import com.bhansali.entity.Consignment;
import com.bhansali.entity.Login;
import com.bhansali.service.ConsignmentService;
import com.bhansali.service.LoginService;
import com.bhansali.util.CONSTANT;
import com.bhansali.util.Lots;

@Controller
public class EditController {

	@Autowired
	ConsignmentService consignmentService;

	@Autowired
	ConsignmentController consignmentController;

	public static final String EDIT = "edit";
	public static final String EDITED = "EDITED SUCCESSFULLY";

	@PostMapping("/" + ConsignmentController.ENTITY + "/" + EDIT)
	public String editForm(HttpSession session, Model model, Consignment consignment) {

		// consignment.setType(Consignment.Type.EDIT);
		model = consignmentController.setConsignmentModel(session, model, consignment, EDIT, "", EDIT.toUpperCase());
		return ConsignmentController.CREATE;
	}

	@PostMapping("/" + ConsignmentController.ENTITY + "/" + CONSTANT.EDIT)
	public String edit(HttpSession session, Model model, Consignment consignment, Lots lots) {

		System.out.println("Editing Consignment");

		Login login = (Login) session.getAttribute(CONSTANT.LOGIN);
		if (!LoginService.basicValidation(login, model))
			return CONSTANT.LOGIN_PAGE;

		String message = consignment.getType().toString();
		try {
			consignment.setType(Consignment.Type.EDIT);
			Consignment saved = consignmentService.setConsignment(consignment, lots, login);
			if (saved != null) {
				return ConsignmentController.viewConsignment(model, saved, lots, EDITED, EDITED);
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
