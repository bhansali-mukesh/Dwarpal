package com.bhansali.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.bhansali.dao.GeneralDao;
import com.bhansali.entity.Login;
import com.bhansali.util.CONSTANT;
import com.bhansali.util.GeneralUtility;

@Service
public class LoginService {

    @Autowired
    GeneralDao generalDao;

	public static boolean basicValidation(Login login, Model model) {

		if (login == null) {
			return false;
		}

		if (GeneralUtility.isExpired()) {
			model.addAttribute(CONSTANT.MESSAGE, "TRIAL HAS EXPIRED, PLEASE CONTACT MR. BHANSALI");
			return false;
		}
		return true;
    }
}