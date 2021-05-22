package com.bhansali.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhansali.entity.Login;
import com.bhansali.entity.Organization;

public interface LoginRepository extends JpaRepository<Login, Integer> {

	Login findLoginByUsernameAndPasswordAndOrganization(String username, String password, Organization organization);
}