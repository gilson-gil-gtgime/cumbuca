package com.cumbuca.api.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import com.cumbuca.api.service.UserService;

public class ServicesConfig extends ResourceConfig {

	public ServicesConfig() {
		super(UserService.class);
		packages("com.cumbuca.api.service");
        register(RolesAllowedDynamicFeature.class);
	}
	
}