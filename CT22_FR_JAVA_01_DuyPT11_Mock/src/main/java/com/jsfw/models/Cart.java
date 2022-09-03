package com.jsfw.models;

import java.util.Optional;

public class Cart {
	private int id;
	private Tbl_Product product;
	private int amount;
	private String image;
	
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Tbl_Product getProduct() {
		return product;
	}
	public void setProduct(Tbl_Product product) {
		this.product = product;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
