package com.jsfw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jsfw.models.Tbl_Order;
import com.jsfw.models.Tbl_Order_Detail;

@Repository
public interface OrderDetailRepository extends JpaRepository<Tbl_Order_Detail, Integer>{
	List<Tbl_Order_Detail> findByTblOrder(Tbl_Order order);

	//trung
	@Query("SELECT od.tblProduct.tblCategory.name,"
			+ "Sum(od.amount),"
			+ "Sum(od.price * od.amount),"
			+ "MIN(od.price),"
			+ "Max(od.price),"
			+ "AVG(od.price)"
			+ "FROM Tbl_Order_Detail od "
			+ "GROUP BY od.tblProduct.tblCategory.name "
			+ "ORDER BY Sum(od.price * od.amount) DESC")
	List<Object[]> turnoverByCategory();

	@Query("SELECT od.tblProduct.name,"
			+ "Sum(od.amount),"
			+ "Sum(od.price * od.amount),"
			+ "MIN(od.price),"
			+ "Max(od.price),"
			+ "AVG(od.price)"
			+ "FROM Tbl_Order_Detail od "
			+ "GROUP BY od.tblProduct.name "
			+ "ORDER BY Sum(od.price * od.amount) DESC")
	List<Object[]> turnoverByProduct();
	
	@Query("SELECT od.tblOrder.tblUser.name,"
			+ "Sum(od.amount),"
			+ "Sum(od.price * od.amount),"
			+ "MIN(od.price),"
			+ "Max(od.price),"
			+ "AVG(od.price)"
			+ "FROM Tbl_Order_Detail od "
			+ "GROUP BY od.tblOrder.tblUser.name "
			+ "ORDER BY Sum(od.price * od.amount) DESC")
	List<Object[]> turnoverByUser();
//
	
//
//	@Query("SELECT month(od.order.createDate),"
//			+ "Sum(od.price*od.quantity),"
//			+ "Sum(od.quantity),"
//			+ "MIN(od.price),"
//			+ "Max(od.price),"
//			+ "AVG(od.price)"
//			+ "FROM OrderDetail od "
//			+ "GROUP BY month(od.order.createDate) "
//			+ "ORDER BY month(od.order.createDate)")
//	List<Object[]> turnoverByMonth();
//
//	@Query("SELECT ceiling(month(od.order.createDate)/3),"
//			+ "Sum(od.price*od.quantity),"
//			+ "Sum(od.quantity),"
//			+ "MIN(od.price),"
//			+ "Max(od.price),"
//			+ "AVG(od.price)"
//			+ "FROM OrderDetail od "
//			+ "GROUP BY ceiling(month(od.order.createDate)/3)"
//			+ "ORDER BY ceiling(month(od.order.createDate)/3)")
//	List<Object[]> turnoverByQuarterly();
//
//	@Query("SELECT year(od.order.createDate),"
//			+ "Sum(od.price*od.quantity),"
//			+ "Sum(od.quantity),"
//			+ "MIN(od.price),"
//			+ "Max(od.price),"
//			+ "AVG(od.price)"
//			+ "FROM OrderDetail od "
//			+ "GROUP BY year(od.order.createDate)"
//			+ "ORDER BY year(od.order.createDate)")
//	List<Object[]> turnoverByYear();
}
