package com.jsfw.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.jsfw.models.Cart;
import com.jsfw.models.Tbl_Order;

public class MailUtils {

	public static String getMail(List<Cart> carts, Tbl_Order order) {
		String mailString = "";
		String total = order.getTotal()+" vnđ";
		String phone = order.getPhone();
		String address = order.getAddress();
		String pattern = "dd/MM/yyyy HH:mm:ss";
		DateFormat df = new SimpleDateFormat(pattern);
		String time = df.format(order.getCreateTime());
		String payment = order.getTblPayment().getMethod();
		for (Cart c : carts) {
			mailString += "<p><b>" + c.getProduct().getName() + "</b>: " + c.getProduct().getPrice() + " x"
					+ c.getAmount() + " = " + c.getAmount() * c.getProduct().getPrice() + " vnđ</p>";
		}
		
		String rs = "<p><h3>Cảm bạn đã ủng hộ chúng tôi!</h3></p>"
				+ "<p>Hóa đơn của bạn:</p>"
				+ "<p>Số điện thoại nhận hàng: "+phone+"</p>"
				+ "<p>Địa chỉ nhận hàng: "+address+"</p>"
				+ "<p>Thời gian đặt hàng: "+time+"</p>"
				+ "<p>Hình thức thanh toán: "+payment+"</p>"
				+ mailString
				+ "<p>Tổng tiền bạn phải thanh toán: "+total+"</p>";
		
		return rs;
	}
}
