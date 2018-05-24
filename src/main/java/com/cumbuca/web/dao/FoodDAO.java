package com.cumbuca.web.dao;

import com.cumbuca.web.entity.Food;

public interface FoodDAO extends GenericDAO<Food, Integer>{
	public Food getFoodWithName(String name);
}
