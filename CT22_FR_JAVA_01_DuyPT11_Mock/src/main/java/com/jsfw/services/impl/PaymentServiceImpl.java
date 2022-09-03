package com.jsfw.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsfw.models.Tbl_Order;
import com.jsfw.models.Tbl_Payment;
import com.jsfw.repositories.PaymentRepository;
import com.jsfw.services.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService{
	@Autowired
	PaymentRepository repository;
	
	public List<Tbl_Payment> findAll(){
		return repository.findAll();
	}
	
	public Optional<Tbl_Payment> findById(Integer id) {
		return repository.findById(id);
	}
	
	public Tbl_Payment findByName(String name) {
		return repository.findByMethod(name);
	}
	
	public void create(Tbl_Payment category) {
		repository.save(category);
	}
	
	public void edit(Tbl_Payment category) {
		repository.save(category);
	}
	
	public void remove(Integer id) {
		List<Tbl_Order> orders = repository.findById(id).get().getTblOrders();
		if(orders.isEmpty() || orders == null) {
			repository.deleteById(id);
		}
	}
}
