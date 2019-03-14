package com.donabate.staveley.alex.pojos.team;

import java.util.concurrent.ThreadLocalRandom;

import javax.validation.constraints.NotNull;

import com.donabate.staveley.alex.pojos.player.Player;
import com.donabate.staveley.alex.pojos.resource.Extensible;
import com.donabate.staveley.alex.pojos.resource.LinkHolder;
import com.donabate.staveley.alex.pojos.resource.PojoService;
import com.donabate.staveley.alex.pojos.resource.Resource;
import com.donabate.staveley.alex.pojos.team.jersey.Jersey;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

public class Team implements Resource, Extensible, LinkHolder {

	@Override
	public String toString() {
		return "Team [name=" + name + ", id=" + id + ", links=" + links + "]";
	}

	private String name;
	private String id;
	private List<Player> players;
	private FanBase fanBase;
	private List<Jersey> jerseys;
	
	private Map<String, URL> links;

	public String getName() {
		return name;
	}
	
	public String getResource() {
		return "team";
	}
	
	public String getId() {
		return id;
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	
	public List<Jersey> getJersey() {
		return jerseys;
	}
	
	public FanBase getFanBase() {
		return this.fanBase;
	}
	
	public Map<String, Object> getExtensions() {
		return new HashMap<String, Object>();
	}
	
	private Team() {
		id = String.valueOf(ThreadLocalRandom.current().nextInt(0, 1000 + 1));
	}
	
	private Team(String teamId) {
		id = teamId;
	}
	
	public static class Builder  {
		private String name;
		private List<Player> players;
		private FanBase fanBase;
		private List<Jersey> jerseys;
		
		public Builder withName(String name) {
			this.name = name;
			return this;
		}
		
		public Builder withPlayers(List<Player> players) {
			this.players = players;
			return this;
		}
		
		public Builder withJerseys(List<Jersey> jerseys) {
			this.jerseys = jerseys;
			return this;
		}
		
		public Builder withFanBase(FanBase fanBase) {
			this.fanBase = fanBase;
			return this;
		}
		
		public Team build() {
			Team team =  new Team();
			team.name = this.name;
			team.players = this.players;
			team.fanBase = this.fanBase;
			team.jerseys = this.jerseys;
			
			URL teamSelfLink = PojoService.createUrl("/teams/" + team.getId());
			Map<String, URL> links =  new HashMap<>();
			links.put(SELF, teamSelfLink);
			team.links = links;
					
			return team;
		}
		
		
		public Team build(String teamId) {
			Team team =  new Team(teamId);
			team.name = this.name;
			team.players = this.players;
			team.fanBase = this.fanBase;
			team.jerseys = this.jerseys;
			
			URL teamSelfLink = PojoService.createUrl("/teams/" + team.getId());
			Map<String, URL> links =  new HashMap<>();
			links.put(SELF, teamSelfLink);
			team.links = links;
			
			return team;
		}
	}

	@Override
	public Map<String, URL> getLinks() {
		// TODO Auto-generated method stub
		return links;
	}
	
	
}
