package com.cumbuca.web.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cumbuca.web.dao.FoodDAO;
import com.cumbuca.web.entity.Food;

@Repository
public class FoodDAOImpl extends GenericDAOImpl<Food, Integer> implements FoodDAO{
	
	public Food getFoodWithName(String name) {
		@SuppressWarnings("unchecked")
		List<Food> foods = em.createQuery("SELECT f FROM Food f WHERE f.name = :name")
			    			.setParameter("name", name)
			    			.setMaxResults(1)
			    			.getResultList();
		if (foods.isEmpty()) {
			return null;
		} else {
			return foods.get(0);
		}
	}
}