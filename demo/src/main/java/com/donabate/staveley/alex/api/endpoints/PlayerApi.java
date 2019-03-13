package com.donabate.staveley.alex.api.endpoints;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.donabate.staveley.alex.pojos.ResourceListWrapper;
import com.donabate.staveley.alex.pojos.player.Player;
import com.donabate.staveley.alex.pojos.player.PlayerQuery;
import com.donabate.staveley.alex.pojos.team.Team;
import com.donabate.staveley.alex.pojos.team.TeamQuery;
import com.donabate.staveley.alex.service.PlayerService;
import com.donabate.staveley.alex.service.TeamService;

public class PlayerApi {

    @Autowired
    private PlayerService playerService;
    
    /**
     * On command line do:
     * <pre>
     * > curl localhost:8080/players/123
     * </pre>
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getSingle(@PathParam("id") String id) {
    	System.out.println(">>getSingle(), id=" + id);
    	Player player = playerService.findPlayer(id);
  
    	GenericEntity<Player> myEntity = 
    			new GenericEntity<Player>(player) {};
    	return Response.status(200).entity(myEntity).build();
    }
    
    
    /**
     * On command line do:
     * <pre>
     * > curl localhost:8080/players
     * </pre>
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response query(@BeanParam PlayerQuery playerQuery) {
    	System.out.println(">>query(), query=" + playerQuery);
    	
    	List<Player> players = playerService.findAllPlayers(playerQuery);
    	ResourceListWrapper<Player> resourceListWrapper = 
    			new ResourceListWrapper<Player>(players);
    	GenericEntity<ResourceListWrapper<Player>> myEntity = 
    			new GenericEntity<ResourceListWrapper<Player>>(resourceListWrapper) {};
    	return Response.status(200).entity(myEntity).build();
    }
}
