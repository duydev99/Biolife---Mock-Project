package com.jsfw.services;

import java.util.List;
import java.util.Optional;

import com.jsfw.models.Tbl_Category;

public interface CategoryService{

	void remove(Integer id);

	void edit(Tbl_Category category);

	void create(Tbl_Category category);

	Tbl_Category findByName(String name);

	Optional<Tbl_Category> findById(Integer id);

	List<Tbl_Category> findAll();

}
