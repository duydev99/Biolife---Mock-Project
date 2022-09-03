package com.jsfw.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jsfw.models.Tbl_Comment;
import com.jsfw.models.Tbl_Product;

public interface CommentService {	
	public void create(Tbl_Comment comment) ;
	public void edit(Tbl_Comment comment) ;
	public void remove(Tbl_Comment comment) ;
	public List<Tbl_Comment> findByProduct(Tbl_Product product);
	public List<Tbl_Comment> findAll();
	public Tbl_Comment findById(Integer id);
}
