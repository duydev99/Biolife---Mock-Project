package com.jsfw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsfw.models.Tbl_Category;
import com.jsfw.models.Tbl_Manufacturer;
import com.jsfw.models.Tbl_Product;

@Repository
public interface ProductRepository extends JpaRepository<Tbl_Product, Integer>{
	Tbl_Product findByName(String name);
	List<Tbl_Product> findByNameLike(String name);
	List<Tbl_Product> findByTblCategory(Tbl_Category category);
	List<Tbl_Product> findByTblCategoryNameLike(String name);
	List<Tbl_Product> findByTblManufacturer(Tbl_Manufacturer manufacturer);
	List<Tbl_Product> findByTblManufacturerNameLike(String name);
	List<Tbl_Product> findTop10ByOrderByIdDesc();
	List<Tbl_Product> findTop07ByTblCategoryOrTblManufacturerOrderByIdDesc(Tbl_Category category, Tbl_Manufacturer manufacturer);
}
