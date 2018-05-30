package com.cumbuca.web.entity;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tb_recipe")
public class Recipe {
	@Id
	@SequenceGenerator(name="recipe", sequenceName="sq_tb_recipe", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="recipe")
	@Column(name="id_recipe")
	private int id;
	
	@Column(name="nm_recipe")
	private String name;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(joinColumns=@JoinColumn(name="id_recipe"), inverseJoinColumns=@JoinColumn(name="id_food"), name="tb_recipe_food")
	private List<Food> foods;

	public Recipe() {
		super();
	}
	
	public Recipe(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Recipe(String name, List<Food> foods) {
		super();
		this.name = name;
		this.foods = foods;
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
	
	public List<Food> getFoods() {
		return foods;
	}
	
	public void setFoods(List<Food> foods) {
		this.foods = foods;
	}
	
	public String commaSeparatedFoods() {
		return this.foods.stream().map(Food::getName).collect(Collectors.joining(", "));
	}
	
	public int compareTo(Recipe compareRecipe) {
		return this.name.compareTo(compareRecipe.name);
	}	
}
