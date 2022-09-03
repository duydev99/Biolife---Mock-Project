package com.jsfw.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.jsfw.models.Statistic;
import com.jsfw.models.Tbl_Category;
import com.jsfw.models.Tbl_Manufacturer;
import com.jsfw.models.Tbl_Product;
import com.jsfw.services.OrderService;

public class StatisticUtils {

	public static List<Statistic> getStatisticsTotal(Model model, HttpServletRequest request, OrderService orderService,
			String key, int value,int value2) {
		List<Statistic> rsList = null;
		switch (key) {
		case "year":
			if (value > 0)
				rsList = getYear(model, request, orderService, value);
			else
				rsList = getYears(model, request, orderService);

			break;
		case "quarter":
			if (value > 0)
				rsList = getQuarter(model, request, orderService, value);
			else
				rsList = getQuarters(model, request, orderService, value);
			break;
		case "month":
			if (value > 0)
				if(value2 > 0)rsList = getMonth(model, request, orderService, value,value2);
				else rsList = getMonthCurrent(model, request, orderService, value);
			else
				rsList = getMonths(model, request, orderService);
			break;
		default:
			break;
		}
		return rsList;
	}
	
	public static List<Statistic> getStatisticsProduct(Model model, HttpServletRequest request, OrderService orderService,
			String key, int value, Tbl_Product product, int value2) {
		List<Statistic> rsList = null;
		switch (key) {
		case "year":
			if (value > 0)
				rsList = getYearByProduct(model, request, orderService, value,product);
			else
				rsList = getYearsByProduct(model, request, orderService,product);

			break;
		case "quarter":
			if (value > 0)
				rsList = getQuarterByProduct(model, request, orderService, value,product);
			else
				rsList = getQuartersByProduct(model, request, orderService, value,product);
			break;
		case "month":
			if (value > 0)
				if(value2>0) {
					rsList = getMonthByProduct(model, request, orderService, value,product,value2);
				}else {
					rsList = getMonthCurrentByProduct(model, request, orderService, value,product);
				}
			else
				rsList = getMonthsByProduct(model, request, orderService,product);
			break;
		default:
			break;
		}
		return rsList;
	}
	
	public static List<Statistic> getStatisticsCategory(Model model, HttpServletRequest request, OrderService orderService,
			String key, int value, Tbl_Category category, int value2) {
		List<Statistic> rsList = null;
		switch (key) {
		case "year":
			if (value > 0)
				rsList = getYearByCategory(model, request, orderService, value,category);
			else
				rsList = getYearsByCategory(model, request, orderService,category);

			break;
		case "quarter":
			if (value > 0)
				rsList = getQuarterByCategory(model, request, orderService, value,category);
			else
				rsList = getQuartersByCategory(model, request, orderService, value,category);
			break;
		case "month":
			if (value > 0)
				if(value2>0) {
					rsList = getMonthByCategory(model, request, orderService, value,category,value2);
				}else {
					rsList = getMonthCurrentByCategory(model, request, orderService, value,category);
				}
			else
				rsList = getMonthsByCategory(model, request, orderService,category);
			break;
		default:
			break;
		}
		return rsList;
	}
	
	public static List<Statistic> getStatisticsManufacturer(Model model, HttpServletRequest request, OrderService orderService,
			String key, int value, Tbl_Manufacturer manufacturer,int value2) {
		List<Statistic> rsList = null;
		switch (key) {
		case "year":
			if (value > 0)
				rsList = getYearByManufacturer(model, request, orderService, value,manufacturer);
			else
				rsList = getYearsByManufacturer(model, request, orderService,manufacturer);
			break;
		case "quarter":
			if (value > 0)
				rsList = getQuarterByManufacturer(model, request, orderService, value,manufacturer);
			else
				rsList = getQuartersByManufacturer(model, request, orderService, value,manufacturer);
			break;
		case "month":
			if (value > 0)
				if(value>0) {
					rsList = getMonthByManufacturer(model, request, orderService, value,manufacturer,value2);
				}else {
					rsList = getMonthCurrentByManufacturer(model, request, orderService, value,manufacturer);
				}
			else
				rsList = getMonthsByManufacturer(model, request, orderService,manufacturer);
			break;
		default:
			break;
		}
		return rsList;
	}

	private static List<Statistic> getYearByProduct(Model model, HttpServletRequest request, OrderService orderService,
			int value, Tbl_Product product) {

		List<Statistic> rsList = new ArrayList<Statistic>();
		for (int i = 1; i <= 12; i++) {
			Statistic statistic = new Statistic();
			statistic.setTime(i);
			statistic.setTotal(orderService.findTotalByMonthOrderByProduct(value, i,product));
			rsList.add(statistic);
		}

		for (int i = 0; i < 12; i++) {
			if (i > 0) {
				if (rsList.get(i - 1).getTotal() != 0) {
					int sub = rsList.get(i).getTotal()- rsList.get(i-1).getTotal();
					double div = (double)sub / rsList.get(i-1).getTotal();
					rsList.get(i).setChange((double) Math.ceil(div * 10000) / 100) ;
				} else {
					if (rsList.get(i).getTotal() > 0)
						rsList.get(i).setChange(100);
					else if (rsList.get(i).getTotal() < 0)
						rsList.get(i).setChange(-100);
					else
						rsList.get(i).setChange(0);
				}
			} else {
				if (rsList.get(i).getTotal() > 0)
					rsList.get(i).setChange(100);
				else if (rsList.get(i).getTotal() < 0)
					rsList.get(i).setChange(-100);
				else
					rsList.get(i).setChange(0);
			}
		}

		return rsList;
	}
	
	private static List<Statistic> getYearByCategory(Model model, HttpServletRequest request, OrderService orderService,
			int value, Tbl_Category category) {

		List<Statistic> rsList = new ArrayList<Statistic>();
		for (int i = 1; i <= 12; i++) {
			Statistic statistic = new Statistic();
			statistic.setTime(i);
			statistic.setTotal(orderService.findTotalByMonthOrderByCategory(value, i,category));
			rsList.add(statistic);
		}

		for (int i = 0; i < 12; i++) {
			if (i > 0) {
				if (rsList.get(i - 1).getTotal() != 0) {
					int sub = rsList.get(i).getTotal()- rsList.get(i-1).getTotal();
					double div = (double)sub / rsList.get(i-1).getTotal();
					rsList.get(i).setChange((double) Math.ceil(div * 10000) / 100) ;
				} else {
					if (rsList.get(i).getTotal() > 0)
						rsList.get(i).setChange(100);
					else if (rsList.get(i).getTotal() < 0)
						rsList.get(i).setChange(-100);
					else
						rsList.get(i).setChange(0);
				}
			} else {
				if (rsList.get(i).getTotal() > 0)
					rsList.get(i).setChange(100);
				else if (rsList.get(i).getTotal() < 0)
					rsList.get(i).setChange(-100);
				else
					rsList.get(i).setChange(0);
			}
		}

		return rsList;
	}
	
