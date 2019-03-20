package com.donabate.staveley.alex.api.filters;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.donabate.staveley.alex.api.endpoints.JerseyApi;

@Provider
public class APIRequestFilter implements ContainerRequestFilter {
	
    private static final Logger LOG = LoggerFactory.getLogger(APIRequestFilter.class);
    
    @Override
	public void filter(ContainerRequestContext requestContext)
		     throws IOException {
        LOG.info(">>filter(), uriPath=" + requestContext.getUriInfo().getRequestUri());
        LOG.info(">>filter(), queryParams" + requestContext.getUriInfo().getQueryParameters());
		String correlationId = requestContext.getHeaderString("X-Correlation-ID");
		
		// ToDo add correlationId to log4j
	}

}
