package com.jsfw.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Tbl_Image_Product database table.
 * 
 */
@Entity
@NamedQuery(name="Tbl_Image_Product.findAll", query="SELECT t FROM Tbl_Image_Product t")
public class Tbl_Image_Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;

	@Column(name="Source")
	private String source;

	//bi-directional many-to-one association to Tbl_Product
	@ManyToOne
	@JoinColumn(name="ProductId")
	private Tbl_Product tblProduct;

	public Tbl_Image_Product() {
	}

	public Tbl_Image_Product(String source) {
		super();
		this.source = source;
	}

	public Tbl_Image_Product(int id, String source) {
		super();
		this.id = id;
		this.source = source;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Tbl_Product getTblProduct() {
		return this.tblProduct;
	}

	public void setTblProduct(Tbl_Product tblProduct) {
		this.tblProduct = tblProduct;
	}

}