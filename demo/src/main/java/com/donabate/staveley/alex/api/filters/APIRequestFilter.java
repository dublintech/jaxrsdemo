package com.donabate.staveley.alex.api.filters;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class APIRequestFilter implements ContainerRequestFilter {
	
    @Override
	public void filter(ContainerRequestContext requestContext)
		     throws IOException {
		System.out.println(">>filter(), uriPath=" + requestContext.getUriInfo().getRequestUri());
		System.out.println(">>filter(), queryParams" + requestContext.getUriInfo().getQueryParameters());
		String correlationId = requestContext.getHeaderString("X-Correlation-ID");
		
		// ToDo add correlationId to log4j
	}

}
