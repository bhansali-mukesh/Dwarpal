package com.bhansali.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhansali.entity.Soil;

public interface SoilRepository extends JpaRepository<Soil, Integer> {
	Soil findByName(String name);
}
