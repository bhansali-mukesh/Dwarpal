package com.bhansali.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhansali.entity.Consignment;
import com.bhansali.entity.Organization;

public interface ConsignmentRepository extends JpaRepository<Consignment, Integer> {

	List<Consignment> findBySenderAndDeleted(Organization organization, Boolean deleted);

	List<Consignment> findByReceiverAndDeleted(Organization organization, Boolean deleted);

	List<Consignment> findByType(Consignment.Type type);

	// @Query("Select ")
	List<Consignment> findByDateBetween(Date startDate, Date endDate);
}
