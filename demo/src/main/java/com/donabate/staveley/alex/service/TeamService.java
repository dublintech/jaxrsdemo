package com.donabate.staveley.alex.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.donabate.staveley.alex.api.validation.TeamApiValidator;
import com.donabate.staveley.alex.pojos.DeleteCommand;
import com.donabate.staveley.alex.pojos.team.CreateTeamCommand;
import com.donabate.staveley.alex.pojos.team.EditTeamCommand;
import com.donabate.staveley.alex.pojos.team.Team;
import com.donabate.staveley.alex.pojos.team.TeamQuery;
import com.donabate.staveley.alex.pojos.team.jersey.CreateJerseyCommand;
import com.donabate.staveley.alex.pojos.team.jersey.Jersey;
import com.donabate.staveley.alex.service.validation.BusinessLogicException;
import com.donabate.staveley.alex.service.validation.TeamServiceValidator;

/**
 * Because Jerseys can only exists within a Team, some jersey methods are included here.
 * 
 * @author astaveley
 */
@Component("teamService")
public class TeamService {
	
    @Autowired
    private TeamServiceValidator teamServiceValidator;
	
	public List<Team> findAllTeams() {
		Team.Builder builderLiverpool = new Team.Builder();
		builderLiverpool.withName("Liverpool");
		
		Team.Builder builderDublin = new Team.Builder();
		builderDublin.withName("Dublin");
		
		List<Team> teams = 
				Arrays.asList(builderLiverpool.build(), builderDublin.build());
		return teams;
	}
	
	public Team findTeam(String teamId) {	
		System.out.println(">>findTeam(teamId=" + teamId + ")");
		Team.Builder builderDublin = new Team.Builder();
		builderDublin.withName("Dublin");
		
		return builderDublin.build(teamId);
	}
	
	public List<Team> findTeams(TeamQuery teamQuery) {
		// 
		String name = teamQuery.getName();
		List<Team> teams = (name == null) ? this.findAllTeams() : findTeamByName(name);
		return teams;
	}
	
	public List<Team> findTeamByName(String name) {
		Team.Builder builder = new Team.Builder();
		builder.withName(name);
		
		return Arrays.asList(builder.build());
	}
	
	public Team createTeam(CreateTeamCommand createTeamCommand) throws BusinessLogicException {
		teamServiceValidator.validate(createTeamCommand);
		Team.Builder builder = new Team.Builder();
		builder.withName(createTeamCommand.getName());
		
		return builder.build();
	}
	
	public Team editTeam(String id, EditTeamCommand editTeamCommand) {
		// For now...
		// make another just so we have a response
		Team.Builder builder = new Team.Builder();
		builder.withName(editTeamCommand.getName());
		return builder.build(id);
	}
	
	public void deleteTeam(DeleteCommand deleteCommand) {
		System.out.println(">>deleteTeamCommand(deleteCommand=" + deleteCommand + ")");
	}
	
	public void deleteTeam(String id) {
		System.out.println(">>deleteTeamCommand(id=" + id + ")");
	}
	
	public Jersey createJersey(String teamId, CreateJerseyCommand createJerseyCommand) {
		Jersey.Builder builder = new Jersey.Builder();
		builder.withType(createJerseyCommand.getType());
		builder.withColour(createJerseyCommand.getColour());
		return builder.build(teamId);
	}
	
	public void deleteJersey(DeleteCommand deleteCommand) {
		System.out.println(">>deleteJersey(deleteCommand=" + deleteCommand + ")");
	}
	
	
	public void deleteJersey(String id) {
		System.out.println(">>deleteJersey(id=" + id + ")");
	}
	
	public Jersey findJersey(String teamId, String jerseyId) {
		Jersey.Builder jerseyDublin = new Jersey.Builder();
		jerseyDublin.withType("Home");
		jerseyDublin.withColour("Blue");
		
		return jerseyDublin.build(teamId);
	}
	
	

}
