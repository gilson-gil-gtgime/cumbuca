package com.cumbuca.web.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.cumbuca.web.dao.RecipeDAO;
import com.cumbuca.web.entity.Recipe;

@Repository
public class RecipeDAOImpl extends GenericDAOImpl<Recipe, Integer> implements RecipeDAO{

	public List<Recipe> recipes(List<Integer> foodIds) {
		@SuppressWarnings("unchecked")
		List<Recipe> recipes = em.createQuery(
				"  SELECT r "
				+ "FROM Recipe r "
				+ "JOIN r.foods f "
				+ "WHERE f.id IN :foodIds")
				.setParameter("foodIds", foodIds)
				.getResultList();
		List<Recipe> unique = recipes
				.stream()
				.distinct()
				.collect(Collectors.toList());
		unique.sort(Recipe::compareTo);
		return unique;
	}

}