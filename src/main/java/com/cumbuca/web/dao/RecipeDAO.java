package com.cumbuca.web.dao;

import java.util.List;

import com.cumbuca.web.entity.Recipe;

public interface RecipeDAO extends GenericDAO<Recipe, Integer>{

	public List<Recipe> recipes(List<Integer> foodIds);
	
}
