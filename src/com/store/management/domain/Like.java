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
	@GeneratedValue(generator="likes_id_like_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "likes_id_like_seq", sequenceName = "store.likes_id_like_seq")
	@Column(name = "id_like")
	private Integer idLike;
	
	@Column(name="id_product")
	private Integer idProduct;
	
	@Column(name="id_user")
	private Integer idUser;

}
