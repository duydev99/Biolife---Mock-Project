package com.jsfw.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jsfw.models.Tbl_Category;
import com.jsfw.models.Tbl_Manufacturer;
import com.jsfw.models.Tbl_Order_Detail;
import com.jsfw.models.Tbl_Product;
import com.jsfw.repositories.CommentRepository;
import com.jsfw.repositories.ImageRepository;
import com.jsfw.repositories.ProductRepository;
import com.jsfw.repositories.VoteRepository;
import com.jsfw.services.ProductService;
@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductRepository repository;
	@Autowired
	ImageRepository imageRepository;
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	VoteRepository voteRepository;
	
	public List<Tbl_Product> findAll(){
		return repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}
	public List<Tbl_Product> findTop10New() {
		return repository.findTop10ByOrderByIdDesc();
	}
	
	public List<Tbl_Product> findAllOrderByName(){
		return repository.findAll(Sort.by(Sort.Direction.ASC, "name"));
	}
	
	public List<Tbl_Product> findByCategoryAndManufacturer(Tbl_Category category, Tbl_Manufacturer manufacturer){
		return repository.findTop07ByTblCategoryOrTblManufacturerOrderByIdDesc(category, manufacturer);
	}
	
	public Optional<Tbl_Product> findById(Integer id) {
		return repository.findById(id);
	}
	
	public Tbl_Product findByName(String name) {
		return repository.findByName(name);
	}
	
	public List<Tbl_Product> findByProductName(String name){
		return repository.findByNameLike("%"+name+"%");
	}
	
	public List<Tbl_Product> findByCategory(Tbl_Category category){
		return repository.findByTblCategory(category);
	}
	
	public List<Tbl_Product> findByCategoryName(String name){
		return repository.findByTblCategoryNameLike("%"+name+"%");
	}
	
	public List<Tbl_Product> findByManufacturer(Tbl_Manufacturer manufacturer){
		return repository.findByTblManufacturer(manufacturer);
	}
	
	public List<Tbl_Product> findByManufacturerName(String name){
		return repository.findByTblManufacturerNameLike("%"+name+"%");
	}
	
	public void create(Tbl_Product product) {
		repository.save(product);
	}
	
	public void edit(Tbl_Product product) {
		repository.save(product);
	}
	
	public void remove(Integer id) {
		List<Tbl_Order_Detail> order_Details =  repository.findById(id).get().getTblOrderDetails();
		if(order_Details.isEmpty() || order_Details == null) {
			imageRepository.deleteByTblProduct(repository.findById(id).get());
			commentRepository.deleteByTblProduct(repository.findById(id).get());
			voteRepository.deleteByTblProduct(repository.findById(id).get());
			repository.deleteById(id);
		}
		
	}
}
