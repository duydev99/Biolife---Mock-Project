package com.jsfw.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Tbl_Manufacturer database table.
 * 
 */
@Entity
@NamedQuery(name="Tbl_Manufacturer.findAll", query="SELECT t FROM Tbl_Manufacturer t")
public class Tbl_Manufacturer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;

	@Column(name="Name", columnDefinition = "nvarchar", nullable = false)
	private String name;

	//bi-directional many-to-one association to Tbl_Product
	@OneToMany(mappedBy="tblManufacturer")
	private List<Tbl_Product> tblProducts;

	public Tbl_Manufacturer() {
	}

	public Tbl_Manufacturer(String name) {
		super();
		this.name = name;
	}

	public Tbl_Manufacturer(int id, String name) {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Tbl_Product> getTblProducts() {
		return this.tblProducts;
	}

	public void setTblProducts(List<Tbl_Product> tblProducts) {
		this.tblProducts = tblProducts;
	}

	public Tbl_Product addTblProduct(Tbl_Product tblProduct) {
		getTblProducts().add(tblProduct);
		tblProduct.setTblManufacturer(this);

		return tblProduct;
	}

	public Tbl_Product removeTblProduct(Tbl_Product tblProduct) {
		getTblProducts().remove(tblProduct);
		tblProduct.setTblManufacturer(null);

		return tblProduct;
	}

}