	private static List<Statistic> getYearByManufacturer(Model model, HttpServletRequest request, OrderService orderService,
			int value, Tbl_Manufacturer manufacturer) {

		List<Statistic> rsList = new ArrayList<Statistic>();
		for (int i = 1; i <= 12; i++) {
			Statistic statistic = new Statistic();
			statistic.setTime(i);
			statistic.setTotal(orderService.findTotalByMonthOrderByManufacturer(value, i,manufacturer));
			rsList.add(statistic);
		}

		for (int i = 0; i < 12; i++) {
			if (i > 0) {
				if (rsList.get(i - 1).getTotal() != 0) {
					int sub = rsList.get(i).getTotal()- rsList.get(i-1).getTotal();
					double div = (double)sub / rsList.get(i-1).getTotal();
					rsList.get(i).setChange((double) Math.ceil(div * 10000) / 100) ;
				} else {
					if (rsList.get(i).getTotal() > 0)
						rsList.get(i).setChange(100);
					else if (rsList.get(i).getTotal() < 0)
						rsList.get(i).setChange(-100);
					else
						rsList.get(i).setChange(0);
				}
			} else {
				if (rsList.get(i).getTotal() > 0)
					rsList.get(i).setChange(100);
				else if (rsList.get(i).getTotal() < 0)
					rsList.get(i).setChange(-100);
				else
					rsList.get(i).setChange(0);
			}
		}

		return rsList;
	}
	
	private static List<Statistic> getYear(Model model, HttpServletRequest request, OrderService orderService,
			int value) {

		List<Statistic> rsList = new ArrayList<Statistic>();
		for (int i = 1; i <= 12; i++) {
			Statistic statistic = new Statistic();
			statistic.setTime(i);
			statistic.setTotal(orderService.findTotalByMonth(value, i));
			rsList.add(statistic);
		}

		for (int i = 0; i < 12; i++) {
			if (i > 0) {
				if (rsList.get(i - 1).getTotal() != 0) {
					int sub = rsList.get(i).getTotal()- rsList.get(i-1).getTotal();
					double div = (double)sub / rsList.get(i-1).getTotal();
					rsList.get(i).setChange((double) Math.ceil(div * 10000) / 100) ;
				} else {
					if (rsList.get(i).getTotal() > 0)
						rsList.get(i).setChange(100);
					else if (rsList.get(i).getTotal() < 0)
						rsList.get(i).setChange(-100);
					else
						rsList.get(i).setChange(0);
				}
			} else {
				if (rsList.get(i).getTotal() > 0)
					rsList.get(i).setChange(100);
				else if (rsList.get(i).getTotal() < 0)
					rsList.get(i).setChange(-100);
				else
					rsList.get(i).setChange(0);
			}
		}

		return rsList;
	}

