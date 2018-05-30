package com.cumbuca.api.service;

import java.net.URI;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import com.cumbuca.web.dao.FoodDAO;
import com.cumbuca.web.dao.impl.FoodDAOImpl;
import com.cumbuca.web.entity.Food;

@Path("/foods")
@PermitAll
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FoodService {
	
	private FoodDAO dao;
	
	public FoodService() {
		dao = new FoodDAOImpl();
	}

	@GET
	@RolesAllowed("guest")
	public List<Food> fetch() {
		return dao.listar();
	}
	
	@GET
	@Path("{id}")
	@RolesAllowed("guest")
	public Food food(@PathParam("id") int id) {
		return dao.buscar(id);
	}
	
	@DELETE
	@Path("{id}")
	@RolesAllowed("user")
	public void delete(@PathParam("id") int id) {
		try {
			dao.remover(id);
		}catch(Exception e) {
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PUT
	@Path("{id}")
	@RolesAllowed("user")
	public Food update(@PathParam("id") int id, Food food) {
		try {
			food.setId(id);
			dao.atualizar(food);
			return food;
		}catch(Exception e) {
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@POST
	@RolesAllowed("user")
	public Response register(Food food) {
		try {
			dao.cadastrar(food);
			URI uri = UriBuilder.fromPath("foods/{id}").build(food.getId());
			return Response.created(uri).entity(food).build();
		}catch(Exception e) {
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}
}
