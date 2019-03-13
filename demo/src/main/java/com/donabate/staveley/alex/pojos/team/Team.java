package com.donabate.staveley.alex.pojos.team;

import java.util.concurrent.ThreadLocalRandom;

import javax.validation.constraints.NotNull;

import com.donabate.staveley.alex.pojos.Extensible;
import com.donabate.staveley.alex.pojos.SelfReferencing;
import com.donabate.staveley.alex.pojos.Resource;
import com.donabate.staveley.alex.pojos.player.Player;
import com.donabate.staveley.alex.pojos.team.jersey.Jersey;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class Team implements Resource, Extensible, SelfReferencing {

	@Override
	public String toString() {
		return "Team [name=" + name + ", id=" + id + ", self=" + self + "]";
	}

	private String name;
	private String id;
	private String self;
	private List<Player> players;
	private FanBase fanBase;
	private List<Jersey> jerseys;
	
	public String getName() {
		return name;
	}
	
	
	public String getResource() {
		return "team";
	}
	
	public String getId() {
		return id;
	}
	
	public String getSelf() {
		return self;
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
		self = "/teams/" + id;
	}
	
	private Team(String teamId) {
		id = teamId;
		self = "/teams/" + id;
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
			return team;
		}
		
		public Team build(String teamId) {
			Team team =  new Team(teamId);
			team.name = this.name;
			team.players = this.players;
			team.fanBase = this.fanBase;
			team.jerseys = this.jerseys;
			return team;
		}
	}
	
	
}
