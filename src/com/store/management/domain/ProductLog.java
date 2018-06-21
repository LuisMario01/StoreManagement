package com.store.management.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(schema = "store", name = "product_log")
public class ProductLog {
	@Id
	@GeneratedValue(generator="product_log_id_product_log_seq", strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "product_log_id_product_log_seq", sequenceName = "store.product_log_id_product_log_seq", allocationSize=1)
	@Column(name = "id_product_log")
	private Integer idProductLog;
	
	@Column(name="previous_price")
	private Double previousPrice;
	
	@Column(name="current_price")
	private Double currentPrice;
	
	@Column(name="id_product")
	private Integer idProduct;
	
	public Integer getIdProductLog() {
		return idProductLog;
	}
	public void setIdProductLog(Integer idProductLog) {
		this.idProductLog = idProductLog;
	}
	public Double getPreviousPrice() {
		return previousPrice;
	}
	public void setPreviousPrice(Double previousPrice) {
		this.previousPrice = previousPrice;
	}
	public Double getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(Double currentPrice) {
		this.currentPrice = currentPrice;
	}
	public Integer getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}
	
}
