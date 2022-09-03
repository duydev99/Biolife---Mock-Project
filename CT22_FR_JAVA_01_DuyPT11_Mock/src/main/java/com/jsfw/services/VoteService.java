package com.jsfw.services;

import java.util.List;

import com.jsfw.models.Tbl_Product;
import com.jsfw.models.Tbl_User;
import com.jsfw.models.Tbl_Vote;

public interface VoteService {

	public List<Tbl_Vote> findAll();
	
	public Tbl_Vote findById(Integer id);
	
	public void create(Tbl_Vote vote);
	
	public void edit(Tbl_Vote vote);
	
	public void remove(Tbl_Vote vote);
	
	public List<Tbl_Vote> findByProductAndUser(Tbl_Product product,Tbl_User user);
	
	public double findTotalRating(Tbl_Product product) ;
	
	public List<Tbl_Vote> findByUser(Tbl_User user);
	
	public List<Tbl_Vote> findByProduct(Tbl_Product product);
	
	public List<Tbl_Vote> findBy1Star(Tbl_Product product);
	
	public List<Tbl_Vote> findBy2Star(Tbl_Product product);
	
	public List<Tbl_Vote> findBy3Star(Tbl_Product product);
	
	public List<Tbl_Vote> findBy4Star(Tbl_Product product);
	
	public List<Tbl_Vote> findBy5Star(Tbl_Product product);
}
