package com.bhansali.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhansali.entity.Consignment;
import com.bhansali.entity.Inventory;
import com.bhansali.entity.Lot;

public interface LotRepository extends JpaRepository<Lot, Integer> {

	List<Lot> findLotByInventoryItem(int inventoryItem);

	List<Lot> findLotByInventory(Inventory inventory);

	List<Lot> findLotByConsignment(Consignment consignment);

	List<Lot> findLotByConsignmentIn(List<Consignment> consignments);
}
