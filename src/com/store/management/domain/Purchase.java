package com.store.management.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(schema = "store", name = "purchase")
public class Purchase {
	
	@Id
	@GeneratedValue(generator="purchase_id_purchase_seq", strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "purchase_id_purchase_seq", sequenceName = "store.purchase_id_purchase_seq", allocationSize=1)
	@Column(name = "id_purchase")
	private Integer idPurchase;
	
	@Column(name="id_user")
	private Integer idUser;
	
	@Column(name="id_product")
	private Integer idProduct;
	
	@Column(name="amount")
	private Integer amount;
	
	@Column(name="purchasedate")
	private Calendar date;
	
	public Integer getIdPurchase() {
		return idPurchase;
	}
	public void setIdPurchase(Integer idPurchase) {
		this.idPurchase = idPurchase;
	}
	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	public Integer getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
}
