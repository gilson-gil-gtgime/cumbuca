package com.cumbuca.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="tb_users", uniqueConstraints={@UniqueConstraint(columnNames={"ds_email", "nm_username"})})
public class User {
	@Id
	@SequenceGenerator(name="user", sequenceName="sq_tb_users", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user")
	@Column(name="id_user")
	private int id;
	
	@Column(name="ds_email", nullable=false, length=100, unique=true)
	private String email;
	
	@Column(name="nm_username", nullable=false, length=30, unique=true)
	private String username;
	
	public User() {
		super();
	}
	
	public User(int id, String email, String username) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}