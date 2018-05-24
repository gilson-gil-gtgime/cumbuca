package com.cumbuca.web.entity;

public class RecipeForm {
	
	private int id;
	private String name;
	private String foods;

	public RecipeForm() {
		super();
	}
	
	public RecipeForm(String name, String foods) {
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

	public String getFoods() {
		return foods;
	}

	public void setFoods(String foods) {
		this.foods = foods;
	}
}
