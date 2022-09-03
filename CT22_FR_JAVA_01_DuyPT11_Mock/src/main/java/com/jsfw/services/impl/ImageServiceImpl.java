package com.jsfw.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsfw.models.Tbl_Image_Product;
import com.jsfw.models.Tbl_Product;
import com.jsfw.repositories.ImageRepository;
import com.jsfw.services.ImageService;

@Service
public class ImageServiceImpl implements ImageService{
	@Autowired
	ImageRepository repository;
	
	public List<Tbl_Image_Product> findByProduct(Optional<Tbl_Product> product){
		return repository.findByTblProduct(product);
	}
	
	public Optional<Tbl_Image_Product> findById(Integer id) {
		return repository.findById(id);
	}
	
	public void create(Tbl_Image_Product image_Product) {
		repository.save(image_Product);
	}
	
	public void edit(Tbl_Image_Product image_Product) {
		repository.save(image_Product);
	}
	
	public void remove(Integer id) {
		repository.deleteById(id);
	}
}
