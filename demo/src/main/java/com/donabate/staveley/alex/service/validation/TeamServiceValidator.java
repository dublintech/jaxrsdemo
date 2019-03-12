package com.donabate.staveley.alex.service.validation;

import org.springframework.stereotype.Component;

import com.donabate.staveley.alex.pojos.team.CreateTeamCommand;

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
	// In real world command not be parameter here but an actual business entity
	public void validate(CreateTeamCommand createTeamCommand) {
		System.out.println(">>TeamServiceValidator.validate(createTeamCommand=" + createTeamCommand + ")");
		if (createTeamCommand.getName().equals("422ex")) {
			throw new BusinessLogicException("You asked for B logic to be thrown");
		} else {
			System.out.println("not throwing ex, when name=" + createTeamCommand.getName());
		}
	}
}
