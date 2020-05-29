package com.vinan.reactproject.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = User.TABLE_NAME)
public class User {
	public static final String TABLE_NAME = "tabel_user";

	@Id
	@GeneratedValue
	@Column(name = "id_user")
	private Integer id;

	@Column(name = "email")
	private String email;

	@Column(name = "phone_number")
	private String phone;

	@Column(name = "password")
	private String password;
}
