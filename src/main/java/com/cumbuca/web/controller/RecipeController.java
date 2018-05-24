package com.cumbuca.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cumbuca.web.dao.FoodDAO;
import com.cumbuca.web.dao.RecipeDAO;
import com.cumbuca.web.entity.Food;
import com.cumbuca.web.entity.Recipe;
import com.cumbuca.web.entity.RecipeForm;

@Controller
@RequestMapping("recipe")
public class RecipeController {
	
	@Autowired
	private RecipeDAO dao;
	
	@Autowired
	private FoodDAO foodDao;

	@GetMapping("register")
	public String openForm(RecipeForm recipeForm) {
		return "recipe/register";
	}
	
	@Transactional
	@PostMapping("register")
	public ModelAndView processForm(RecipeForm recipeForm, RedirectAttributes redirect) {
	  try {
		  Recipe recipe = new Recipe();
		  recipe.setName(recipeForm.getName());
		  List<Food> foods = new ArrayList<Food>();
		  String[] foodNames = recipeForm.getFoods().split(",");
		  for (String foodName : foodNames) {
			  Food food = foodDao.getFoodWithName(foodName);
			  if (food == null) {
				  food = new Food();
				  food.setName(foodName);
				  foodDao.cadastrar(food);
			  }
			  
			  foods.add(food);
		  }
		  recipe.setFoods(foods);
		  dao.cadastrar(recipe);
	    redirect.addFlashAttribute("msg", "Receita cadastrada");
	  }catch(Exception e) {
	    return new ModelAndView("recipe/register").addObject("msg", e.getMessage());
	  }
	  return new ModelAndView("redirect:/recipe/register");
	}
	
	@GetMapping("list")
	public ModelAndView listar() {
		return new ModelAndView("recipe/list").addObject("recipes",dao.listar());
	}
}
