package com.bhansali.repository;

import com.bhansali.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<Gender, Integer> {

    Gender findByName(String name);
}
