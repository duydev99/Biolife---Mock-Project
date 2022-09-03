package com.jsfw.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 * The persistent class for the Tbl_Order database table.
 * 
 */
@Entity
@NamedQuery(name="Tbl_Order.findAll", query="SELECT t FROM Tbl_Order t")
public class Tbl_Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;

	@Column(name="Address", columnDefinition = "nvarchar", nullable = false)
	private String address;

	@Column(name="CreateTime")
	private Date createTime;

	@Column(name="Phone")
	private String phone;

	@Column(name="Status")
	private int status;

	@Column(name="Total")
	private int total;

	@Column(name="UpdateTime")
	private Date updateTime;

	//bi-directional many-to-one association to Tbl_Payment
	@ManyToOne
	@JoinColumn(name="PaymentId")
	private Tbl_Payment tblPayment;

	//bi-directional many-to-one association to Tbl_User
	@ManyToOne
	@JoinColumn(name="UserId")
	private Tbl_User tblUser;

	//bi-directional many-to-one association to Tbl_Order_Detail
	@OneToMany(mappedBy="tblOrder")
	private List<Tbl_Order_Detail> tblOrderDetails;

	public Tbl_Order() {
	}

	public int getId() {
		return this.id;
	}

	public Tbl_Order(int id, Date createTime, int total) {
		super();
		this.id = id;
		this.createTime = createTime;
		this.total = total;
	}

	public Tbl_Order(int id, String address, int total) {
		super();
		this.id = id;
		this.address = address;
		this.total = total;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date date) {
		this.createTime = date;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getTotal() {
		return this.total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Tbl_Payment getTblPayment() {
		return this.tblPayment;
	}

	public void setTblPayment(Tbl_Payment tblPayment) {
		this.tblPayment = tblPayment;
	}

	public Tbl_User getTblUser() {
		return this.tblUser;
	}

	public void setTblUser(Tbl_User tblUser) {
		this.tblUser = tblUser;
	}

	public List<Tbl_Order_Detail> getTblOrderDetails() {
		return this.tblOrderDetails;
	}

	public void setTblOrderDetails(List<Tbl_Order_Detail> tblOrderDetails) {
		this.tblOrderDetails = tblOrderDetails;
	}

	public Tbl_Order_Detail addTblOrderDetail(Tbl_Order_Detail tblOrderDetail) {
		getTblOrderDetails().add(tblOrderDetail);
		tblOrderDetail.setTblOrder(this);

		return tblOrderDetail;
	}

	public Tbl_Order_Detail removeTblOrderDetail(Tbl_Order_Detail tblOrderDetail) {
		getTblOrderDetails().remove(tblOrderDetail);
		tblOrderDetail.setTblOrder(null);

		return tblOrderDetail;
	}

}