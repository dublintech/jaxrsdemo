package com.donabate.staveley.alex.pojos.team.jersey;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import com.donabate.staveley.alex.pojos.resource.LinkHolder;
import com.donabate.staveley.alex.pojos.resource.PojoService;
import com.donabate.staveley.alex.pojos.resource.Resource;
import com.donabate.staveley.alex.pojos.team.Team;
import com.donabate.staveley.alex.pojos.team.Team.Builder;

/**
 * A jersey always belongs to a team.
 * A team can have many different jersey.  One for home, one for away.
 * etc. etc.
 * 
 * This resource is not extensible
 * 
 * @author astaveley
 */
public class Jersey implements Resource, LinkHolder {

	private String colour;
	private String type;
	private String id;
	
	private Map<String, URL> links;
	
	private Jersey (String teamId) {
		id = String.valueOf(ThreadLocalRandom.current().nextInt(0, 1000 + 1));
	}
	
	@Override
	public String getResource() {
		// TODO Auto-generated method stub
		return "jersey";
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public String getColour() {
		return colour;
	}

	public String getType() {
		return type;
	}
	
	@Override
	public Map<String, URL> getLinks() {
		// TODO Auto-generated method stub
		return links;
	}
	
	public static class Builder  {
		private String type;
		private String colour;
		
		
		public Builder withColour(String colour) {
			this.colour = colour;
			return this;
		}
		
		public Builder withType(String type) {
			this.type = type;
			return this;
		}
		
		public Jersey build(String teamId) {
			Jersey jersey =  new Jersey(teamId);
			jersey.type = this.type;
			jersey.colour = this.colour;
			
			URL playerSelfLink = PojoService.createUrl("/teams/" + teamId + 
					"/jerseys/" + jersey.getId());
			Map<String, URL> links =  new HashMap<>();
			links.put(SELF, playerSelfLink);
			jersey.links = links;
			
			return jersey;
		}
	}

	
}
