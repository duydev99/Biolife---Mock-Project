package com.jsfw.models;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the Tbl_Comment database table.
 * 
 */
@Entity
@NamedQuery(name="Tbl_Comment.findAll", query="SELECT t FROM Tbl_Comment t")
public class Tbl_Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;

	@Column(name="Content", columnDefinition = "nvarchar", nullable = false)
	private String content;

	@Column(name="CreateTime")
	private String createTime;

	//bi-directional many-to-one association to Tbl_Product
	@ManyToOne
	@JoinColumn(name="ProductId")
	private Tbl_Product tblProduct;

	//bi-directional many-to-one association to Tbl_User
	@ManyToOne
	@JoinColumn(name="UserId")
	private Tbl_User tblUser;

	public Tbl_Comment() {
	}

	

	public Tbl_Comment(int id, String content) {
		super();
		this.id = id;
		this.content = content;
	}



	public Tbl_Comment(String content) {
		super();
		this.content = content;
	}



	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String string) {
		this.createTime = string;
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