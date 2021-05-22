package com.bhansali.repository;

import com.bhansali.entity.Address;
import com.bhansali.entity.Soil;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<Address, Integer> {

    Address findTopBySoilAndZipAndDescription(Soil soil, Integer zip, String description);
}
