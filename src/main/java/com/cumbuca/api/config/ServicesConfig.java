package com.cumbuca.api.config;

import org.glassfish.jersey.server.ResourceConfig;

public class ServicesConfig extends ResourceConfig {

	public ServicesConfig() {
		packages("com.cumbuca.api.service");
	}
	
}
