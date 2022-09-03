package com.jsfw.models;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the Tbl_Order_Detail database table.
 * 
 */
@Entity
@NamedQuery(name="Tbl_Order_Detail.findAll", query="SELECT t FROM Tbl_Order_Detail t")
public class Tbl_Order_Detail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;

	@Column(name="Amount")
	private int amount;

	@Column(name="CreateTime")
	private Date createTime;

	@Column(name="Price")
	private double price;

	@Column(name="UpdateTime")
	private Date updateTime;

	//bi-directional many-to-one association to Tbl_Order
	@ManyToOne
	@JoinColumn(name="OrderId")
	private Tbl_Order tblOrder;

	//bi-directional many-to-one association to Tbl_Product
	@ManyToOne
	@JoinColumn(name="ProductId")
	private Tbl_Product tblProduct;

	public Tbl_Order_Detail() {
	}

	public Tbl_Order_Detail(int id, int amount, double price) {
		super();
		this.id = id;
		this.amount = amount;
		this.price = price;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Tbl_Order getTblOrder() {
		return this.tblOrder;
	}

	public void setTblOrder(Tbl_Order tblOrder) {
		this.tblOrder = tblOrder;
	}

	public Tbl_Product getTblProduct() {
		return this.tblProduct;
	}

	public void setTblProduct(Tbl_Product tblProduct) {
		this.tblProduct = tblProduct;
	}

}