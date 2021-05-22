package com.bhansali.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhansali.entity.Inventory;
import com.bhansali.entity.WIPInventory;


public interface WIPRepository extends JpaRepository<WIPInventory, Integer> {

	WIPInventory findByInventory(Inventory inventory);
	
	WIPInventory findByInventoryItem(Integer item);
}
