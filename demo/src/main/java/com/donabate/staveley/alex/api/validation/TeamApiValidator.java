package com.donabate.staveley.alex.api.validation;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.donabate.staveley.alex.api.endpoints.JerseyApi;
import com.donabate.staveley.alex.api.exceptions.APIException;
import com.donabate.staveley.alex.pojos.command.LinkCommand;
import com.donabate.staveley.alex.pojos.command.UnlinkCommand;
import com.donabate.staveley.alex.pojos.error.APIError;
import com.donabate.staveley.alex.pojos.error.ErrorResponse;
import com.donabate.staveley.alex.pojos.player.PlayerQuery;
import com.donabate.staveley.alex.pojos.team.CreateTeamCommand;
import com.donabate.staveley.alex.service.TeamService;

/**
 * This is the second error handling pattern.
 * <UL>
 * <LI> The first deals with validation annotations on the command.
 * <LI> The second (this) contains some java functions applied on the the command
 * <LI> The third is deeper business logic.
 * </UL>
 * 
 * Idea is that any part of the API is invalid then a 400 is returned.
 * A deeper validation problem should throw a 422.
 * 
 * @author astaveley
 */
@Component("teamValidationService")
public class TeamApiValidator {
	
    private static final Logger LOG = 
            LoggerFactory.getLogger(TeamApiValidator.class);
    
	public void validate(CreateTeamCommand createTeamCommand) throws APIException {
	    LOG.info(">>validate(createTeamCommand.name=" + createTeamCommand.getName());
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
	

	public void validate(LinkCommand linkCommand) throws APIException {
	    LOG.info(">>validate(linkCommand=" + linkCommand);
		ErrorResponse errorResponse = new ErrorResponse();
		List<APIError> apiErrors = new ArrayList<>();
		
		if (!linkCommand.getRelName().equals("players")) {
			APIError apiError =  new APIError();
			apiError.setTitle("Can only change the player rel.");
			apiError.setCode("400");
			apiErrors.add(apiError);
		}
		errorResponse.setAPIErrors(apiErrors);
		
		if (apiErrors.size() > 0) {
			throw new APIException(errorResponse, Response.Status.BAD_REQUEST);
		}
	}
	
	public void validate(UnlinkCommand unlinkCommand) throws APIException {
	    LOG.info(">>validate(linkCommand=" + unlinkCommand);
		ErrorResponse errorResponse = new ErrorResponse();
		List<APIError> apiErrors = new ArrayList<>();
		
		if (!unlinkCommand.getRelName().equals("players")) {
			APIError apiError =  new APIError();
			apiError.setTitle("Can only change the player rel.");
			apiError.setCode("400");
			apiErrors.add(apiError);
		}
		errorResponse.setAPIErrors(apiErrors);
		
		if (apiErrors.size() > 0) {
			throw new APIException(errorResponse, Response.Status.BAD_REQUEST);
		}
	}

}
