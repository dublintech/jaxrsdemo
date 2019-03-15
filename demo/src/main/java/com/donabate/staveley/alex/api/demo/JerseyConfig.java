package com.donabate.staveley.alex.api.demo;

import com.donabate.staveley.alex.api.endpoints.TeamApi;
import com.donabate.staveley.alex.api.endpoints.JerseyApi;
import com.donabate.staveley.alex.api.endpoints.PlayerApi;
import com.donabate.staveley.alex.api.exceptions.ApiConstraintExceptionHandler;
import com.donabate.staveley.alex.api.filters.APIRequestFilter;
import com.donabate.staveley.alex.api.filters.APIResponseFilter;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.donabate.staveley.alex")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
    	System.out.println(">>JerseyConfig()");
        registerEndpoints();
        registerExceptionHandler();
        registerFilters();
    }

    private void registerEndpoints() {
    	System.out.println(">>registerEndpoints()");
        register(TeamApi.class);
        register(JerseyApi.class);
        register(PlayerApi.class);
    }
    
    private void registerExceptionHandler() {
    	register(ApiConstraintExceptionHandler.class);
    }
    
    private void registerFilters() {
    	register(APIResponseFilter.class);
    	register(APIRequestFilter.class);
    }
}