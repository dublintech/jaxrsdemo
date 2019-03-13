package com.donabate.staveley.alex.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.donabate.staveley.alex.pojos.player.Player;
import com.donabate.staveley.alex.pojos.player.PlayerQuery;


@Component("playerService")
public final class PlayerService {
	
	public List<Player> findAllPlayers(PlayerQuery playerQuery) {
		Player.Builder builderEuan = new Player.Builder();
		builderEuan.withName("Euan Staveley");
		
		Player.Builder builderOliver = new Player.Builder();
		builderOliver.withName("Oliver Staveley");
		
		List<Player> players = 
				Arrays.asList(builderOliver.build(), builderOliver.build());
		return players;
	}
	
	public Player findPlayer(String playerId) {	
		System.out.println(">>findPlayer(playerId=" + playerId + ")");
		Player.Builder builderPlayer = new Player.Builder();
		builderPlayer.withName("RandomPLayer");
		
		Player player =  builderPlayer.build(playerId);
		System.out.println("<<findPlayer(), return=" + player);
		return player;
	}

}
