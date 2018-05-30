package com.cumbuca.api.service;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import com.cumbuca.web.dao.FoodDAO;
import com.cumbuca.web.dao.RecipeDAO;
import com.cumbuca.web.dao.impl.FoodDAOImpl;
import com.cumbuca.web.dao.impl.RecipeDAOImpl;
import com.cumbuca.web.entity.Food;
import com.cumbuca.web.entity.Recipe;

@Path("/recipes")
@PermitAll
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RecipeService {

	private RecipeDAO dao;
	
	private FoodDAO foodDAO;
	
	public RecipeService() {
		dao = new RecipeDAOImpl();
		foodDAO = new FoodDAOImpl();
	}
	
	@GET
	@RolesAllowed("guest")
	@Path("{id}")
	public Recipe recipe(@PathParam("id") int id) {
		return dao.buscar(id);
	}
	
	@GET
	@RolesAllowed("user")
	public List<Recipe> recipes(@QueryParam("foodIds") List<Integer> foodIds) {
		if (foodIds == null || foodIds.isEmpty()) {
			return dao.listar();
		}
		return dao.recipes(foodIds);
	}

	@POST
	@RolesAllowed("user")
	public Response create(Map<String, Object> object) {
		try {
			String name = (String) object.get("name");
			@SuppressWarnings("unchecked")
			List<Integer> foodIds = (List<Integer>) object.get("foodIds");
			List<Food> foods = foodIds.stream().map(id -> foodDAO.buscar(id)).collect(Collectors.toList());
			Recipe recipe = new Recipe(name, foods);
			dao.cadastrar(recipe);
			URI uri = UriBuilder.fromPath("recipes/{id}").build(recipe.getId());
			return Response.created(uri).entity(recipe).build();
		}catch(Exception e) {
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DELETE
	@RolesAllowed("user")
	@Path("{id}")
	public void delete(@PathParam("id") int id) {
		try {
			dao.remover(id);
		}catch(Exception e) {
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}
	
}
