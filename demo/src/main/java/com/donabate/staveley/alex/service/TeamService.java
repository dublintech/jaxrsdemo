package com.donabate.staveley.alex.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.donabate.staveley.alex.api.validation.TeamApiValidator;
import com.donabate.staveley.alex.pojos.command.DeleteCommand;
import com.donabate.staveley.alex.pojos.command.LinkCommand;
import com.donabate.staveley.alex.pojos.command.UnlinkCommand;
import com.donabate.staveley.alex.pojos.player.Player;
import com.donabate.staveley.alex.pojos.team.CreateTeamCommand;
import com.donabate.staveley.alex.pojos.team.EditTeamCommand;
import com.donabate.staveley.alex.pojos.team.FanBase;
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
    
    @Autowired
    private PlayerService playerService;
	
	public List<Team> findAllTeams() {		
		List<Team> teams = 
				Arrays.asList(makeLiverpool("222"), makeDublin("227"));
		return teams;
	}
	
	public Team findTeam(String teamId) {	
		System.out.println(">>findTeam(teamId=" + teamId + ")");
		return makeLiverpool(teamId);
	}
	
	// utility method, to make Liverpool.
	private Team makeLiverpool(String teamId) {
		Team.Builder builderLiverpool = new Team.Builder();
		builderLiverpool.withName("Liverpool");
		Jersey.Builder builderJersey = new Jersey.Builder();
		builderJersey.withColour("Red");
		builderJersey.withType("Home");
		builderLiverpool.withJerseys(Arrays.asList(builderJersey.build("100")));
		builderLiverpool.withFanBase(new FanBase("YNWA", "Carlsberg"));
		
		Player.Builder builderPlayer = new Player.Builder();
		builderPlayer.withName("Van Dijk");
		
		builderLiverpool.withPlayers(Arrays.asList(builderPlayer.build("3342")));
		
		return builderLiverpool.build(teamId);
	}
	
	// utility method, to make Liverpool.
	private Team makeDublin(String teamId) {
		Team.Builder builderTeam = new Team.Builder();
		builderTeam.withName("Dublin");
		Jersey.Builder builderJersey = new Jersey.Builder();
		builderJersey.withColour("Blue");
		builderJersey.withType("Home");
		builderTeam.withJerseys(Arrays.asList(builderJersey.build("177")));
		builderTeam.withFanBase(new FanBase("COYBIB", "Guinness"));
		
		Player.Builder builderPlayer = new Player.Builder();
		builderPlayer.withName("Jonny Cooper");
		
		builderTeam.withPlayers(Arrays.asList(builderPlayer.build("3377")));
		
		return builderTeam.build(teamId);
	}
	
	// utility method, to make Liverpool.
	private Team makeTeam(String teamId, String name) {
		Team.Builder builderTeam = new Team.Builder();
		builderTeam.withName(name);
		Jersey.Builder builderJersey = new Jersey.Builder();
		builderJersey.withColour("Black");
		builderJersey.withType("Home");
		builderTeam.withJerseys(Arrays.asList(builderJersey.build("777")));
		builderTeam.withFanBase(new FanBase("Generic", "N/A"));
		
		Player.Builder builderPlayer = new Player.Builder();
		builderPlayer.withName("Jonny Magoo");
		
		builderTeam.withPlayers(Arrays.asList(builderPlayer.build("000")));
		
		return builderTeam.build(teamId);
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
		return makeTeam(id, editTeamCommand.getName());
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

	public Team addPlayerToTeam(String id, @Valid LinkCommand linkCommand) {
		// Get the Team
		Team originalTeam = this.findTeam(id);
		
		// Now get the players to add. 
		Player player = playerService.getPlayer(linkCommand.getId());
		
		Team.Builder builder = new Team.Builder();
		builder.withName(originalTeam.getName());
		builder.withFanBase(originalTeam.getFanBase());
		
		List<Player> newPlayers =  new ArrayList<>();
		newPlayers.addAll(originalTeam.getPlayers());
		newPlayers.add(player);
		builder.withPlayers(newPlayers);
		
		Team team = builder.build(id);
		
		return team;
	}
	
	public Team removePlayerFromTeam(String id, @Valid UnlinkCommand unlinkCommand) {
		// Get the Team
		Team originalTeam = this.findTeam(id);
		
		// Now get the players to add. 
		Player player = playerService.getPlayer(unlinkCommand.getId());
		
		Team.Builder builder = new Team.Builder();
		builder.withName(originalTeam.getName());
		builder.withFanBase(originalTeam.getFanBase());
		
		// For now, just clear it out, it's only really to show Sample APIs.
		List<Player> newPlayers = new ArrayList<>();
		builder.withPlayers(newPlayers);
		
		Team team = builder.build(id);
		
		return team;
	}
	
	
	

}
