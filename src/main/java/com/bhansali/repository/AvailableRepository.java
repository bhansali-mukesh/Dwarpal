package com.bhansali.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhansali.entity.Available;
import com.bhansali.entity.Inventory;


public interface AvailableRepository extends JpaRepository<Available, Integer> {

	Available findByInventory(Inventory inventory);
}
