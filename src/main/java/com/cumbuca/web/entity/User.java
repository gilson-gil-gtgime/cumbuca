package com.cumbuca.web.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tb_users")
public class User {
	@Id
	@SequenceGenerator(name="user", sequenceName="sq_tb_users", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user")
	@Column(name="id_user")
	private int id;
	
	@Column(name="ds_email", nullable=false)
	private String email;
	
	@Column(name="nm_username", nullable=false)
	private String username;
	
	@OneToMany(mappedBy="user")
	private List<FoodRecord> foods;
	
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

	public List<FoodRecord> getFoods() {
		return foods;
	}

	public void setFoods(List<FoodRecord> foods) {
		this.foods = foods;
	}
}