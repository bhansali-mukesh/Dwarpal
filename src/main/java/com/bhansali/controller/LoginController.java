package com.bhansali.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bhansali.entity.Login;
import com.bhansali.entity.Organization;
import com.bhansali.entity.Role;
import com.bhansali.service.AccessControlService;
import com.bhansali.service.GeneralService;
import com.bhansali.util.CONSTANT;

@Controller
public class LoginController {

	@Autowired
	GeneralService generalService;

	@Autowired
	AccessControlService accessControlService;

	public static final String ENTITY = CONSTANT.LOGIN;

	public static final String REGISTER = ENTITY + "/register";
	public static final String ROLES = "roles";

	@GetMapping("/" + CONSTANT.CREATE + "/" + ENTITY)
	public String add(Model model, Login login) {

		if (login == null) {
			login = new Login();
		}

		model = setModel(model, login);
		return REGISTER;
	}

	@PostMapping("/" + REGISTER)
	public String save(HttpServletRequest request, @ModelAttribute("login") @Validated Login login,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			model.addAttribute(CONSTANT.TITLE, ENTITY.toUpperCase() + " " + CONSTANT.HAS_ERROR);
			model = setModel(model, login);
			return REGISTER;
		}

		if (!(login.getPassword().equals(login.getConfirmPassword()))
				|| !(login.getPowerPhrase().equals(login.getConfirmPowerPhrase()))) {
			model.addAttribute(CONSTANT.TITLE, "Password or PowerPhrase can not be confirmed");
			model = setModel(model, login);
			return REGISTER;
		}

		// Temporary for Free Version
		login.setExpiry(new Date(120, 2, 07));

		generalService.save(login);
		return login(request, login, model);
	}

	@PostMapping("/" + ENTITY)
	public String login(HttpServletRequest request, Login login, Model model) {

		Login persistedLogin = generalService.getLogin(login.getUsername(), login.getPassword(),
				login.getOrganization());

		String error_message = null;
		if (persistedLogin == null) {
			error_message = "Login Failed";
		}

		if (persistedLogin.getExpiry().before(new Date())) {
			error_message = "Login Expired";
		}

		if (error_message != null) {
			model = setModel(model, login);
			model.addAttribute(CONSTANT.TITLE, error_message);
			model.addAttribute(CONSTANT.MESSAGE, error_message);
			return CONSTANT.LOGIN_PAGE;
		}

		request.getSession().setAttribute(ENTITY, login);
		model.addAttribute(ENTITY, persistedLogin);

		return ENTITY + "/" + CONSTANT.HOME;
	}

	// Temporary Method for getting rid of login
	// Used in Home Button
	@PostMapping("/l" + ENTITY)
	public String hackIn(Model model) {

		model.addAttribute(ENTITY, generalService.getLogin(1));
		return ENTITY + "/" + CONSTANT.HOME;
	}

	@GetMapping("/" + ENTITY)
	public String login(Model model, Login login) {

		model = setModel(model, login);
		return CONSTANT.LOGIN_PAGE;
	}

	@Transactional
	private List<Role> getDefaultRoles() {
		List<Role> roles = new ArrayList<Role>();

		// Roles are Multiplication of Prime Numbers ( That May Be Defined in Other
		// Roles )
		roles.add(new Role("OMNI", 385, "Can Do Anything"));
		roles.add(new Role("DWARPAL", 5, "Can Buy, Process and Sell Goods"));
		roles.add(new Role("MANAGER", 7, "Can View Avialble Inventory, and Sent and Received Consignments"));
		roles.add(new Role("ACCOUNTANT", 11, "Can See Accounts"));

		for (Role role : roles) {
			generalService.save(role);
		}

		return generalService.getRoles();
	}

	// @GetMapping("/person/login")
	// public static String person( Model model) {
	// return PersonController.LOGIN;
	// }
	//
	@GetMapping("/organization/" + ENTITY)
	public static String member(Model model) {
		return "login/organization_login";
	}

	@GetMapping("/admin/" + ENTITY)
	public static String admin(Model model) {
		return "login/admin_login";
	}

	public static Login getLogin(HttpSession session) {
		return (Login) session.getAttribute(ENTITY);
	}

	public static Organization getOrganization(HttpSession session) {
		return getLogin(session).getOrganization();
	}

	public boolean isAccountant(HttpSession session, Model model) {

		Login login = getLogin(session);
		if (login == null)
			return false;

		return isAccountant(login, model);
	}

	public boolean isAccountant(Login login, Model model) {

		if (!(accessControlService.isAccountant(login))) {
			model.addAttribute(CONSTANT.MESSAGE, CONSTANT.NO_PERMISSION);
			model.addAttribute(ENTITY, login);
			return false;
		}
		return true;
	}

	public boolean isManager(HttpSession session, Model model) {

		Login login = getLogin(session);
		if (login == null)
			return false;

		return isManager(login, model);
	}

	public boolean isManager(Login login, Model model) {

		if (!(accessControlService.isManager(login))) {
			model.addAttribute(CONSTANT.MESSAGE, CONSTANT.NO_PERMISSION);
			model.addAttribute(ENTITY, login);
			return false;
		}
		return true;
	}

	public boolean isWatchMan(HttpSession session, Model model) {

		Login login = getLogin(session);
		if (login == null)
			return false;

		return isWatchMan(login, model);
	}

	public boolean isWatchMan(Login login, Model model) {

		if (!(accessControlService.isWatchMan(login))) {
			model.addAttribute(CONSTANT.MESSAGE, CONSTANT.NO_PERMISSION);
			model.addAttribute(ENTITY, login);
			return false;
		}
		return true;
	}

	private Model setModel(Model model, Login login) {

		model.addAttribute(ENTITY, login);
		model.addAttribute(CONSTANT.ORGANIZATIONS, generalService.getOrganizations());

		List<Role> roles = generalService.getRoles();

		if (roles == null || roles.size() == 0)
			roles = getDefaultRoles();

		model.addAttribute(ROLES, generalService.getRoles());
		return model;
	}

}