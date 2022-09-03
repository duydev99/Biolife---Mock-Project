package com.jsfw.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsfw.models.Tbl_Manufacturer;
import com.jsfw.models.Tbl_Product;
import com.jsfw.repositories.ManufacturerRepository;
import com.jsfw.services.ManufacturerService;

@Service
public class ManufacturerServiceImpl implements ManufacturerService{
	@Autowired
	ManufacturerRepository repository;
	
	public List<Tbl_Manufacturer> findAll(){
		return repository.findAll();
	}
	
	public Optional<Tbl_Manufacturer> findById(Integer id) {
		return repository.findById(id);
	}
	
	public Tbl_Manufacturer findByName(String name) {
		return repository.findByName(name);
	}
	
	public void create(Tbl_Manufacturer category) {
		repository.save(category);
	}
	
	public void edit(Tbl_Manufacturer category) {
		repository.save(category);
	}
	
	public void remove(Integer id) {
		List<Tbl_Product> products =repository.findById(id).get().getTblProducts();
		if(products.isEmpty() || products == null) {
			repository.deleteById(id);
		}
	}
}
