package com.jsfw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsfw.models.Tbl_Manufacturer;

@Repository
public interface ManufacturerRepository extends JpaRepository<Tbl_Manufacturer, Integer>{
	Tbl_Manufacturer findByName(String name);
}
