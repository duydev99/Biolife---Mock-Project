package com.jsfw.services;

import java.util.List;
import java.util.Optional;

import com.jsfw.models.Tbl_Image_Product;
import com.jsfw.models.Tbl_Product;

public interface ImageService {
	public List<Tbl_Image_Product> findByProduct(Optional<Tbl_Product> product);
	
	public Optional<Tbl_Image_Product> findById(Integer id) ;
	
	public void create(Tbl_Image_Product image_Product) ;
	
	public void edit(Tbl_Image_Product image_Product) ;
	
	public void remove(Integer id) ;
}
