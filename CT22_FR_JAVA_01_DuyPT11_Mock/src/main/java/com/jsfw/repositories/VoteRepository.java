package com.jsfw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsfw.models.Tbl_Product;
import com.jsfw.models.Tbl_User;
import com.jsfw.models.Tbl_Vote;

@Repository
public interface VoteRepository extends JpaRepository<Tbl_Vote, Integer>{
	List<Tbl_Vote> findTop01ByTblProductAndTblUserOrderByIdDesc(Tbl_Product product, Tbl_User user);
	List<Tbl_Vote> findByTblProduct(Tbl_Product product);
	List<Tbl_Vote> findByTblUser(Tbl_User user);
	List<Tbl_Vote> findByTblProductAndStar(Tbl_Product product, Integer star);
	void deleteByTblProduct(Tbl_Product product);
}
