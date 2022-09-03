package com.jsfw.services.impl;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsfw.models.Tbl_Product;
import com.jsfw.models.Tbl_User;
import com.jsfw.models.Tbl_Vote;
import com.jsfw.repositories.VoteRepository;
import com.jsfw.services.VoteService;

@Service
public class VoteServiceImpl implements VoteService{

	@Autowired
	VoteRepository repository;
	
	public void create(Tbl_Vote vote) {
		repository.save(vote);
	}
	
	public void edit(Tbl_Vote vote) {
		repository.save(vote);
	}
	
	public List<Tbl_Vote> findByProductAndUser(Tbl_Product product,Tbl_User user){
		return repository.findTop01ByTblProductAndTblUserOrderByIdDesc(product, user);
	}
	
	public double findTotalRating(Tbl_Product product) {
		List<Tbl_Vote> votes = repository.findByTblProduct(product);
		int total = 0;
		for(Tbl_Vote v: votes) {
			total+=v.getStar();
		}
		DecimalFormat df = new DecimalFormat("#.#");
		return Double.valueOf(df.format((double) Math.round(total) / votes.size()));
	}
	
	public List<Tbl_Vote> findByUser(Tbl_User user){
		return repository.findByTblUser(user);
	}
	
	public List<Tbl_Vote> findByProduct(Tbl_Product product){
		return repository.findByTblProduct(product);
	}
	
	public List<Tbl_Vote> findBy1Star(Tbl_Product product){
		return repository.findByTblProductAndStar(product, 1);
	}
	public List<Tbl_Vote> findBy2Star(Tbl_Product product){
		return repository.findByTblProductAndStar(product, 2);
	}
	public List<Tbl_Vote> findBy3Star(Tbl_Product product){
		return repository.findByTblProductAndStar(product, 3);
	}
	public List<Tbl_Vote> findBy4Star(Tbl_Product product){
		return repository.findByTblProductAndStar(product, 4);
	}
	public List<Tbl_Vote> findBy5Star(Tbl_Product product){
		return repository.findByTblProductAndStar(product, 5);
	}

	@Override
	public List<Tbl_Vote> findAll() {
		return repository.findAll();
	}

	@Override
	public void remove(Tbl_Vote vote) {
		repository.delete(vote);
	}

	@Override
	public Tbl_Vote findById(Integer id) {
		return repository.findById(id).get();
	}
}
