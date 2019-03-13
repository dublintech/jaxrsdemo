package com.donabate.staveley.alex.pojos.team.jersey;

import java.util.concurrent.ThreadLocalRandom;

import com.donabate.staveley.alex.pojos.SelfReferencing;
import com.donabate.staveley.alex.pojos.Resource;
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
public class Jersey implements Resource, SelfReferencing {

	private String colour;
	private String type;
	private String id;
	private String self;
	
	private Jersey (String teamId) {
		id = String.valueOf(ThreadLocalRandom.current().nextInt(0, 1000 + 1));
		self = "/teams/" + teamId + "/jerseys/" + id;
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

			return jersey;
		}
	}

	@Override
	public String getSelf() {
		// TODO Auto-generated method stub
		return self;
	}
}
