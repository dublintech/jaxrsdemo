package com.donabate.staveley.alex.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.donabate.staveley.alex.pojos.player.Player;
import com.donabate.staveley.alex.pojos.player.PlayerQuery;
import com.donabate.staveley.alex.pojos.player.WritePlayerCommand;
import com.donabate.staveley.alex.service.validation.BusinessLogicException;
import com.donabate.staveley.alex.api.endpoints.PlayerApi;
import com.donabate.staveley.alex.pojos.command.EditCommand;

import static com.donabate.staveley.alex.service.validation.BusinessLogicException.BusinessErrorCodeEnum;


@Component("playerService")
public final class PlayerService {
	
	private static final Logger LOG = LoggerFactory.getLogger(PlayerService.class);

	private List<Player> playersInDB = null;
	
	
	public PlayerQueryResponse findAllPlayers(PlayerQuery playerQuery) {
	    Integer pageSize =  playerQuery.getPageSize();
	    Integer pageStartIndex = playerQuery.getPageStartIndex();
	
	    // If pageSize is then full player set return lot
		
	    // Check if there is any filtering needed.
	    //  For now the only filter is when both are there.
	    List<Player> filteredPlayers = null;
	    if (playerQuery.getName() != null && playerQuery.getAge() != null) {
	        filteredPlayers = 
	            this.playersInDB.stream().filter(player -> player.getName().equals(playerQuery.getName()))     // we dont like mkyong
	            .filter(player -> player.getAge() == playerQuery.getAge())
	            .collect(Collectors.toList());  
	    } else {
	        filteredPlayers = playersInDB;
	    }
		
	    List<Player> playersToReturn = null;
	    this.sortPlayers(filteredPlayers, playerQuery.getSort());
	    if (pageSize == null || pageSize > filteredPlayers.size()) {
	        playersToReturn = filteredPlayers;
	    } else {
	        playersToReturn = filteredPlayers.subList(pageStartIndex, pageStartIndex + pageSize);
	    }
		
	    return new PlayerQueryResponse (playersToReturn, filteredPlayers.size());
	}
	
	@PostConstruct
	private void initPlayers() {
	    Player.Builder builderEuan = new Player.Builder();
	    builderEuan.withName("Euan Staveley");
	    builderEuan.withAge(21);
		
	    Player.Builder builderOliver = new Player.Builder();
	    builderOliver.withName("Oliver Staveley");
	    builderEuan.withAge(22);
		
	    Player.Builder vanDijkBuilder = new Player.Builder();
	    vanDijkBuilder.withName("Van Dijk");
	    builderEuan.withAge(23);
		
	    Player.Builder gomezBuilder = new Player.Builder();
	    gomezBuilder.withName("Gomez");
	    builderEuan.withAge(24);
		
	    // Use add All so it is possible to add and remove from list
	    playersInDB = new ArrayList<>();
		
	    playersInDB.addAll(
	            Arrays.asList(builderEuan.build("11"), builderOliver.build("12"),
	                    vanDijkBuilder.build("13"),
	                    gomezBuilder.build("14")));
	}
	
	/**
	 * @throws BusinessLogicException if player not in DB
	 * @param playerId
	 * @return
	 */
	public Player getPlayer(String playerId) throws BusinessLogicException {	
	    LOG.info(">>getPlayer(playerId=" + playerId + ")");
	    Optional<Player> foundPlayer = 
	            playersInDB.stream().filter(player -> player.getId().equals(playerId)).findFirst();
		
	    if (foundPlayer.isPresent()) {
	        return foundPlayer.get();
	    } else {
	        throw new BusinessLogicException("Player with id:" + playerId + 
	                ", not in DB", BusinessErrorCodeEnum.ENTIT_NOT_IN_DB);
	    }
	}
	
	public Player editPlayer(String playerId, EditCommand editCommand) {
	    LOG.info(">>editPlayer(playerId=" + playerId + ",editCommand=" + editCommand);
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
	    Player player = this.getPlayer(playerId);
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
	            throw new BusinessLogicException(e.getMessage(), 
	                    BusinessErrorCodeEnum.INTERNAL_PROCESSING_ERROR);
	        }
	    }
        return player;
	}
	
	/**
	 * For now we just sort on one field. Can be ascending or descending.
	 * @param players
	 * @param sortField
	 */
	private void sortPlayers(List<Player> players, String sortField) {
	    LOG.info("sortPlayers=(players=" + players + ",sortField=" + sortField);
	    if (sortField != null && !sortField.equals("")) {
	        Comparator<Player> comp = new Comparator<Player>(){
	            @Override
	            public int compare(Player p1, Player p2) {
	                // Descending if it begins with a -
	                boolean desc  = sortField.startsWith("-");
	                String fieldToUse = desc ? sortField.substring(1): sortField; 

	                try {
	                    String field1 = BeanUtils.getProperty(p1, fieldToUse);
	                    String field2 = BeanUtils.getProperty(p2, fieldToUse);
	                    return (desc) ? field2.compareTo(field1) : field1.compareTo(field2);
	                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
						// TODO Auto-generated catch block
						// Again just for test purposes.  Since we are in the service layer 
						// and beyond the API.  We will throw a Business Logic Exception.
						throw new BusinessLogicException(e.getMessage(), 
								BusinessErrorCodeEnum.INTERNAL_PROCESSING_ERROR);
					}
				}
				
			};
			Collections.sort(players, comp);
		}
	}
	
	public Player replace(String playerId, WritePlayerCommand writePlayerCommand) {
	    // This just a simulation of a full replace.
	    // Step 1 - go off to and get existing player from DB
	    Player player = this.getPlayer(playerId);
	    LOG.info(">>replacePlayer(playerId=" + playerId + ", writePlayerCommand=" + 
				writePlayerCommand + ")");
	    Player.Builder builderPlayer = new Player.Builder();
	    builderPlayer.withName(writePlayerCommand.getName());
	    builderPlayer.withAge(writePlayerCommand.getAge());
	    Player newPlayer =  builderPlayer.build(playerId);

	    this.playersInDB.remove(player);
	    this.playersInDB.add(newPlayer);
		
	    return newPlayer;	
	}
	
	public Optional<Player> getPlayerByName(String name) {
	    Optional<Player> foundPlayer = 
				playersInDB.stream().filter(player -> 
					player.getName().equals(name)).findFirst();
	    return foundPlayer;
	}
	
	public Player create(WritePlayerCommand writePlayerCommand) {
	    LOG.info(">>create(writePlayerCommand=" + writePlayerCommand + ")");
	    Player.Builder builderPlayer = new Player.Builder();
	    builderPlayer.withName(writePlayerCommand.getName());
	    builderPlayer.withAge(writePlayerCommand.getAge());
	    Player player =  builderPlayer.build();
	    // Add to players in DB.
	    playersInDB.add(player);
	    return player;	
	}
	
	public void remove(String playerId) {
		// reality, delete happens inDB.
		// Step 1 - go off to and get existing player from DB
	    Player player = this.getPlayer(playerId);
	    LOG.info("Removing player" + player);
		// Step 2 delete it.
		// Remove it from players  in DB
	    this.playersInDB.remove(player);
	}

}
