package com.donabate.staveley.alex.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;

import com.donabate.staveley.alex.pojos.player.Player;
import com.donabate.staveley.alex.pojos.player.PlayerQuery;
import com.donabate.staveley.alex.service.validation.BusinessLogicException;
import com.donabate.staveley.alex.pojos.command.EditCommand;


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
	
	public Player editPlayer(String playerId, EditCommand editCommand) {
		System.out.println(">>editPlayer(playerId=" + playerId + ",editCommand=" + editCommand);
		// In reality,
		// (1) the service method would go off and get a Business Entity 
		// or External represenation of the Player.
		// (2) That would be then be changed
		// (3) The POJO would be converted
		
		// Because this is just a sample project we skip (1).
		// And use a Player POJO and change that. 
		// There's lots of ways that can be done.
		// This way just uses an apache library BeanUtil.
		
		//  Just do it the Java 7 way to keep it simple. 
		Player player = this.findPlayer(playerId);
		for (Map.Entry<String,Object> entry : editCommand.entrySet())  {
			String key = entry.getKey();
			Object value =  entry.getValue();
			System.out.println("Key=" + key + ",value=" + value);
			try {
				BeanUtils.setProperty(player,key, value);
			} catch (IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				// Again just for test purposes.  Since we are in the service layer 
				// and beyond the API.  We will throw a Business Logic Exception.
				throw new BusinessLogicException(e.getMessage());
			}
		}
           	
		return player;
	}

}