	private static List<Statistic> getYears(Model model, HttpServletRequest request, OrderService orderService) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
		LocalDate localDate = LocalDate.now();
		int currentYear = localDate.getYear();
		List<Statistic> rsList = new ArrayList<Statistic>();
		for (int i = currentYear - 4; i <= currentYear; i++) {
			Statistic statistic = new Statistic();
			statistic.setTime(i);
			statistic.setTotal(orderService.findTotalByYear(i));
			rsList.add(statistic);
		}
		for (int i = 0; i < 5; i++) {
			if (i > 0) {
				if (rsList.get(i - 1).getTotal() != 0) {
					int sub = rsList.get(i).getTotal()- rsList.get(i-1).getTotal();
					double div = (double)sub / rsList.get(i-1).getTotal();
					rsList.get(i).setChange((double) Math.ceil(div * 10000) / 100) ;
				} else {
					if (rsList.get(i).getTotal() > 0)
						rsList.get(i).setChange(100);
					else if (rsList.get(i).getTotal() < 0)
						rsList.get(i).setChange(-100);
					else
						rsList.get(i).setChange(0);
				}
			} else {
				if (rsList.get(i).getTotal() > 0)
					rsList.get(i).setChange(100);
				else if (rsList.get(i).getTotal() < 0)
					rsList.get(i).setChange(-100);
				else
					rsList.get(i).setChange(0);
			}
		}
		return rsList;
	}
	
	private static List<Statistic> getYearsByProduct(Model model, HttpServletRequest request, OrderService orderService,Tbl_Product product) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
		LocalDate localDate = LocalDate.now();
		int currentYear = localDate.getYear();
		List<Statistic> rsList = new ArrayList<Statistic>();
		for (int i = currentYear - 4; i <= currentYear; i++) {
			Statistic statistic = new Statistic();
			statistic.setTime(i);
			statistic.setTotal(orderService.findTotalByYearOrderByProduct(i,product));
			rsList.add(statistic);
			System.out.println(statistic.getTotal());
		}
		for (int i = 0; i < 5; i++) {
			if (i > 0) {
				if (rsList.get(i - 1).getTotal() != 0) {
					int sub = rsList.get(i).getTotal()- rsList.get(i-1).getTotal();
					double div = (double)sub / rsList.get(i-1).getTotal();
					rsList.get(i).setChange((double) Math.ceil(div * 10000) / 100) ;
				} else {
					if (rsList.get(i).getTotal() > 0)
						rsList.get(i).setChange(100);
					else if (rsList.get(i).getTotal() < 0)
						rsList.get(i).setChange(-100);
					else
						rsList.get(i).setChange(0);
				}
			} else {
				if (rsList.get(i).getTotal() > 0)
					rsList.get(i).setChange(100);
				else if (rsList.get(i).getTotal() < 0)
					rsList.get(i).setChange(-100);
				else
					rsList.get(i).setChange(0);
			}
		}
		return rsList;
	}
	
	private static List<Statistic> getYearsByCategory(Model model, HttpServletRequest request, OrderService orderService,Tbl_Category category) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
		LocalDate localDate = LocalDate.now();
		int currentYear = localDate.getYear();
		List<Statistic> rsList = new ArrayList<Statistic>();
		for (int i = currentYear - 4; i <= currentYear; i++) {
			Statistic statistic = new Statistic();
			statistic.setTime(i);
			statistic.setTotal(orderService.findTotalByYearOrderByCategory(i,category));
			rsList.add(statistic);
		}
		for (int i = 0; i < 5; i++) {
			if (i > 0) {
				if (rsList.get(i - 1).getTotal() != 0) {
					int sub = rsList.get(i).getTotal()- rsList.get(i-1).getTotal();
					double div = (double)sub / rsList.get(i-1).getTotal();
					rsList.get(i).setChange((double) Math.ceil(div * 10000) / 100) ;
				} else {
					if (rsList.get(i).getTotal() > 0)
						rsList.get(i).setChange(100);
					else if (rsList.get(i).getTotal() < 0)
						rsList.get(i).setChange(-100);
					else
						rsList.get(i).setChange(0);
				}
			} else {
				if (rsList.get(i).getTotal() > 0)
					rsList.get(i).setChange(100);
				else if (rsList.get(i).getTotal() < 0)
					rsList.get(i).setChange(-100);
				else
					rsList.get(i).setChange(0);
			}
		}
		return rsList;
	}
	
	private static List<Statistic> getYearsByManufacturer(Model model, HttpServletRequest request, OrderService orderService,Tbl_Manufacturer manufacturer) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
		LocalDate localDate = LocalDate.now();
		int currentYear = localDate.getYear();
		List<Statistic> rsList = new ArrayList<Statistic>();
		for (int i = currentYear - 4; i <= currentYear; i++) {
			Statistic statistic = new Statistic();
			statistic.setTime(i);
			statistic.setTotal(orderService.findTotalByYearOrderByManufacturer(i,manufacturer));
			rsList.add(statistic);
		}
		for (int i = 0; i < 5; i++) {
			if (i > 0) {
				if (rsList.get(i - 1).getTotal() != 0) {
					int sub = rsList.get(i).getTotal()- rsList.get(i-1).getTotal();
					double div = (double)sub / rsList.get(i-1).getTotal();
					rsList.get(i).setChange((double) Math.ceil(div * 10000) / 100) ;
				} else {
					if (rsList.get(i).getTotal() > 0)
						rsList.get(i).setChange(100);
					else if (rsList.get(i).getTotal() < 0)
						rsList.get(i).setChange(-100);
					else
						rsList.get(i).setChange(0);
				}
			} else {
				if (rsList.get(i).getTotal() > 0)
					rsList.get(i).setChange(100);
				else if (rsList.get(i).getTotal() < 0)
					rsList.get(i).setChange(-100);
				else
					rsList.get(i).setChange(0);
			}
		}
		return rsList;
	}

	private static List<Statistic> getMonthByProduct(Model model, HttpServletRequest request, OrderService orderService, int value, Tbl_Product product,int value2) {
		List<Statistic> rsList = new ArrayList<Statistic>();
		for(int i=1;i<=31;i++) {
			Statistic statistic = new Statistic();
			statistic.setTime(i);
			statistic.setTotal(orderService.findTotalByDayOrderByProduct(value2, value, i,product));
			rsList.add(statistic);
		}

		for(int i=0;i<31;i++) {
			if(i>0) {
				if (rsList.get(i-1).getTotal()!= 0) {
					int sub = rsList.get(i).getTotal()- rsList.get(i-1).getTotal();
					double div = (double)sub / rsList.get(i-1).getTotal();
					rsList.get(i).setChange((double) Math.ceil(div * 10000) / 100) ;
				} else {
					if (rsList.get(i).getTotal() > 0)
						rsList.get(i).setChange(100);
					else if (rsList.get(i).getTotal() < 0)
						rsList.get(i).setChange(-100);
					else
						rsList.get(i).setChange(0);
				}
			}else {
				if (rsList.get(i).getTotal() > 0)
					rsList.get(i).setChange(100);
				else if (rsList.get(i).getTotal() < 0)
					rsList.get(i).setChange(-100);
				else
					rsList.get(i).setChange(0);
			}
			
		}
		return rsList;
	}
	
	private static List<Statistic> getMonthCurrentByProduct(Model model, HttpServletRequest request, OrderService orderService, int value, Tbl_Product product) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
		LocalDate localDate = LocalDate.now();
		int currentYear = localDate.getYear();
		List<Statistic> rsList = new ArrayList<Statistic>();
		for(int i=1;i<=31;i++) {
			Statistic statistic = new Statistic();
			statistic.setTime(i);
			statistic.setTotal(orderService.findTotalByDayOrderByProduct(currentYear, value, i,product));
			rsList.add(statistic);
		}

		for(int i=0;i<31;i++) {
			if(i>0) {
				if (rsList.get(i-1).getTotal()!= 0) {
					int sub = rsList.get(i).getTotal()- rsList.get(i-1).getTotal();
					double div = (double)sub / rsList.get(i-1).getTotal();
					rsList.get(i).setChange((double) Math.ceil(div * 10000) / 100) ;
				} else {
					if (rsList.get(i).getTotal() > 0)
						rsList.get(i).setChange(100);
					else if (rsList.get(i).getTotal() < 0)
						rsList.get(i).setChange(-100);
					else
						rsList.get(i).setChange(0);
				}
			}else {
				if (rsList.get(i).getTotal() > 0)
					rsList.get(i).setChange(100);
				else if (rsList.get(i).getTotal() < 0)
					rsList.get(i).setChange(-100);
				else
					rsList.get(i).setChange(0);
			}
			
		}
		return rsList;
	}
	
	private static List<Statistic> getMonthByCategory(Model model, HttpServletRequest request, OrderService orderService, int value, Tbl_Category category,int value2) {
		List<Statistic> rsList = new ArrayList<Statistic>();
		for(int i=1;i<=31;i++) {
			Statistic statistic = new Statistic();
			statistic.setTime(i);
			statistic.setTotal(orderService.findTotalByDayOrderByCategory(value2, value, i,category));
			rsList.add(statistic);
		}

		for(int i=0;i<31;i++) {
			if(i>0) {
				if (rsList.get(i-1).getTotal()!= 0) {
					int sub = rsList.get(i).getTotal()- rsList.get(i-1).getTotal();
					double div = (double)sub / rsList.get(i-1).getTotal();
					rsList.get(i).setChange((double) Math.ceil(div * 10000) / 100) ;
				} else {
					if (rsList.get(i).getTotal() > 0)
						rsList.get(i).setChange(100);
					else if (rsList.get(i).getTotal() < 0)
						rsList.get(i).setChange(-100);
					else
						rsList.get(i).setChange(0);
				}
			}else {
				if (rsList.get(i).getTotal() > 0)
					rsList.get(i).setChange(100);
				else if (rsList.get(i).getTotal() < 0)
					rsList.get(i).setChange(-100);
				else
					rsList.get(i).setChange(0);
			}
			
		}
		return rsList;
	}
	
	private static List<Statistic> getMonthCurrentByCategory(Model model, HttpServletRequest request, OrderService orderService, int value, Tbl_Category category) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
		LocalDate localDate = LocalDate.now();
		int currentYear = localDate.getYear();
		List<Statistic> rsList = new ArrayList<Statistic>();
		for(int i=1;i<=31;i++) {
			Statistic statistic = new Statistic();
			statistic.setTime(i);
			statistic.setTotal(orderService.findTotalByDayOrderByCategory(currentYear, value, i,category));
			rsList.add(statistic);
		}

		for(int i=0;i<31;i++) {
			if(i>0) {
				if (rsList.get(i-1).getTotal()!= 0) {
					int sub = rsList.get(i).getTotal()- rsList.get(i-1).getTotal();
					double div = (double)sub / rsList.get(i-1).getTotal();
					rsList.get(i).setChange((double) Math.ceil(div * 10000) / 100) ;
				} else {
					if (rsList.get(i).getTotal() > 0)
						rsList.get(i).setChange(100);
					else if (rsList.get(i).getTotal() < 0)
						rsList.get(i).setChange(-100);
					else
						rsList.get(i).setChange(0);
				}
			}else {
				if (rsList.get(i).getTotal() > 0)
					rsList.get(i).setChange(100);
				else if (rsList.get(i).getTotal() < 0)
					rsList.get(i).setChange(-100);
				else
					rsList.get(i).setChange(0);
			}
			
		}
		return rsList;
	}
	
	private static List<Statistic> getMonthByManufacturer(Model model, HttpServletRequest request, OrderService orderService, int value, Tbl_Manufacturer manufacturer, int value2) {
		List<Statistic> rsList = new ArrayList<Statistic>();
		for(int i=1;i<=31;i++) {
			Statistic statistic = new Statistic();
			statistic.setTime(i);
			statistic.setTotal(orderService.findTotalByDayOrderByManufacturer(value2, value, i,manufacturer));
			rsList.add(statistic);
		}

		for(int i=0;i<31;i++) {
			if(i>0) {
				if (rsList.get(i-1).getTotal()!= 0) {
					int sub = rsList.get(i).getTotal()- rsList.get(i-1).getTotal();
					double div = (double)sub / rsList.get(i-1).getTotal();
					rsList.get(i).setChange((double) Math.ceil(div * 10000) / 100) ;
				} else {
					if (rsList.get(i).getTotal() > 0)
						rsList.get(i).setChange(100);
					else if (rsList.get(i).getTotal() < 0)
						rsList.get(i).setChange(-100);
					else
						rsList.get(i).setChange(0);
				}
			}else {
				if (rsList.get(i).getTotal() > 0)
					rsList.get(i).setChange(100);
				else if (rsList.get(i).getTotal() < 0)
					rsList.get(i).setChange(-100);
				else
					rsList.get(i).setChange(0);
			}
			
		}
		return rsList;
	}
	
	private static List<Statistic> getMonthCurrentByManufacturer(Model model, HttpServletRequest request, OrderService orderService, int value, Tbl_Manufacturer manufacturer) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
		LocalDate localDate = LocalDate.now();
		int currentYear = localDate.getYear();
		List<Statistic> rsList = new ArrayList<Statistic>();
		for(int i=1;i<=31;i++) {
			Statistic statistic = new Statistic();
			statistic.setTime(i);
			statistic.setTotal(orderService.findTotalByDayOrderByManufacturer(currentYear, value, i,manufacturer));
			rsList.add(statistic);
		}

		for(int i=0;i<31;i++) {
			if(i>0) {
				if (rsList.get(i-1).getTotal()!= 0) {
					int sub = rsList.get(i).getTotal()- rsList.get(i-1).getTotal();
					double div = (double)sub / rsList.get(i-1).getTotal();
					rsList.get(i).setChange((double) Math.ceil(div * 10000) / 100) ;
				} else {
					if (rsList.get(i).getTotal() > 0)
						rsList.get(i).setChange(100);
					else if (rsList.get(i).getTotal() < 0)
						rsList.get(i).setChange(-100);
					else
						rsList.get(i).setChange(0);
				}
			}else {
				if (rsList.get(i).getTotal() > 0)
					rsList.get(i).setChange(100);
				else if (rsList.get(i).getTotal() < 0)
					rsList.get(i).setChange(-100);
				else
					rsList.get(i).setChange(0);
			}
			
		}
		return rsList;
	}
	
	private static List<Statistic> getMonth(Model model, HttpServletRequest request, OrderService orderService, int value,int value2) {
		List<Statistic> rsList = new ArrayList<Statistic>();
		for(int i=1;i<=31;i++) {
			Statistic statistic = new Statistic();
			statistic.setTime(i);
			statistic.setTotal(orderService.findTotalByDay(value2, value, i));
			rsList.add(statistic);
		}

		for(int i=0;i<31;i++) {
			if(i>0) {
				if (rsList.get(i-1).getTotal()!= 0) {
					int sub = rsList.get(i).getTotal()- rsList.get(i-1).getTotal();
					double div = (double)sub / rsList.get(i-1).getTotal();
					rsList.get(i).setChange((double) Math.ceil(div * 10000) / 100) ;
				} else {
					if (rsList.get(i).getTotal() > 0)
						rsList.get(i).setChange(100);
					else if (rsList.get(i).getTotal() < 0)
						rsList.get(i).setChange(-100);
					else
						rsList.get(i).setChange(0);
				}
			}else {
				if (rsList.get(i).getTotal() > 0)
					rsList.get(i).setChange(100);
				else if (rsList.get(i).getTotal() < 0)
					rsList.get(i).setChange(-100);
				else
					rsList.get(i).setChange(0);
			}
			
		}
		return rsList;
	}
	
	private static List<Statistic> getMonthCurrent(Model model, HttpServletRequest request, OrderService orderService, int value) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
		LocalDate localDate = LocalDate.now();
		int currentYear = localDate.getYear();
		List<Statistic> rsList = new ArrayList<Statistic>();
		for(int i=1;i<=31;i++) {
			Statistic statistic = new Statistic();
			statistic.setTime(i);
			statistic.setTotal(orderService.findTotalByDay(currentYear, value, i));
			rsList.add(statistic);
		}

		for(int i=0;i<31;i++) {
			if(i>0) {
				if (rsList.get(i-1).getTotal()!= 0) {
					int sub = rsList.get(i).getTotal()- rsList.get(i-1).getTotal();
					double div = (double)sub / rsList.get(i-1).getTotal();
					rsList.get(i).setChange((double) Math.ceil(div * 10000) / 100) ;
				} else {
					if (rsList.get(i).getTotal() > 0)
						rsList.get(i).setChange(100);
					else if (rsList.get(i).getTotal() < 0)
						rsList.get(i).setChange(-100);
					else
						rsList.get(i).setChange(0);
				}
			}else {
				if (rsList.get(i).getTotal() > 0)
					rsList.get(i).setChange(100);
				else if (rsList.get(i).getTotal() < 0)
					rsList.get(i).setChange(-100);
				else
					rsList.get(i).setChange(0);
			}
			
		}
		return rsList;
	}

	private static List<Statistic> getMonths(Model model, HttpServletRequest request, OrderService orderService) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
		LocalDate localDate = LocalDate.now();
		int currentYear = localDate.getYear();
		List<Statistic> rsList = new ArrayList<Statistic>();
		for (int i = 1; i <= 12; i++) {
			Statistic statistic = new Statistic();
			statistic.setTime(i);
			statistic.setTotal(orderService.findTotalByMonth(currentYear, i));
			rsList.add(statistic);
		}

		for (int i = 0; i < 12; i++) {
			if (i > 0) {
				if (rsList.get(i - 1).getTotal() != 0) {
					int sub = rsList.get(i).getTotal()- rsList.get(i-1).getTotal();
					double div = (double)sub / rsList.get(i-1).getTotal();
					rsList.get(i).setChange((double) Math.ceil(div * 10000) / 100) ;
				} else {
					if (rsList.get(i).getTotal() > 0)
						rsList.get(i).setChange(100);
					else if (rsList.get(i).getTotal() < 0)
						rsList.get(i).setChange(-100);
					else
						rsList.get(i).setChange(0);
				}
			} else {
				if (rsList.get(i).getTotal() > 0)
					rsList.get(i).setChange(100);
				else if (rsList.get(i).getTotal() < 0)
					rsList.get(i).setChange(-100);
				else
					rsList.get(i).setChange(0);
			}
		}

		return rsList;
	}
	
	private static List<Statistic> getMonthsByProduct(Model model, HttpServletRequest request, OrderService orderService,Tbl_Product product) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
		LocalDate localDate = LocalDate.now();
		int currentYear = localDate.getYear();
		List<Statistic> rsList = new ArrayList<Statistic>();
		for (int i = 1; i <= 12; i++) {
			Statistic statistic = new Statistic();
			statistic.setTime(i);
			statistic.setTotal(orderService.findTotalByMonthOrderByProduct(currentYear, i,product));
			rsList.add(statistic);
		}

		for (int i = 0; i < 12; i++) {
			if (i > 0) {
				if (rsList.get(i - 1).getTotal() != 0) {
					int sub = rsList.get(i).getTotal()- rsList.get(i-1).getTotal();
					double div = (double)sub / rsList.get(i-1).getTotal();
					rsList.get(i).setChange((double) Math.ceil(div * 10000) / 100) ;
				} else {
					if (rsList.get(i).getTotal() > 0)
						rsList.get(i).setChange(100);
					else if (rsList.get(i).getTotal() < 0)
						rsList.get(i).setChange(-100);
					else
						rsList.get(i).setChange(0);
				}
			} else {
				if (rsList.get(i).getTotal() > 0)
					rsList.get(i).setChange(100);
				else if (rsList.get(i).getTotal() < 0)
					rsList.get(i).setChange(-100);
				else
					rsList.get(i).setChange(0);
			}
		}

		return rsList;
	}
	
	private static List<Statistic> getMonthsByCategory(Model model, HttpServletRequest request, OrderService orderService,Tbl_Category category) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
		LocalDate localDate = LocalDate.now();
		int currentYear = localDate.getYear();
		List<Statistic> rsList = new ArrayList<Statistic>();
		for (int i = 1; i <= 12; i++) {
			Statistic statistic = new Statistic();
			statistic.setTime(i);
			statistic.setTotal(orderService.findTotalByMonthOrderByCategory(currentYear, i,category));
			rsList.add(statistic);
		}

		for (int i = 0; i < 12; i++) {
			if (i > 0) {
				if (rsList.get(i - 1).getTotal() != 0) {
					int sub = rsList.get(i).getTotal()- rsList.get(i-1).getTotal();
					double div = (double)sub / rsList.get(i-1).getTotal();
					rsList.get(i).setChange((double) Math.ceil(div * 10000) / 100) ;
				} else {
					if (rsList.get(i).getTotal() > 0)
						rsList.get(i).setChange(100);
					else if (rsList.get(i).getTotal() < 0)
						rsList.get(i).setChange(-100);
					else
						rsList.get(i).setChange(0);
				}
			} else {
				if (rsList.get(i).getTotal() > 0)
					rsList.get(i).setChange(100);
				else if (rsList.get(i).getTotal() < 0)
					rsList.get(i).setChange(-100);
				else
					rsList.get(i).setChange(0);
			}
		}

		return rsList;
	}
	
	private static List<Statistic> getMonthsByManufacturer(Model model, HttpServletRequest request, OrderService orderService,Tbl_Manufacturer manufacturer) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
		LocalDate localDate = LocalDate.now();
		int currentYear = localDate.getYear();
		List<Statistic> rsList = new ArrayList<Statistic>();
		for (int i = 1; i <= 12; i++) {
			Statistic statistic = new Statistic();
			statistic.setTime(i);
			statistic.setTotal(orderService.findTotalByMonthOrderByManufacturer(currentYear, i,manufacturer));
			rsList.add(statistic);
		}

		for (int i = 0; i < 12; i++) {
			if (i > 0) {
				if (rsList.get(i - 1).getTotal() != 0) {
					int sub = rsList.get(i).getTotal()- rsList.get(i-1).getTotal();
					double div = (double)sub / rsList.get(i-1).getTotal();
					rsList.get(i).setChange((double) Math.ceil(div * 10000) / 100) ;
				} else {
					if (rsList.get(i).getTotal() > 0)
						rsList.get(i).setChange(100);
					else if (rsList.get(i).getTotal() < 0)
						rsList.get(i).setChange(-100);
					else
						rsList.get(i).setChange(0);
				}
			} else {
				if (rsList.get(i).getTotal() > 0)
					rsList.get(i).setChange(100);
				else if (rsList.get(i).getTotal() < 0)
					rsList.get(i).setChange(-100);
				else
					rsList.get(i).setChange(0);
			}
		}

		return rsList;
	}
	
	private static List<Statistic> getQuarter(Model model, HttpServletRequest request, OrderService orderService,
			int value) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
		LocalDate localDate = LocalDate.now();
		int currentYear = localDate.getYear();
		List<Statistic> rsList = new ArrayList<Statistic>();
		switch (value) {
		case 1:
			for (int i = 1; i <= 3; i++) {
				Statistic statistic = new Statistic();
				statistic.setTime(i);
				statistic.setTotal(orderService.findTotalByMonth(currentYear, i));
				rsList.add(statistic);
			}
			break;
		case 2:
			for (int i = 4; i <= 6; i++) {
				Statistic statistic = new Statistic();
				statistic.setTime(i);
				statistic.setTotal(orderService.findTotalByMonth(currentYear, i));
				rsList.add(statistic);
			}
			break;
		case 3:
			for (int i = 7; i <= 9; i++) {
				Statistic statistic = new Statistic();
				statistic.setTime(i);
				statistic.setTotal(orderService.findTotalByMonth(currentYear, i));
				rsList.add(statistic);
			}
			break;
		case 4:
			for (int i = 10; i <= 12; i++) {
				Statistic statistic = new Statistic();
				statistic.setTime(i);
				statistic.setTotal(orderService.findTotalByMonth(currentYear, i));
				rsList.add(statistic);
			}
			break;
		default:
			break;
		}

		for (int i = 0; i < 3; i++) {
			if (i > 0) {
				if (rsList.get(i - 1).getTotal() != 0) {
					int sub = rsList.get(i).getTotal()- rsList.get(i-1).getTotal();
					double div = (double)sub / rsList.get(i-1).getTotal();
					rsList.get(i).setChange((double) Math.ceil(div * 10000) / 100) ;
				} else {
					if (rsList.get(i).getTotal() > 0)
						rsList.get(i).setChange(100);
					else if (rsList.get(i).getTotal() < 0)
						rsList.get(i).setChange(-100);
					else
						rsList.get(i).setChange(0);
				}
			} else {
				if (rsList.get(i).getTotal() > 0)
					rsList.get(i).setChange(100);
				else if (rsList.get(i).getTotal() < 0)
					rsList.get(i).setChange(-100);
				else
					rsList.get(i).setChange(0);
			}
		}
		return rsList;
	}
	
	private static List<Statistic> getQuarterByProduct(Model model, HttpServletRequest request, OrderService orderService,
			int value, Tbl_Product product) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
		LocalDate localDate = LocalDate.now();
		int currentYear = localDate.getYear();
		List<Statistic> rsList = new ArrayList<Statistic>();
		switch (value) {
		case 1:
			for (int i = 1; i <= 3; i++) {
				Statistic statistic = new Statistic();
				statistic.setTime(i);
				statistic.setTotal(orderService.findTotalByMonthOrderByProduct(currentYear, i,product));
				rsList.add(statistic);
			}
			break;
		case 2:
			for (int i = 4; i <= 6; i++) {
				Statistic statistic = new Statistic();
				statistic.setTime(i);
				statistic.setTotal(orderService.findTotalByMonthOrderByProduct(currentYear, i,product));
				rsList.add(statistic);
			}
			break;
		case 3:
			for (int i = 7; i <= 9; i++) {
				Statistic statistic = new Statistic();
				statistic.setTime(i);
				statistic.setTotal(orderService.findTotalByMonthOrderByProduct(currentYear, i,product));
				rsList.add(statistic);
			}
			break;
		case 4:
			for (int i = 10; i <= 12; i++) {
				Statistic statistic = new Statistic();
				statistic.setTime(i);
				statistic.setTotal(orderService.findTotalByMonthOrderByProduct(currentYear, i,product));
				rsList.add(statistic);
			}
			break;
		default:
			break;
		}

		for (int i = 0; i < 3; i++) {
			if (i > 0) {
				if (rsList.get(i - 1).getTotal() != 0) {
					int sub = rsList.get(i).getTotal()- rsList.get(i-1).getTotal();
					double div = (double)sub / rsList.get(i-1).getTotal();
					rsList.get(i).setChange((double) Math.ceil(div * 10000) / 100) ;
				} else {
					if (rsList.get(i).getTotal() > 0)
						rsList.get(i).setChange(100);
					else if (rsList.get(i).getTotal() < 0)
						rsList.get(i).setChange(-100);
					else
						rsList.get(i).setChange(0);
				}
			} else {
				if (rsList.get(i).getTotal() > 0)
					rsList.get(i).setChange(100);
				else if (rsList.get(i).getTotal() < 0)
					rsList.get(i).setChange(-100);
				else
					rsList.get(i).setChange(0);
			}
		}
		return rsList;
	}
	
	private static List<Statistic> getQuarterByCategory(Model model, HttpServletRequest request, OrderService orderService,
			int value, Tbl_Category category) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
		LocalDate localDate = LocalDate.now();
		int currentYear = localDate.getYear();
		List<Statistic> rsList = new ArrayList<Statistic>();
		switch (value) {
		case 1:
			for (int i = 1; i <= 3; i++) {
				Statistic statistic = new Statistic();
				statistic.setTime(i);
				statistic.setTotal(orderService.findTotalByMonthOrderByCategory(currentYear, i,category));
				rsList.add(statistic);
			}
			break;
		case 2:
			for (int i = 4; i <= 6; i++) {
				Statistic statistic = new Statistic();
				statistic.setTime(i);
				statistic.setTotal(orderService.findTotalByMonthOrderByCategory(currentYear, i,category));
				rsList.add(statistic);
			}
			break;
		case 3:
			for (int i = 7; i <= 9; i++) {
				Statistic statistic = new Statistic();
				statistic.setTime(i);
				statistic.setTotal(orderService.findTotalByMonthOrderByCategory(currentYear, i,category));
				rsList.add(statistic);
			}
			break;
		case 4:
			for (int i = 10; i <= 12; i++) {
				Statistic statistic = new Statistic();
				statistic.setTime(i);
				statistic.setTotal(orderService.findTotalByMonthOrderByCategory(currentYear, i,category));
				rsList.add(statistic);
			}
			break;
		default:
			break;
		}

		for (int i = 0; i < 3; i++) {
			if (i > 0) {
				if (rsList.get(i - 1).getTotal() != 0) {
					int sub = rsList.get(i).getTotal()- rsList.get(i-1).getTotal();
					double div = (double)sub / rsList.get(i-1).getTotal();
					rsList.get(i).setChange((double) Math.ceil(div * 10000) / 100) ;
				} else {
					if (rsList.get(i).getTotal() > 0)
						rsList.get(i).setChange(100);
					else if (rsList.get(i).getTotal() < 0)
						rsList.get(i).setChange(-100);
					else
						rsList.get(i).setChange(0);
				}
			} else {
				if (rsList.get(i).getTotal() > 0)
					rsList.get(i).setChange(100);
				else if (rsList.get(i).getTotal() < 0)
					rsList.get(i).setChange(-100);
				else
					rsList.get(i).setChange(0);
			}
		}
		return rsList;
	}
	
	private static List<Statistic> getQuarterByManufacturer(Model model, HttpServletRequest request, OrderService orderService,
			int value, Tbl_Manufacturer manufacturer) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
		LocalDate localDate = LocalDate.now();
		int currentYear = localDate.getYear();
		List<Statistic> rsList = new ArrayList<Statistic>();
		switch (value) {
		case 1:
			for (int i = 1; i <= 3; i++) {
				Statistic statistic = new Statistic();
				statistic.setTime(i);
				statistic.setTotal(orderService.findTotalByMonthOrderByManufacturer(currentYear, i,manufacturer));
				rsList.add(statistic);
			}
			break;
		case 2:
			for (int i = 4; i <= 6; i++) {
				Statistic statistic = new Statistic();
				statistic.setTime(i);
				statistic.setTotal(orderService.findTotalByMonthOrderByManufacturer(currentYear, i,manufacturer));
				rsList.add(statistic);
			}
			break;
		case 3:
			for (int i = 7; i <= 9; i++) {
				Statistic statistic = new Statistic();
				statistic.setTime(i);
				statistic.setTotal(orderService.findTotalByMonthOrderByManufacturer(currentYear, i,manufacturer));
				rsList.add(statistic);
			}
			break;
		case 4:
			for (int i = 10; i <= 12; i++) {
				Statistic statistic = new Statistic();
				statistic.setTime(i);
				statistic.setTotal(orderService.findTotalByMonthOrderByManufacturer(currentYear, i,manufacturer));
				rsList.add(statistic);
			}
			break;
		default:
			break;
		}

		for (int i = 0; i < 3; i++) {
			if (i > 0) {
				if (rsList.get(i - 1).getTotal() != 0) {
					int sub = rsList.get(i).getTotal()- rsList.get(i-1).getTotal();
					double div = (double)sub / rsList.get(i-1).getTotal();
					rsList.get(i).setChange((double) Math.ceil(div * 10000) / 100) ;
				} else {
					if (rsList.get(i).getTotal() > 0)
						rsList.get(i).setChange(100);
					else if (rsList.get(i).getTotal() < 0)
						rsList.get(i).setChange(-100);
					else
						rsList.get(i).setChange(0);
				}
			} else {
				if (rsList.get(i).getTotal() > 0)
					rsList.get(i).setChange(100);
				else if (rsList.get(i).getTotal() < 0)
					rsList.get(i).setChange(-100);
				else
					rsList.get(i).setChange(0);
			}
		}
		return rsList;
	}

	private static List<Statistic> getQuarters(Model model, HttpServletRequest request, OrderService orderService,
			int value) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
		LocalDate localDate = LocalDate.now();
		int currentYear = localDate.getYear();
		List<Statistic> rsList = new ArrayList<Statistic>();
		for (int idx = 0; idx < 4; idx++) {
			switch (idx) {
			case 0:
				int total1 = 0;
				for (int i = 1; i <= 3; i++) {
					total1 += orderService.findTotalByMonth(currentYear, i);
				}
				Statistic statistic1 = new Statistic();
				statistic1.setTime(idx + 1);
				statistic1.setTotal(total1);
				rsList.add(statistic1);
				break;
			case 1:
				int total2 = 0;
				for (int i = 4; i <= 6; i++) {
					total2 += orderService.findTotalByMonth(currentYear, i);
				}
				Statistic statistic2 = new Statistic();
				statistic2.setTime(idx + 1);
				statistic2.setTotal(total2);
				rsList.add(statistic2);
				break;
			case 2:
				int total3 = 0;
				for (int i = 7; i <= 9; i++) {
					total3 += orderService.findTotalByMonth(currentYear, i);
				}
				Statistic statistic3 = new Statistic();
				statistic3.setTime(idx + 1);
				statistic3.setTotal(total3);
				rsList.add(statistic3);
				break;
			case 3:
				int total4 = 0;
				for (int i = 10; i <= 12; i++) {
					total4 += orderService.findTotalByMonth(currentYear, i);
				}
				Statistic statistic4 = new Statistic();
				statistic4.setTime(idx + 1);
				statistic4.setTotal(total4);
				rsList.add(statistic4);
				break;
			default:
				break;
			}

			if (idx > 0) {
				if (rsList.get(idx - 1).getTotal() != 0) {
					int sub = rsList.get(idx).getTotal()- rsList.get(idx-1).getTotal();
					double div = (double)sub / rsList.get(idx-1).getTotal();
					rsList.get(idx).setChange((double) Math.ceil(div * 10000) / 100) ;
				} else {
					if (rsList.get(idx).getTotal() > 0)
						rsList.get(idx).setChange(100);
					else if (rsList.get(idx).getTotal() < 0)
						rsList.get(idx).setChange(-100);
					else
						rsList.get(idx).setChange(0);
				}
			} else {
				if (rsList.get(idx).getTotal() > 0)
					rsList.get(idx).setChange(100);
				else if (rsList.get(idx).getTotal() < 0)
					rsList.get(idx).setChange(-100);
				else
					rsList.get(idx).setChange(0);
			}
		}
		return rsList;
	}
	
	private static List<Statistic> getQuartersByProduct(Model model, HttpServletRequest request, OrderService orderService,
			int value,Tbl_Product product) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
		LocalDate localDate = LocalDate.now();
		int currentYear = localDate.getYear();
		List<Statistic> rsList = new ArrayList<Statistic>();
		for (int idx = 0; idx < 4; idx++) {
			switch (idx) {
			case 0:
				int total1 = 0;
				for (int i = 1; i <= 3; i++) {
					total1 += orderService.findTotalByMonthOrderByProduct(currentYear, i,product);
				}
				Statistic statistic1 = new Statistic();
				statistic1.setTime(idx + 1);
				statistic1.setTotal(total1);
				rsList.add(statistic1);
				break;
			case 1:
				int total2 = 0;
				for (int i = 4; i <= 6; i++) {
					total2 += orderService.findTotalByMonthOrderByProduct(currentYear, i,product);
				}
				Statistic statistic2 = new Statistic();
				statistic2.setTime(idx + 1);
				statistic2.setTotal(total2);
				rsList.add(statistic2);
				break;
			case 2:
				int total3 = 0;
				for (int i = 7; i <= 9; i++) {
					total3 += orderService.findTotalByMonthOrderByProduct(currentYear, i,product);
				}
				Statistic statistic3 = new Statistic();
				statistic3.setTime(idx + 1);
				statistic3.setTotal(total3);
				rsList.add(statistic3);
				break;
			case 3:
				int total4 = 0;
				for (int i = 10; i <= 12; i++) {
					total4 += orderService.findTotalByMonthOrderByProduct(currentYear, i,product);
				}
				Statistic statistic4 = new Statistic();
				statistic4.setTime(idx + 1);
				statistic4.setTotal(total4);
				rsList.add(statistic4);
				break;
			default:
				break;
			}

			if (idx > 0) {
				if (rsList.get(idx - 1).getTotal() != 0) {
					int sub = rsList.get(idx).getTotal()- rsList.get(idx-1).getTotal();
					double div = (double)sub / rsList.get(idx-1).getTotal();
					rsList.get(idx).setChange((double) Math.ceil(div * 10000) / 100) ;
				} else {
					if (rsList.get(idx).getTotal() > 0)
						rsList.get(idx).setChange(100);
					else if (rsList.get(idx).getTotal() < 0)
						rsList.get(idx).setChange(-100);
					else
						rsList.get(idx).setChange(0);
				}
			} else {
				if (rsList.get(idx).getTotal() > 0)
					rsList.get(idx).setChange(100);
				else if (rsList.get(idx).getTotal() < 0)
					rsList.get(idx).setChange(-100);
				else
					rsList.get(idx).setChange(0);
			}
		}
		return rsList;
	}
	
	private static List<Statistic> getQuartersByCategory(Model model, HttpServletRequest request, OrderService orderService,
			int value, Tbl_Category category) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
		LocalDate localDate = LocalDate.now();
		int currentYear = localDate.getYear();
		List<Statistic> rsList = new ArrayList<Statistic>();
		for (int idx = 0; idx < 4; idx++) {
			switch (idx) {
			case 0:
				int total1 = 0;
				for (int i = 1; i <= 3; i++) {
					total1 += orderService.findTotalByMonthOrderByCategory(currentYear, i,category);
				}
				Statistic statistic1 = new Statistic();
				statistic1.setTime(idx + 1);
				statistic1.setTotal(total1);
				rsList.add(statistic1);
				break;
			case 1:
				int total2 = 0;
				for (int i = 4; i <= 6; i++) {
					total2 += orderService.findTotalByMonthOrderByCategory(currentYear, i,category);
				}
				Statistic statistic2 = new Statistic();
				statistic2.setTime(idx + 1);
				statistic2.setTotal(total2);
				rsList.add(statistic2);
				break;
			case 2:
				int total3 = 0;
				for (int i = 7; i <= 9; i++) {
					total3 += orderService.findTotalByMonthOrderByCategory(currentYear, i,category);
				}
				Statistic statistic3 = new Statistic();
				statistic3.setTime(idx + 1);
				statistic3.setTotal(total3);
				rsList.add(statistic3);
				break;
			case 3:
				int total4 = 0;
				for (int i = 10; i <= 12; i++) {
					total4 += orderService.findTotalByMonthOrderByCategory(currentYear, i,category);
				}
				Statistic statistic4 = new Statistic();
				statistic4.setTime(idx + 1);
				statistic4.setTotal(total4);
				rsList.add(statistic4);
				break;
			default:
				break;
			}

			if (idx > 0) {
				if (rsList.get(idx - 1).getTotal() != 0) {
					int sub = rsList.get(idx).getTotal()- rsList.get(idx-1).getTotal();
					double div = (double)sub / rsList.get(idx-1).getTotal();
					rsList.get(idx).setChange((double) Math.ceil(div * 10000) / 100) ;
				} else {
					if (rsList.get(idx).getTotal() > 0)
						rsList.get(idx).setChange(100);
					else if (rsList.get(idx).getTotal() < 0)
						rsList.get(idx).setChange(-100);
					else
						rsList.get(idx).setChange(0);
				}
			} else {
				if (rsList.get(idx).getTotal() > 0)
					rsList.get(idx).setChange(100);
				else if (rsList.get(idx).getTotal() < 0)
					rsList.get(idx).setChange(-100);
				else
					rsList.get(idx).setChange(0);
			}
		}
		return rsList;
	}
	
	private static List<Statistic> getQuartersByManufacturer(Model model, HttpServletRequest request, OrderService orderService,
			int value, Tbl_Manufacturer manufacturer) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
		LocalDate localDate = LocalDate.now();
		int currentYear = localDate.getYear();
		List<Statistic> rsList = new ArrayList<Statistic>();
		for (int idx = 0; idx < 4; idx++) {
			switch (idx) {
			case 0:
				int total1 = 0;
				for (int i = 1; i <= 3; i++) {
					total1 += orderService.findTotalByMonthOrderByManufacturer(currentYear, i,manufacturer);
				}
				Statistic statistic1 = new Statistic();
				statistic1.setTime(idx + 1);
				statistic1.setTotal(total1);
				rsList.add(statistic1);
				break;
			case 1:
				int total2 = 0;
				for (int i = 4; i <= 6; i++) {
					total2 += orderService.findTotalByMonthOrderByManufacturer(currentYear, i,manufacturer);
				}
				Statistic statistic2 = new Statistic();
				statistic2.setTime(idx + 1);
				statistic2.setTotal(total2);
				rsList.add(statistic2);
				break;
			case 2:
				int total3 = 0;
				for (int i = 7; i <= 9; i++) {
					total3 += orderService.findTotalByMonthOrderByManufacturer(currentYear, i,manufacturer);
				}
				Statistic statistic3 = new Statistic();
				statistic3.setTime(idx + 1);
				statistic3.setTotal(total3);
				rsList.add(statistic3);
				break;
			case 3:
				int total4 = 0;
				for (int i = 10; i <= 12; i++) {
					total4 += orderService.findTotalByMonthOrderByManufacturer(currentYear, i,manufacturer);
				}
				Statistic statistic4 = new Statistic();
				statistic4.setTime(idx + 1);
				statistic4.setTotal(total4);
				rsList.add(statistic4);
				break;
			default:
				break;
			}

			if (idx > 0) {
				if (rsList.get(idx - 1).getTotal() != 0) {
					int sub = rsList.get(idx).getTotal()- rsList.get(idx-1).getTotal();
					double div = (double)sub / rsList.get(idx-1).getTotal();
					rsList.get(idx).setChange((double) Math.ceil(div * 10000) / 100) ;
				} else {
					if (rsList.get(idx).getTotal() > 0)
						rsList.get(idx).setChange(100);
					else if (rsList.get(idx).getTotal() < 0)
						rsList.get(idx).setChange(-100);
					else
						rsList.get(idx).setChange(0);
				}
			} else {
				if (rsList.get(idx).getTotal() > 0)
					rsList.get(idx).setChange(100);
				else if (rsList.get(idx).getTotal() < 0)
					rsList.get(idx).setChange(-100);
				else
					rsList.get(idx).setChange(0);
			}
		}
		return rsList;
	}
}
