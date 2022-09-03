package com.jsfw.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jsfw.models.Tbl_Category;
import com.jsfw.models.Tbl_Manufacturer;
import com.jsfw.models.Tbl_Order;
import com.jsfw.models.Tbl_Order_Detail;
import com.jsfw.models.Tbl_Product;
import com.jsfw.models.Tbl_User;
import com.jsfw.repositories.OrderRepository;
import com.jsfw.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository repository;

	@Override
	public List<Tbl_Order> findAll() {
		return repository.findAll(Sort.by(Sort.Direction.DESC, "createTime"));
	}

	@Override
	public void create(Tbl_Order order) {
		repository.save(order);
	}

	@Override
	public Tbl_Order findById(Integer id) {
		return repository.findById(id).get();
	}

	@Override
	public List<Tbl_Order> findByUser(Tbl_User user) {
		return repository.findByTblUserOrderByIdDesc(user);
	}

	@Override
	public void edit(Tbl_Order order) {
		repository.save(order);
	}

	@Override
	public int findTotalByYear(Integer year) {
		int total = 0;
		for (Tbl_Order o : repository.findByYear(year)) {
			total += o.getTotal();
		}
		return total;
	}

	@Override
	public int findTotalByMonth(Integer year, Integer month) {
		int total = 0;
		for (Tbl_Order o : repository.findByQuarter(year, month)) {
			total += o.getTotal();
		}
		return total;
	}

	@Override
	public int findTotalByDay(Integer year, Integer month, Integer day) {
		int total = 0;
		for (Tbl_Order o : repository.findByMonth(year, month, day)) {
			total += o.getTotal();
		}
		return total;
	}

	@Override
	public int findTotalByYearOrderByProduct(Integer year, Tbl_Product product) {
		int total = 0;
		for (Tbl_Order o : repository.findByYear(year)) {
			List<Tbl_Order_Detail> order_Details = o.getTblOrderDetails().stream()
					.filter(e -> e.getTblProduct().equals(product)).toList();
			for(Tbl_Order_Detail d:order_Details) {
				total += d.getAmount() * d.getPrice();
			}
		}
		return total;
	}

	@Override
	public int findTotalByMonthOrderByProduct(Integer year, Integer month, Tbl_Product product) {
		int total = 0;
		for (Tbl_Order o : repository.findByQuarter(year, month)) {
			List<Tbl_Order_Detail> order_Details = o.getTblOrderDetails().stream()
					.filter(e -> e.getTblProduct().equals(product)).toList();
			for(Tbl_Order_Detail d:order_Details) {
				total += d.getAmount() * d.getPrice();
			}
		}
		return total;
	}

	@Override
	public int findTotalByDayOrderByProduct(Integer year, Integer month, Integer day, Tbl_Product product) {
		int total = 0;
		for (Tbl_Order o : repository.findByMonth(year, month, day)) {
			List<Tbl_Order_Detail> order_Details = o.getTblOrderDetails().stream()
					.filter(e -> e.getTblProduct().equals(product)).toList();
			for(Tbl_Order_Detail d:order_Details) {
				total += d.getAmount() * d.getPrice();
			}
		}
		return total;
	}

	@Override
	public int findTotalByYearOrderByCategory(Integer year, Tbl_Category category) {
		int total = 0;
		for (Tbl_Order o : repository.findByYear(year)) {
			List<Tbl_Order_Detail> order_Details = o.getTblOrderDetails().stream()
					.filter(e -> e.getTblProduct().getTblCategory().equals(category)).toList();
			for(Tbl_Order_Detail d:order_Details) {
				total += d.getAmount() * d.getPrice();
			}
		}
		return total;
	}

	@Override
	public int findTotalByMonthOrderByCategory(Integer year, Integer month, Tbl_Category category) {
		int total = 0;
		for (Tbl_Order o : repository.findByQuarter(year, month)) {
			List<Tbl_Order_Detail> order_Details = o.getTblOrderDetails().stream()
					.filter(e -> e.getTblProduct().getTblCategory().equals(category)).toList();
			for(Tbl_Order_Detail d:order_Details) {
				total += d.getAmount() * d.getPrice();
			}
		}
		return total;
	}

	@Override
	public int findTotalByDayOrderByCategory(Integer year, Integer month, Integer day, Tbl_Category category) {
		int total = 0;
		for (Tbl_Order o : repository.findByMonth(year, month, day)) {
			List<Tbl_Order_Detail> order_Details = o.getTblOrderDetails().stream()
					.filter(e -> e.getTblProduct().getTblCategory().equals(category)).toList();
			for(Tbl_Order_Detail d:order_Details) {
				total += d.getAmount() * d.getPrice();
			}
		}
		return total;
	}

	@Override
	public int findTotalByYearOrderByManufacturer(Integer year, Tbl_Manufacturer manufacturer) {
		int total = 0;
		for (Tbl_Order o : repository.findByYear(year)) {
			List<Tbl_Order_Detail> order_Details = o.getTblOrderDetails().stream()
					.filter(e -> e.getTblProduct().getTblManufacturer().equals(manufacturer)).toList();
			for(Tbl_Order_Detail d:order_Details) {
				total += d.getAmount() * d.getPrice();
			}
		}
		return total;
	}

	@Override
	public int findTotalByMonthOrderByManufacturer(Integer year, Integer month, Tbl_Manufacturer manufacturer) {
		int total = 0;
		for (Tbl_Order o : repository.findByQuarter(year, month)) {
			List<Tbl_Order_Detail> order_Details = o.getTblOrderDetails().stream()
					.filter(e -> e.getTblProduct().getTblManufacturer().equals(manufacturer)).toList();
			for(Tbl_Order_Detail d:order_Details) {
				total += d.getAmount() * d.getPrice();
			}
		}
		return total;
	}

	@Override
	public int findTotalByDayOrderByManufacturer(Integer year, Integer month, Integer day,
			Tbl_Manufacturer manufacturer) {
		int total = 0;
		for (Tbl_Order o : repository.findByMonth(year, month, day)) {
			List<Tbl_Order_Detail> order_Details = o.getTblOrderDetails().stream()
					.filter(e -> e.getTblProduct().getTblManufacturer().equals(manufacturer)).toList();
			for(Tbl_Order_Detail d:order_Details) {
				total += d.getAmount() * d.getPrice();
			}
		}
		return total;
	}

}
