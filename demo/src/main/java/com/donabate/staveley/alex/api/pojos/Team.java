package com.donabate.staveley.alex.api.pojos;

import java.util.concurrent.ThreadLocalRandom;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;
import java.util.HashMap;

@JsonIgnoreProperties(value = { "location" })
public class Team implements Resource, Extensible {
	private String name;
	private String id;
	private String location;
	
	public String getName() {
		return name;
	}
	
	public Team(String name) {
		this.name = name;
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
	}
	
}
