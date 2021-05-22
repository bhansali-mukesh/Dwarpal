package com.bhansali.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhansali.entity.Inventory;
import com.bhansali.entity.InventoryDetail;
import com.bhansali.entity.Type;


public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

	List<Inventory> findByItem(Integer item);
	
	List<Inventory> findByDetail(InventoryDetail detail);
	
	Inventory findByItemAndDetail(Integer item, InventoryDetail inventoryDetail);
}
