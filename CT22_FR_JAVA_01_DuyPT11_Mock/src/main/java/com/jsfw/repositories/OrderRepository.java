package com.jsfw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jsfw.models.Tbl_Order;
import com.jsfw.models.Tbl_User;
@Repository
public interface OrderRepository extends JpaRepository<Tbl_Order, Integer>{
	public List<Tbl_Order> findByTblUserOrderByIdDesc(Tbl_User user);
	
	@Query(value = "select * from Tbl_Order where Year(CreateTime) = :year and (Status = 1 or Status = 2)",nativeQuery = true)
	public List<Tbl_Order> findByYear(Integer year);
	
	@Query(value = "select * from Tbl_Order where Year(CreateTime) = :year and Month(CreateTime) = :month and (Status = 1 or Status = 2)",nativeQuery = true)
	public List<Tbl_Order> findByQuarter(Integer year, Integer month);
	
	@Query(value = "select * from Tbl_Order where Year(CreateTime) = :year and Month(CreateTime) = :month and Day(CreateTime) = :day and (Status = 1 or Status = 2)",nativeQuery = true)
	public List<Tbl_Order> findByMonth(Integer year, Integer month, Integer day);
}
