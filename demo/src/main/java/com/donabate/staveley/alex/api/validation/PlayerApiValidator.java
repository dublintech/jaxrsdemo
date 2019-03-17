package com.donabate.staveley.alex.api.validation;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.donabate.staveley.alex.api.exceptions.APIException;
import com.donabate.staveley.alex.pojos.error.APIError;
import com.donabate.staveley.alex.pojos.error.ErrorResponse;
import com.donabate.staveley.alex.pojos.player.PlayerQuery;
import com.donabate.staveley.alex.pojos.team.CreateTeamCommand;

@Component("playerApiService")
public class PlayerApiValidator {

	public PlayerApiValidator() {
		// TODO Auto-generated constructor stub
	}
	
	public void validate(PlayerQuery playerQuery) throws APIException {
		System.out.println(">>validate(playerQuery=" + playerQuery);
		ErrorResponse errorResponse = new ErrorResponse();
		List<APIError> apiErrors = new ArrayList<>();
		
		if (playerQuery.getPageSize() != null &&
				playerQuery.getPageSize() > 0) {
			// pageStartIndex must have a value.
			if (playerQuery.getPageStartIndex() == null) {
				APIError apiError =  new APIError();
				apiError.setTitle("If pageSize is set, pageStartIndex must also be set");
				apiError.setCode("400");
				apiErrors.add(apiError);
			}
		}
		errorResponse.setAPIErrors(apiErrors);
		
		if (apiErrors.size() > 0) {
			throw new APIException(errorResponse, Response.Status.BAD_REQUEST);
		}
	}
	

}
