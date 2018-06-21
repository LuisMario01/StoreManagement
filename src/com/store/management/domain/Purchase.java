package com.store.management.domain;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(schema = "store", name = "purchase")
public class Purchase {
	
	private Integer idPurchase;
	private Integer idUser;
	private Integer idProduct;
	private Integer amount;
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
