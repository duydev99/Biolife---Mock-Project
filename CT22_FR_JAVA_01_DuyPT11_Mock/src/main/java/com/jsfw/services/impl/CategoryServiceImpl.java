package com.jsfw.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsfw.models.Tbl_Category;
import com.jsfw.models.Tbl_Product;
import com.jsfw.repositories.CategoryRepository;
import com.jsfw.services.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryRepository repository;
	
	@Override
	public List<Tbl_Category> findAll(){
		return repository.findAll();
	}
	
	@Override
	public Optional<Tbl_Category> findById(Integer id) {
		return repository.findById(id);
	}
	
	@Override
	public Tbl_Category findByName(String name) {
		return repository.findByName(name);
	}
	
	@Override
	public void create(Tbl_Category category) {
		repository.save(category);
	}
	
	@Override
	public void edit(Tbl_Category category) {
		repository.save(category);
	}
	
	@Override
	public void remove(Integer id) {
		List<Tbl_Product> products = repository.findById(id).get().getTblProducts();
		if(products.isEmpty() || products == null) {
			repository.deleteById(id);	
		}
		
	}

}
