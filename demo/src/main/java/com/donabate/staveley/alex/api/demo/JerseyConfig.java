package com.donabate.staveley.alex.api.demo;

import com.donabate.staveley.alex.api.endpoints.TeamAPI;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.donabate.staveley.alex.api.service")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
    	System.out.println(">>JerseyConfig()");
        registerEndpoints();
    }

    private void registerEndpoints() {
    	System.out.println(">>registerEndpoints()");
        register(TeamAPI.class);
    }
}