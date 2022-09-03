package com.jsfw.models;

import java.io.Serializable;
import javax.persistence.*;


import java.util.List;


/**
 * The persistent class for the Tbl_User database table.
 * 
 */
@Entity
@NamedQuery(name="Tbl_User.findAll", query="SELECT t FROM Tbl_User t")
public class Tbl_User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;
	
	@Column(name="Address", columnDefinition = "nvarchar", nullable = false)
	private String address;

	@Column(name="Name", columnDefinition = "nvarchar", nullable = false)
	private String name;

	@Column(name="Password")
	private String password;

	@Column(name="Phone")
	private String phone;
	
	@Column(name="Email")
	private String email;

	@Column(name="Status")
	private int status;

	@Column(name="Type")
	private int type;

	@Column(name="Username")
	private String username;

	//bi-directional many-to-one association to Tbl_Comment
	@OneToMany(mappedBy="tblUser")
	private List<Tbl_Comment> tblComments;

	//bi-directional many-to-one association to Tbl_Order
	@OneToMany(mappedBy="tblUser")
	private List<Tbl_Order> tblOrders;

	//bi-directional many-to-one association to Tbl_Vote
	@OneToMany(mappedBy="tblUser")
	private List<Tbl_Vote> tblVotes;

	public Tbl_User() {
	}

	public Tbl_User(int id, String password, String username) {
		super();
		this.id = id;
		this.password = password;
		this.username = username;
	}

	public Tbl_User(String name) {
		super();
		this.name = name;
	}

	public Tbl_User(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return this.id;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Tbl_Comment> getTblComments() {
		return this.tblComments;
	}

	public void setTblComments(List<Tbl_Comment> tblComments) {
		this.tblComments = tblComments;
	}

	public Tbl_Comment addTblComment(Tbl_Comment tblComment) {
		getTblComments().add(tblComment);
		tblComment.setTblUser(this);

		return tblComment;
	}

	public Tbl_Comment removeTblComment(Tbl_Comment tblComment) {
		getTblComments().remove(tblComment);
		tblComment.setTblUser(null);

		return tblComment;
	}

	public List<Tbl_Order> getTblOrders() {
		return this.tblOrders;
	}

	public void setTblOrders(List<Tbl_Order> tblOrders) {
		this.tblOrders = tblOrders;
	}

	public Tbl_Order addTblOrder(Tbl_Order tblOrder) {
		getTblOrders().add(tblOrder);
		tblOrder.setTblUser(this);

		return tblOrder;
	}

	public Tbl_Order removeTblOrder(Tbl_Order tblOrder) {
		getTblOrders().remove(tblOrder);
		tblOrder.setTblUser(null);

		return tblOrder;
	}

	public List<Tbl_Vote> getTblVotes() {
		return this.tblVotes;
	}

	public void setTblVotes(List<Tbl_Vote> tblVotes) {
		this.tblVotes = tblVotes;
	}

	public Tbl_Vote addTblVote(Tbl_Vote tblVote) {
		getTblVotes().add(tblVote);
		tblVote.setTblUser(this);

		return tblVote;
	}

	public Tbl_Vote removeTblVote(Tbl_Vote tblVote) {
		getTblVotes().remove(tblVote);
		tblVote.setTblUser(null);

		return tblVote;
	}
	
	

}