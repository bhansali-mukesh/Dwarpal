package com.bhansali.repository;

import com.bhansali.entity.Category;
import com.bhansali.entity.Soil;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Integer> {

	Category findByName( String name );
}
