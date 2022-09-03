package com.jsfw.services;

import java.util.List;
import java.util.Optional;

import com.jsfw.models.Tbl_Payment;

public interface PaymentService {

	public List<Tbl_Payment> findAll();
	
	public Optional<Tbl_Payment> findById(Integer id) ;
	
	public Tbl_Payment findByName(String name) ;
	
	public void create(Tbl_Payment category) ;
	
	public void edit(Tbl_Payment category);
	
	public void remove(Integer id) ;
}
