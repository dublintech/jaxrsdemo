package com.donabate.staveley.alex.api.exceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.donabate.staveley.alex.pojos.APIError;
import com.donabate.staveley.alex.pojos.ErrorResponse;

import javax.validation.ConstraintViolationException;

/**
 * ApiConstraintExceptionHandler will generically handle violations on command
 * objects that break annotated such as not null
 * 
 * @author astaveley
 */
@Provider
public class ApiConstraintExceptionHandler implements ExceptionMapper<ConstraintViolationException> {
	 
    @Override
    public Response toResponse(ConstraintViolationException exception) {
    	System.out.println(">>toResponse=" + exception);
 
    	ErrorResponse errorResponse = new ErrorResponse();
    	String title = 
    			exception instanceof ConstraintViolationException ? 
    					prepareMessage((ConstraintViolationException)exception) :
    					"Unknown problem";
    	
    	APIError apiError =  new APIError();
    	apiError.setTitle(title);
    	apiError.setCode(Response.Status.BAD_REQUEST.toString());

    	List<APIError> apiErrors = Arrays.asList(apiError);
    	
    	errorResponse.setAPIErrors(apiErrors);
    	
        return Response.status(Response.Status.BAD_REQUEST)
            .entity(errorResponse)
            .type(MediaType.APPLICATION_JSON_TYPE)
            .build();
    }
    

 
    private String prepareMessage(ConstraintViolationException exception) {
        StringBuilder message = new StringBuilder();

    	for (ConstraintViolation<?> cv : exception.getConstraintViolations()) {
        	message.append(cv.getPropertyPath() + " " + cv.getMessage() + "\n");
    	}
        
        return message.toString();
    }
}