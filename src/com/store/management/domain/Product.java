package com.store.management.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(schema = "store", name = "product")
public class Product {
	@Id
	@GeneratedValue(generator="product_id_product_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "product_id_product_seq", sequenceName = "store.product_id_product_seq")
	@Column(name = "id_product")
	private Integer idProduct;
	
	@Column(name = "product")
	private String product;
	
	@Column(name="price")
	private Double price;
	
	@Column(name="stock")
	private Integer stock;

	public Integer getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	
}
