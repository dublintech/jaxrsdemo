package com.donabate.staveley.alex.api.exceptions;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.donabate.staveley.alex.pojos.APIError;
import com.donabate.staveley.alex.pojos.ErrorResponse;
import com.donabate.staveley.alex.service.validation.BusinessLogicException;

public class APIException extends WebApplicationException  {
	
    public APIException(ErrorResponse errorResponse, Response.Status httpCode) {
        super(Response.status(httpCode)
            .entity(errorResponse).type(MediaType.APPLICATION_JSON_TYPE).build());
    }
    
    public APIException(ErrorResponse errorResponse, int httpCode) {
        super(Response.status(httpCode)
            .entity(errorResponse).type(MediaType.APPLICATION_JSON_TYPE).build());
    }
    
    public static void throwApiException(BusinessLogicException ble) throws APIException {
    	String message = ble.getMessage();
    	
		ErrorResponse errorResponse = new ErrorResponse();
		List<APIError> apiErrors = new ArrayList<>();
		APIError apiError =  new APIError();
		apiError.setTitle(message);
		apiError.setCode("422");
		apiErrors.add(apiError);
		errorResponse.setAPIErrors(apiErrors);
		
		throw new APIException(errorResponse, 422);
    }
}
