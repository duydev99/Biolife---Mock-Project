package com.jsfw.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Tbl_Product database table.
 * 
 */
@Entity
@NamedQuery(name="Tbl_Product.findAll", query="SELECT t FROM Tbl_Product t")
public class Tbl_Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;

	@Column(name="Amount")
	private int amount;

	@Column(name="Description", columnDefinition = "nvarchar", nullable = false)
	private String description;

	@Column(name="Name", columnDefinition = "nvarchar", nullable = false)
	private String name;

	@Column(name="Price")
	private double price;
	
	//bi-directional many-to-one association to Tbl_Comment
	@OneToMany(mappedBy="tblProduct")
	private List<Tbl_Comment> tblComments;

	//bi-directional many-to-one association to Tbl_Image_Product
	@OneToMany(mappedBy="tblProduct")
	private List<Tbl_Image_Product> tblImageProducts;

	//bi-directional many-to-one association to Tbl_Order_Detail
	@OneToMany(mappedBy="tblProduct")
	private List<Tbl_Order_Detail> tblOrderDetails;

	//bi-directional many-to-one association to Tbl_Category
	@ManyToOne
	@JoinColumn(name="CategoryId")
	private Tbl_Category tblCategory;

	//bi-directional many-to-one association to Tbl_Manufacturer
	@ManyToOne
	@JoinColumn(name="ManufacturerId")
	private Tbl_Manufacturer tblManufacturer;

	//bi-directional many-to-one association to Tbl_Vote
	@OneToMany(mappedBy="tblProduct")
	private List<Tbl_Vote> tblVotes;

	public Tbl_Product() {
	}

	public Tbl_Product(int id, String name) {
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

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<Tbl_Comment> getTblComments() {
		return this.tblComments;
	}

	public void setTblComments(List<Tbl_Comment> tblComments) {
		this.tblComments = tblComments;
	}

	public Tbl_Comment addTblComment(Tbl_Comment tblComment) {
		getTblComments().add(tblComment);
		tblComment.setTblProduct(this);

		return tblComment;
	}

	public Tbl_Comment removeTblComment(Tbl_Comment tblComment) {
		getTblComments().remove(tblComment);
		tblComment.setTblProduct(null);

		return tblComment;
	}

	public List<Tbl_Image_Product> getTblImageProducts() {
		return this.tblImageProducts;
	}

	public void setTblImageProducts(List<Tbl_Image_Product> tblImageProducts) {
		this.tblImageProducts = tblImageProducts;
	}

	public Tbl_Image_Product addTblImageProduct(Tbl_Image_Product tblImageProduct) {
		getTblImageProducts().add(tblImageProduct);
		tblImageProduct.setTblProduct(this);

		return tblImageProduct;
	}

	public Tbl_Image_Product removeTblImageProduct(Tbl_Image_Product tblImageProduct) {
		getTblImageProducts().remove(tblImageProduct);
		tblImageProduct.setTblProduct(null);

		return tblImageProduct;
	}

	public List<Tbl_Order_Detail> getTblOrderDetails() {
		return this.tblOrderDetails;
	}

	public void setTblOrderDetails(List<Tbl_Order_Detail> tblOrderDetails) {
		this.tblOrderDetails = tblOrderDetails;
	}

	public Tbl_Order_Detail addTblOrderDetail(Tbl_Order_Detail tblOrderDetail) {
		getTblOrderDetails().add(tblOrderDetail);
		tblOrderDetail.setTblProduct(this);

		return tblOrderDetail;
	}

	public Tbl_Order_Detail removeTblOrderDetail(Tbl_Order_Detail tblOrderDetail) {
		getTblOrderDetails().remove(tblOrderDetail);
		tblOrderDetail.setTblProduct(null);

		return tblOrderDetail;
	}

	public Tbl_Category getTblCategory() {
		return this.tblCategory;
	}

	public void setTblCategory(Tbl_Category tblCategory) {
		this.tblCategory = tblCategory;
	}

	public Tbl_Manufacturer getTblManufacturer() {
		return this.tblManufacturer;
	}

	public void setTblManufacturer(Tbl_Manufacturer tblManufacturer) {
		this.tblManufacturer = tblManufacturer;
	}

	public List<Tbl_Vote> getTblVotes() {
		return this.tblVotes;
	}

	public void setTblVotes(List<Tbl_Vote> tblVotes) {
		this.tblVotes = tblVotes;
	}

	public Tbl_Vote addTblVote(Tbl_Vote tblVote) {
		getTblVotes().add(tblVote);
		tblVote.setTblProduct(this);

		return tblVote;
	}

	public Tbl_Vote removeTblVote(Tbl_Vote tblVote) {
		getTblVotes().remove(tblVote);
		tblVote.setTblProduct(null);

		return tblVote;
	}

}