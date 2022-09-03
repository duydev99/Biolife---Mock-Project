package com.jsfw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsfw.models.Tbl_Comment;
import com.jsfw.models.Tbl_Product;

@Repository
public interface CommentRepository extends JpaRepository<Tbl_Comment, Integer>{
	List<Tbl_Comment> findByTblProductOrderByCreateTimeDesc(Tbl_Product product);
	void deleteByTblProduct(Tbl_Product product);
}
