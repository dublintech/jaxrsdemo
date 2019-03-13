package com.donabate.staveley.alex.pojos.player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import com.donabate.staveley.alex.pojos.Extensible;
import com.donabate.staveley.alex.pojos.SelfReferencing;
import com.donabate.staveley.alex.pojos.Resource;
import com.donabate.staveley.alex.pojos.team.Team;
import com.donabate.staveley.alex.pojos.team.Team.Builder;
import com.donabate.staveley.alex.pojos.team.jersey.Jersey;

public class Player implements Resource, Extensible, SelfReferencing {
	@Override
	public String toString() {
		return "Player [name=" + name + ", id=" + id + ", self=" + self + "]";
	}

	private String name;
	private String id;
	private String self;
	
	public Map<String, Object> getExtensions() {
		return new HashMap<String, Object>();
	}
	
	private Player() {
		id = String.valueOf(ThreadLocalRandom.current().nextInt(0, 1000 + 1));
		self = "/players/" + id;
	}
	
	private Player(String playerId) {
		id = playerId;
		self = "/players/" + id;
	}
	
	public static class Builder  {
		private String name;
		
		public Builder withName(String name) {
			this.name = name;
			return this;
		}
		
		public Player build() {
			Player player =  new Player();
			player.name = this.name;
			return player;
		}
		
		public Player build(String playerId) {
			Player player =  new Player(playerId);
			player.name = this.name;
			return player;
		}
	}

	@Override
	public String getSelf() {
		return self;
	}

	@Override
	public String getResource() {
		return "player";
	}

	@Override
	public String getId() {
		return id;
	}
}
