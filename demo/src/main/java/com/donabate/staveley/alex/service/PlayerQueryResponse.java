package com.donabate.staveley.alex.service;

import java.util.List;

import com.donabate.staveley.alex.pojos.player.Player;

/** 
 * Represents a response from service layer to player query.
 * 
 */
public class PlayerQueryResponse {

	List<Player> players;
	// size of all players - used in paginated queries.
	Integer totalSize;
	
	public PlayerQueryResponse(List<Player> players, Integer totalSize) {
		// TODO Auto-generated constructor stub
		this.players = players;
		this.totalSize = totalSize;
		
	}

	public List<Player> getPlayers() {
		return players;
	}

	public Integer getTotalSize() {
		return totalSize;
	}


}
