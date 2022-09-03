package com.jsfw.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsfw.models.Tbl_Image_Product;
import com.jsfw.models.Tbl_Product;

@Repository
public interface ImageRepository extends JpaRepository<Tbl_Image_Product, Integer>{
	List<Tbl_Image_Product> findByTblProduct(Optional<Tbl_Product> product);
	void deleteByTblProduct(Tbl_Product product);
}
