package com.bhansali.repository;

import com.bhansali.entity.Business;
import com.bhansali.entity.Soil;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BusinessRepository extends JpaRepository<Business, Integer> {

	Business findByName( String name);
}
