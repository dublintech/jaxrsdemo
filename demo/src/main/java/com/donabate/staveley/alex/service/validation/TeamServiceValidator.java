package com.donabate.staveley.alex.service.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.donabate.staveley.alex.api.endpoints.TeamApi;
import com.donabate.staveley.alex.pojos.team.CreateTeamCommand;
import static com.donabate.staveley.alex.service.validation.BusinessLogicException.BusinessErrorCodeEnum;

/**
 * This is the third error handling pattern.
 * 
 * At this point we are inside the service layer. We are doing database queries and more 
 * complex validation.
 * 
 * This type of error will end up returning a 422.
 *   
 * @author astaveley
 */
@Component("teamServiceValidator")
public class TeamServiceValidator {
    private static final Logger LOG = LoggerFactory.getLogger(TeamServiceValidator.class);
    
    // In real world command not be parameter here but an actual business entity
	public void validate(CreateTeamCommand createTeamCommand) {
	    LOG.info(">>TeamServiceValidator.validate(createTeamCommand=" + createTeamCommand + ")");
		if (createTeamCommand.getName().equals("422ex")) {
			throw new BusinessLogicException("You asked for B logic to be thrown", BusinessErrorCodeEnum.INTERNAL_PROCESSING_ERROR);
		} else {
		    LOG.info("not throwing ex, when name=" + createTeamCommand.getName());
		}
	}
}
