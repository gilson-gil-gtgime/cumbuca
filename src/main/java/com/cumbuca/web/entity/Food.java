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
@Table(name="tb_food", uniqueConstraints={@UniqueConstraint(columnNames={"nm_food"})})
public class Food {
	@Id
	@SequenceGenerator(name="food", sequenceName="sq_tb_food", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="food")
	@Column(name="id_food")
	private int id;
	
	@Column(name="nm_food", nullable=false)
	private String name;

	public Food() {
		super();
	}
	
	public Food(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
