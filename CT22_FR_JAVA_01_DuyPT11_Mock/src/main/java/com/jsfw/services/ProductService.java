package com.jsfw.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jsfw.models.Tbl_Category;
import com.jsfw.models.Tbl_Manufacturer;
import com.jsfw.models.Tbl_Product;
@Service
public interface ProductService {
	
	public List<Tbl_Product> findAll();
	
	public List<Tbl_Product> findAllOrderByName();
	
	public List<Tbl_Product> findByCategoryAndManufacturer(Tbl_Category category, Tbl_Manufacturer manufacturer);
	
	public Optional<Tbl_Product> findById(Integer id) ;
	
	public List<Tbl_Product> findTop10New();
	
	public Tbl_Product findByName(String name);
	
	public List<Tbl_Product> findByProductName(String name);
	
	public List<Tbl_Product> findByCategory(Tbl_Category category);
	
	public List<Tbl_Product> findByCategoryName(String name);
	
	public List<Tbl_Product> findByManufacturer(Tbl_Manufacturer manufacturer);
	
	public List<Tbl_Product> findByManufacturerName(String name);
	
	public void create(Tbl_Product product);
	
	public void edit(Tbl_Product product) ;
	
	public void remove(Integer id);
}
