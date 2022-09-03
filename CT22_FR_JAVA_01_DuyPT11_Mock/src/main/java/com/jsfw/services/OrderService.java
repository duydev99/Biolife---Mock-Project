package com.jsfw.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jsfw.models.Tbl_Category;
import com.jsfw.models.Tbl_Manufacturer;
import com.jsfw.models.Tbl_Order;
import com.jsfw.models.Tbl_Product;
import com.jsfw.models.Tbl_User;


public interface OrderService {
	
	public List<Tbl_Order> findAll();
	public void create(Tbl_Order order);
	public Tbl_Order findById(Integer id);
	public List<Tbl_Order> findByUser(Tbl_User user);
	public void edit(Tbl_Order order);
	public int findTotalByYear(Integer year);
	public int findTotalByMonth(Integer year, Integer month);
	public int findTotalByDay(Integer year, Integer month,  Integer day);
	
	public int findTotalByYearOrderByProduct(Integer year, Tbl_Product product);
	public int findTotalByMonthOrderByProduct(Integer year, Integer month,Tbl_Product product);
	public int findTotalByDayOrderByProduct(Integer year, Integer month,  Integer day,Tbl_Product product);
	
	public int findTotalByYearOrderByCategory(Integer year, Tbl_Category category);
	public int findTotalByMonthOrderByCategory(Integer year, Integer month, Tbl_Category category);
	public int findTotalByDayOrderByCategory(Integer year, Integer month,  Integer day, Tbl_Category category);
	
	public int findTotalByYearOrderByManufacturer(Integer year, Tbl_Manufacturer manufacturer);
	public int findTotalByMonthOrderByManufacturer(Integer year, Integer month, Tbl_Manufacturer manufacturer);
	public int findTotalByDayOrderByManufacturer(Integer year, Integer month,  Integer day, Tbl_Manufacturer manufacturer);

}
