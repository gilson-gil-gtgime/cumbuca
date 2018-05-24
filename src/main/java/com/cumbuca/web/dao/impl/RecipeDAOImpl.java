package com.cumbuca.web.dao.impl;

import org.springframework.stereotype.Repository;

import com.cumbuca.web.dao.RecipeDAO;
import com.cumbuca.web.entity.Recipe;

@Repository
public class RecipeDAOImpl extends GenericDAOImpl<Recipe, Integer> implements RecipeDAO{

}