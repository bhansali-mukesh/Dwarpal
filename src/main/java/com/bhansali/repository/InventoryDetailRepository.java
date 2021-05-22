package com.bhansali.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhansali.entity.InventoryDetail;
import com.bhansali.entity.Type;


public interface InventoryDetailRepository extends JpaRepository<InventoryDetail, Integer> {

	InventoryDetail findTopByAndNameAndTypeAndColorAndSize(String name,
			Type type, String color, Double size);
}
