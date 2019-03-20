package com.donabate.staveley.alex.api.exceptions;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.donabate.staveley.alex.pojos.error.APIError;
import com.donabate.staveley.alex.pojos.error.ErrorResponse;
import com.donabate.staveley.alex.service.validation.BusinessLogicException;
import static com.donabate.staveley.alex.service.validation.BusinessLogicException.BusinessErrorCodeEnum;

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
        Integer errorCode = null;
        if (ble.getBusinessErrorCodeEnum().equals(BusinessErrorCodeEnum.ENTIT_NOT_IN_DB)) {
        	errorCode = 404;
        } else {
        	errorCode = 422;
        }
        apiError.setCode(errorCode.toString());
        apiErrors.add(apiError);
        errorResponse.setAPIErrors(apiErrors);
        
        throw new APIException(errorResponse, errorCode);
    }
}
