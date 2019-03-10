package com.donabate.staveley.alex.api.exceptions;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.donabate.staveley.alex.api.pojos.APIError;
import com.donabate.staveley.alex.api.pojos.ErrorResponse;

public class APIException extends WebApplicationException  {
	
    public APIException(ErrorResponse errorResponse, Response.Status httpCode) {
        super(Response.status(httpCode)
            .entity(errorResponse).type(MediaType.APPLICATION_JSON_TYPE).build());
    }
}
