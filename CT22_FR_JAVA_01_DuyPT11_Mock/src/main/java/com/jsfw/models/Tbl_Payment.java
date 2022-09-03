package com.jsfw.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Tbl_Payment database table.
 * 
 */
@Entity
@NamedQuery(name="Tbl_Payment.findAll", query="SELECT t FROM Tbl_Payment t")
public class Tbl_Payment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;

	@Column(name="Method", columnDefinition = "nvarchar", nullable = false)
	private String method;

	//bi-directional many-to-one association to Tbl_Order
	@OneToMany(mappedBy="tblPayment")
	private List<Tbl_Order> tblOrders;

	public Tbl_Payment() {
	}

	public Tbl_Payment(int id, String method) {
		super();
		this.id = id;
		this.method = method;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMethod() {
		return this.method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public List<Tbl_Order> getTblOrders() {
		return this.tblOrders;
	}

	public void setTblOrders(List<Tbl_Order> tblOrders) {
		this.tblOrders = tblOrders;
	}

	public Tbl_Order addTblOrder(Tbl_Order tblOrder) {
		getTblOrders().add(tblOrder);
		tblOrder.setTblPayment(this);

		return tblOrder;
	}

	public Tbl_Order removeTblOrder(Tbl_Order tblOrder) {
		getTblOrders().remove(tblOrder);
		tblOrder.setTblPayment(null);

		return tblOrder;
	}

}