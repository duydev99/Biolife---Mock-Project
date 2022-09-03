package com.jsfw.services;

import java.util.List;
import java.util.Optional;

import com.jsfw.models.Tbl_Manufacturer;

public interface ManufacturerService {
	
	public List<Tbl_Manufacturer> findAll();
	
	public Optional<Tbl_Manufacturer> findById(Integer id) ;
	
	public Tbl_Manufacturer findByName(String name) ;
	
	public void create(Tbl_Manufacturer category) ;
	
	public void edit(Tbl_Manufacturer category) ;
	
	public void remove(Integer id) ;
}
