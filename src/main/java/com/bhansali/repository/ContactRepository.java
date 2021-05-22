package com.bhansali.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhansali.entity.Contact;


public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
