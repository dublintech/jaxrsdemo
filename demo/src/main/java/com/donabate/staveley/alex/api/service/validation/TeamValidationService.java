package com.donabate.staveley.alex.api.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.donabate.staveley.alex.api.exceptions.APIException;
import com.donabate.staveley.alex.api.pojos.APIError;
import com.donabate.staveley.alex.api.pojos.CreateTeamCommand;
import com.donabate.staveley.alex.api.pojos.ErrorResponse;
import com.donabate.staveley.alex.api.service.TeamService;

/**
 * Idea is that any part of the API is invalid then a 400 is returned.
 * A deeper validation problem should throw a 422.
 * 
 * @author astaveley
 */
@Component("teamValidationService")
public class TeamValidationService {
	
	public void validate(CreateTeamCommand createTeamCommand) throws APIException {
		System.out.println(">>validate(createTeamCommand.name=" + createTeamCommand.getName());
		ErrorResponse errorResponse = new ErrorResponse();
		List<APIError> apiErrors = new ArrayList<>();
		
		if (createTeamCommand.getName().equals("fail")) {
			APIError apiError =  new APIError();
			apiError.setTitle("Name cannot equal fail");
			apiError.setCode("400");
			apiErrors.add(apiError);
		}
		errorResponse.setAPIErrors(apiErrors);
		
		if (apiErrors.size() > 0) {
			throw new APIException(errorResponse, Response.Status.BAD_REQUEST);
		}
	}

}
