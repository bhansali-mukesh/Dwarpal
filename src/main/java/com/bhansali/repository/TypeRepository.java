package com.bhansali.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhansali.entity.Type;


public interface TypeRepository extends JpaRepository<Type, Integer> {

	Type findByName( String name);
}