package com.donabate.staveley.alex.pojos.team;

import java.util.concurrent.ThreadLocalRandom;

import javax.validation.constraints.NotNull;

import com.donabate.staveley.alex.pojos.Extensible;
import com.donabate.staveley.alex.pojos.Location;
import com.donabate.staveley.alex.pojos.Resource;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;
import java.util.HashMap;

public class Team implements Resource, Extensible, Location {

	@Override
	public String toString() {
		return "Team [name=" + name + ", id=" + id + ", location=" + location + "]";
	}

	private String name;
	private String id;
	private String location;
	
	public String getName() {
		return name;
	}
	
	
	public String getResource() {
		return "team";
	}
	
	public String getId() {
		return id;
	}
	
	public String getLocation() {
		return location;
	}
	
	public Map<String, Object> getExtensions() {
		return new HashMap<String, Object>();
	}
	
	private Team() {
		id = String.valueOf(ThreadLocalRandom.current().nextInt(0, 1000 + 1));
		location = "/teams/" + id;
	}
	
	private Team(String teamId) {
		id = teamId;
		location = "/teams/" + id;
	}
	
	public static class Builder  {
		private String name;
		
		public Builder withName(String name) {
			this.name = name;
			return this;
		}
		
		public Team build() {
			Team team =  new Team();
			team.name = this.name;
			return team;
		}
		
		public Team build(String teamId) {
			Team team =  new Team(teamId);
			team.name = this.name;
			return team;
		}
	}
	
	
}
