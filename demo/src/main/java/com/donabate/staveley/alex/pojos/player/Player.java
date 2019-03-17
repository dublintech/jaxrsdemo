package com.donabate.staveley.alex.pojos.player;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import com.donabate.staveley.alex.pojos.resource.Extensible;
import com.donabate.staveley.alex.pojos.resource.LinkHolder;
import com.donabate.staveley.alex.pojos.resource.PojoService;
import com.donabate.staveley.alex.pojos.resource.Resource;
import com.donabate.staveley.alex.pojos.team.Team;
import com.donabate.staveley.alex.pojos.team.Team.Builder;
import com.donabate.staveley.alex.pojos.team.jersey.Jersey;

public class Player implements Resource, Extensible, LinkHolder {
	@Override
	public String toString() {
		return "Player [name=" + name + ", id=" + id + ",age=" + age + ", links=" + links + "]";
	}

	private String name;
	public String getName() {
		return name;
	}

	private String id;
	private int age;
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	private Map<String, URL> links;
	
	public Map<String, Object> getExtensions() {
		return new HashMap<String, Object>();
	}
	
	private Player() {
		id = String.valueOf(ThreadLocalRandom.current().nextInt(0, 1000 + 1));
	}
	
	private Player(String playerId) {
		id = playerId;
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
			
			URL playerSelfLink = PojoService.createUrl("/players/" + player.getId());
			Map<String, URL> links =  new HashMap<>();
			links.put(SELF, playerSelfLink);
			player.links = links;
			return player;
		}
		
		public Player build(String playerId) {
			Player player =  new Player(playerId);
			player.name = this.name;
			
			URL playerSelfLink = PojoService.createUrl("/players/" + player.getId());
			Map<String, URL> links =  new HashMap<>();
			links.put(SELF, playerSelfLink);
			player.links = links;
			return player;
		}
	}

	@Override
	public String getResource() {
		return "player";
	}

	@Override
	public String getId() {
		return id;
	}
	
	@Override
	public Map<String, URL> getLinks() {
		// TODO Auto-generated method stub
		return links;
	}
}
