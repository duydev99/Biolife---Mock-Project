package com.jsfw.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the Tbl_Vote database table.
 * 
 */
@Entity
@NamedQuery(name="Tbl_Vote.findAll", query="SELECT t FROM Tbl_Vote t")
public class Tbl_Vote implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;

	@Column(name="CreateTime")
	private String createTime;

	@Column(name="Star")
	private int star;

	//bi-directional many-to-one association to Tbl_Product
	@ManyToOne
	@JoinColumn(name="ProductId")
	private Tbl_Product tblProduct;

	//bi-directional many-to-one association to Tbl_User
	@ManyToOne
	@JoinColumn(name="UserId")
	private Tbl_User tblUser;

	public Tbl_Vote() {
	}

	public Tbl_Vote(int id, int star) {
		super();
		this.id = id;
		this.star = star;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String string) {
		this.createTime = string;
	}

	public int getStar() {
		return this.star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public Tbl_Product getTblProduct() {
		return this.tblProduct;
	}

	public void setTblProduct(Tbl_Product tblProduct) {
		this.tblProduct = tblProduct;
	}

	public Tbl_User getTblUser() {
		return this.tblUser;
	}

	public void setTblUser(Tbl_User tblUser) {
		this.tblUser = tblUser;
	}

}