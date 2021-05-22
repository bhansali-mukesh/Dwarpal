package com.bhansali.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bhansali.entity.Login;
import com.bhansali.entity.Role;

@Service
public class AccessControlService {

	@Autowired
	GeneralService generalService;

	private Role dwarpal;
	private Role prabandhak;
	private Role lekhak;

	public boolean isWatchMan(Login login) {
		return true;// login.getRole() % getDwarpal().getKey() == 0;
	}

	public boolean isManager(Login login) {
		return true;// login.getRole() % getPrabandhak().getKey() == 0;
	}

	public boolean isAccountant(Login login) {
		return true;// login.getRole() % getLekhak().getKey() == 0;
	}

	public Role getDwarpal() {
		if (dwarpal == null)
			dwarpal = generalService.getRole("DWARPAL");

		return dwarpal;
	}

	public void setDwarpal(Role dwarpal) {
		this.dwarpal = dwarpal;
	}

	public Role getPrabandhak() {
		if (prabandhak == null)
			prabandhak = generalService.getRole("PRABANDHAK");

		return prabandhak;
	}

	public void setPrabandhak(Role prabandhak) {
		this.prabandhak = prabandhak;
	}

	public Role getLekhak() {
		if (lekhak == null)
			lekhak = generalService.getRole("LEKHAK");

		return lekhak;
	}

	public void setLekhak(Role lekhak) {
		this.lekhak = lekhak;
	}
}