package com.donabate.staveley.alex.api.endpoints;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donabate.staveley.alex.api.exceptions.APIException;
import com.donabate.staveley.alex.pojos.command.EditCommand;
import com.donabate.staveley.alex.pojos.player.Player;
import com.donabate.staveley.alex.pojos.player.PlayerQuery;
import com.donabate.staveley.alex.pojos.resource.LinkHolder;
import com.donabate.staveley.alex.pojos.resource.ResourceListWrapper;
import com.donabate.staveley.alex.pojos.team.Team;
import com.donabate.staveley.alex.pojos.team.TeamQuery;
import com.donabate.staveley.alex.service.PlayerService;
import com.donabate.staveley.alex.service.TeamService;
import com.donabate.staveley.alex.service.validation.BusinessLogicException;
import com.donabate.staveley.alex.pojos.command.EditCommand;

@Service
@Path("/players")
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
    public Response query( 
    		@BeanParam PlayerQuery playerQuery) {
    	System.out.println(">>query(), query=" + playerQuery);
    	
    	List<Player> players = playerService.findAllPlayers(playerQuery);
    	URL url = null;
    	try {
    		url = playerQuery.getUriInfo().getRequestUri().toURL();
    	}  catch (MalformedURLException me) {
    		// very unlikely to happen.
    		// so swallow, for prototype
    		me.printStackTrace();
    	}
    	
    	ResourceListWrapper<Player> resourceListWrapper = 
    			new ResourceListWrapper<Player>(players, url);
    				
    	GenericEntity<ResourceListWrapper<Player>> myEntity = 
    			new GenericEntity<ResourceListWrapper<Player>>(resourceListWrapper) {};
    	return Response.status(200).entity(myEntity).build();
    }
    
    
    /**
     * To execute this:
     *  curl -X POST -v -d "{"""age""":27}" localhost:8080/players/123/edit --header "Content-Type:application/json"
     * @param id
     * @param editCommand
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/edit")
    public Response edit(@PathParam("id") String id,  
    		EditCommand editCommand) {
    	Player player = null;
    	try {
        	player = playerService.editPlayer(id, editCommand);
    	}  catch (BusinessLogicException ble) {
    		APIException.throwApiException(ble);
    	}

    	return Response.status(200).header("location", player.getLinks().get(LinkHolder.SELF)).entity(player).build();
    }
    
}
