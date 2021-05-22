package com.bhansali.repository;

import com.bhansali.entity.Person;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {
	
	List<Person> findPersonByAgent(boolean agent);
}
