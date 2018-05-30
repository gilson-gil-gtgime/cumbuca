package com.cumbuca.web.dao.impl;

import org.springframework.stereotype.Repository;

import com.cumbuca.web.dao.UserDAO;
import com.cumbuca.web.entity.User;

@Repository
public class UserDAOImpl extends GenericDAOImpl<User, Integer> implements UserDAO{
	
}