package com.cumbuca.api.service;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.cumbuca.web.dao.FoodRecordDAO;
import com.cumbuca.web.dao.UserDAO;
import com.cumbuca.web.dao.impl.FoodDAOImpl;
import com.cumbuca.web.dao.impl.FoodRecordDAOImpl;
import com.cumbuca.web.dao.impl.UserDAOImpl;
import com.cumbuca.web.entity.Food;
import com.cumbuca.web.entity.FoodRecord;
import com.cumbuca.web.entity.FoodRecordIdentifier;
import com.cumbuca.web.entity.User;

@Path("/users")
@PermitAll
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserService {
	
	private UserDAO dao;
	
	private FoodRecordDAO foodRecordDAO;
	
	private FoodDAO foodDAO;
	
	private SimpleDateFormat df;
	
	public UserService() {
		dao = new UserDAOImpl();
		foodRecordDAO = new FoodRecordDAOImpl();
		foodDAO = new FoodDAOImpl();
		df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setLenient(false);
	}

	@GET
	@RolesAllowed("guest")
	public List<User> fetch() {
		return dao.listar();
	}
	
	@GET
	@Path("{id}")
	@RolesAllowed("guest")
	public User user(@PathParam("id") int id) {
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
	public User update(@PathParam("id") int id, User user) {
		try {
			user.setId(id);
			dao.atualizar(user);
			return user;
		}catch(Exception e) {
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@POST
	@RolesAllowed("user")
	public Response register(User user) {
		try {
			dao.cadastrar(user);
			URI uri = UriBuilder.fromPath("users/{id}").build(user.getId());
			return Response.created(uri).entity(user).build();
		}catch(Exception e) {
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}
	
//	Foods
	@GET
	@Path("{id}/foods")
	@RolesAllowed("user")
	public List<FoodRecord> foods(@PathParam("id") int id) {
		return foodRecordDAO.getFoodRecordsWithUserId(id);
	}
	
	@POST
	@Path("{id}/foods")
	@RolesAllowed("user")
	public Response insertFoodRecord(@PathParam("id") int id, Map<String, Object> object) {
		try {
			int foodId = (int) object.get("id_food");
			int total = (int) object.get("total");
			String unit = (String) object.get("unit");
			String expireString = (String) object.get("expire");
			Date expire = this.df.parse(expireString);
			System.out.println(expire);
			Food food = foodDAO.buscar(foodId);
			User user = dao.buscar(id);
			FoodRecord foodRecord = new FoodRecord(user, food, total, unit, expire);
			foodRecordDAO.cadastrar(foodRecord);
			URI uri = UriBuilder.fromPath("users/{id}/foods").build(foodRecord.getUser().getId());
			return Response.created(uri).entity(foodRecord).build();
		}catch(Exception e) {
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PUT
	@Path("{id}/foods")
	@RolesAllowed("user")
	public FoodRecord update(@PathParam("id") int id, Map<String, Object> object) {
		try {
			int foodId = (int) object.get("id_food");
			int total = (int) object.get("total");
			String unit = (String) object.get("unit");
			String expireString = (String) object.get("expire");
			Date expire = this.df.parse(expireString);
			System.out.println(expire);
			Food food = foodDAO.buscar(foodId);
			User user = dao.buscar(id);
			FoodRecord foodRecord = new FoodRecord(user, food, total, unit, expire);
			FoodRecordIdentifier foodRecordIdentifier = new FoodRecordIdentifier(id, foodId);
			foodRecord.setId(foodRecordIdentifier);
			foodRecordDAO.atualizar(foodRecord);
			return foodRecord;
		}catch(Exception e) {
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DELETE
	@Path("{id}/foods/{foodId}")
	@RolesAllowed("user")
	public void delete(@PathParam("id") int id, @PathParam("foodId") int foodId) {
		try {
			FoodRecordIdentifier foodRecordIdentifier = new FoodRecordIdentifier(id, foodId);
			foodRecordDAO.remover(foodRecordIdentifier);
		}catch(Exception e) {
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}
	
}
