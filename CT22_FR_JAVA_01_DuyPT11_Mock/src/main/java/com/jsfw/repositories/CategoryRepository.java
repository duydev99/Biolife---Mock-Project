package com.jsfw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsfw.models.Tbl_Category;
@Repository
public interface CategoryRepository extends JpaRepository<Tbl_Category, Integer>{
	Tbl_Category findByName(String name);
}
