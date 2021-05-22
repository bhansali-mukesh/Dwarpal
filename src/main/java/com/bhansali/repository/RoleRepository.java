package com.bhansali.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhansali.entity.Role;


public interface RoleRepository extends JpaRepository<Role, Integer> {

	 Role findRoleByName(String name);
}
