package com.store.management.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(schema = "store", name = "likes")
public class Like {
	@Id
	@GeneratedValue(generator="likes_id_like_seq", strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "likes_id_like_seq", sequenceName = "store.likes_id_like_seq", allocationSize = 1)
	@Column(name = "id_like")
	private Integer idLike;
	
	@Column(name="id_product")
	private Integer product;
	
	@Column(name="id_user")
	private Integer user;

	public Integer getIdLike() {
		return idLike;
	}

	public void setIdLike(Integer idLike) {
		this.idLike = idLike;
	}

	public Integer getIdProduct() {
		return product;
	}

	public void setIdProduct(Integer product) {
		this.product = product;
	}

	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}
}
