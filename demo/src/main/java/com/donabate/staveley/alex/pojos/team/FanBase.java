package com.donabate.staveley.alex.pojos.team;

/**FanBase is an embeddable object for Teams.
 * It is not a Resource. */
public class FanBase {

	public String favouriteSong;
	public String favouriteBeer; 
	
	public FanBase(String favouriteSong, String favouriteBeer) {
		this.favouriteSong = favouriteSong;
		this.favouriteBeer = favouriteBeer;
	}

}
