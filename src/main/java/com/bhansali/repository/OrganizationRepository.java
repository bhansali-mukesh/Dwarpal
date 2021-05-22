package com.bhansali.repository;

import com.bhansali.entity.Organization;
import com.bhansali.entity.Soil;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrganizationRepository extends JpaRepository<Organization, Integer> {

}
