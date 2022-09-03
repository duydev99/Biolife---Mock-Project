package com.jsfw.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsfw.models.Tbl_Comment;
import com.jsfw.models.Tbl_Product;
import com.jsfw.repositories.CommentRepository;
import com.jsfw.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	CommentRepository repository;
	
	public void create(Tbl_Comment comment) {
		repository.save(comment);
	}
	
	public List<Tbl_Comment> findByProduct(Tbl_Product product){
		return repository.findByTblProductOrderByCreateTimeDesc(product);
	}

	@Override
	public List<Tbl_Comment> findAll() {
		return repository.findAll();
	}

	@Override
	public void edit(Tbl_Comment comment) {
		repository.save(comment);
	}

	@Override
	public void remove(Tbl_Comment comment) {
		repository.delete(comment);
	}

	@Override
	public Tbl_Comment findById(Integer id) {
		return repository.findById(id).get();
	}
}
