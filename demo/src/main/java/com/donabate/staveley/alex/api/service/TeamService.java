package com.donabate.staveley.alex.api.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.donabate.staveley.alex.api.pojos.CreateTeamCommand;
import com.donabate.staveley.alex.api.pojos.Team;
import com.donabate.staveley.alex.api.pojos.TeamQuery;

@Component("teamService")
public class TeamService {
	
	public List<Team> findAll() {
		Team.Builder builderLiverpool = new Team.Builder();
		builderLiverpool.withName("Liverpool");
		
		Team.Builder builderDublin = new Team.Builder();
		builderDublin.withName("Dublin");
		
		List<Team> teams = 
				Arrays.asList(builderLiverpool.build(), builderDublin.build());
		return teams;
	}
	
	public Team find(String id) {		
		Team.Builder builderDublin = new Team.Builder();
		builderDublin.withName("Dublin");
		
		return builderDublin.build();
	}
	
	public Team findByName(String name) {
		Team.Builder builder = new Team.Builder();
		builder.withName(name);
		
		return builder.build();
	}
	
	public Team createTeam(CreateTeamCommand createTeamCommand) {
		Team.Builder builder = new Team.Builder();
		builder.withName(createTeamCommand.getName());
		
		return builder.build();
	}

}
