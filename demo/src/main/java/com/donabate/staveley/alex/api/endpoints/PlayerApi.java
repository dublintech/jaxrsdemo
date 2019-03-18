package com.donabate.staveley.alex.api.endpoints;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
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
import com.donabate.staveley.alex.api.validation.PlayerApiValidator;
import com.donabate.staveley.alex.api.validation.TeamApiValidator;
import com.donabate.staveley.alex.pojos.command.EditCommand;
import com.donabate.staveley.alex.pojos.player.Player;
import com.donabate.staveley.alex.pojos.player.PlayerQuery;
import com.donabate.staveley.alex.pojos.player.WritePlayerCommand;
import com.donabate.staveley.alex.pojos.resource.LinkHolder;
import com.donabate.staveley.alex.pojos.resource.PaginationLinks;
import com.donabate.staveley.alex.pojos.resource.ResourceListWrapper;
import com.donabate.staveley.alex.pojos.team.Team;
import com.donabate.staveley.alex.pojos.team.TeamQuery;
import com.donabate.staveley.alex.service.PlayerQueryResponse;
import com.donabate.staveley.alex.service.PlayerService;
import com.donabate.staveley.alex.service.TeamService;
import com.donabate.staveley.alex.service.validation.BusinessLogicException;
import com.donabate.staveley.alex.pojos.command.EditCommand;

@Service
@Path("/players")
public class PlayerApi {

    @Autowired
    private PlayerService playerService;
    
    @Autowired
    private PlayerApiValidator playerApiValidator;
    
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
    	Player player = playerService.getPlayer(id);
  
    	GenericEntity<Player> myEntity = 
    			new GenericEntity<Player>(player) {};
    	return Response.status(200).entity(myEntity).build();
    }
    
    
    /**
     * On command line do:
     * <pre>
     * > curl localhost:8080/players
     * </pre>
     * 
     * Or for asc sort:
     * <pre>
     * curl localhost:8080/players?sort=name
     * </pre>
     * 
     * Or for desc sort:
     * <pre>
     * curl localhost:8080/players?sort=-name
     * </pre>
     * 
     * Or for Pagination
     * <pre>
     * curl localhost:8080/players?pageStartIndex=3"&"pageSize=4
     * </pre>
     * 
     * Pagination and sorting:
     * <pre>
     * curl localhost:8080/players?pageSize=1"&"pageStartIndex=0"&"sort=name
     * curl localhost:8080/players?pageSize=1"&"pageStartIndex=1"&"sort=name
     * curl localhost:8080/players?pageSize=1"&"pageStartIndex=2"&"sort=name
     * curl localhost:8080/players?pageSize=1"&"pageStartIndex=3"&"sort=name
     * </pre>
     *
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response query( 
    		@BeanParam @Valid PlayerQuery playerQuery) {
    	System.out.println(">>query(), query=" + playerQuery);
    	playerApiValidator.validate(playerQuery);
    	
    	PlayerQueryResponse playerQueryResponse = playerService.findAllPlayers(playerQuery);
    	List<Player> players  = playerQueryResponse.getPlayers();
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
    				
    	if (playerQuery.getPageSize() != null && playerQuery.getPageSize() > 0) {
    		resourceListWrapper.addPagination(createPaginationLinks(playerQuery, url, playerQueryResponse.getTotalSize()));
    	}
    	
    	GenericEntity<ResourceListWrapper<Player>> myEntity = 
    			new GenericEntity<ResourceListWrapper<Player>>(resourceListWrapper) {};
    	return Response.status(200).entity(myEntity).build();
    }
    
    private PaginationLinks createPaginationLinks(PlayerQuery playerQuery, URL url, int totalQuerySize) {
    	System.out.println(">>createPaginationLinks(totalQuerySize=" + totalQuerySize);
    	int pageStartIndex = playerQuery.getPageStartIndex();
    	int pageSize = playerQuery.getPageSize();
    	
    	PaginationLinks paginationLinks = new PaginationLinks();
		String urlAsString = url.toString();
		String firstString = urlAsString.substring(0, 
				urlAsString.indexOf("pageStartIndex=") + 15) +
				"0" +
				urlAsString.substring(urlAsString.indexOf("pageStartIndex=") + 16, 
	    				urlAsString.length()); 
		
		String nextString = urlAsString.substring(0, 
				urlAsString.indexOf("pageStartIndex=") + 15) +
				(pageStartIndex + pageSize) + 
				urlAsString.substring(urlAsString.indexOf("pageStartIndex=") + 16, 
	    				urlAsString.length()); 
		
		
		//  previous index should never be less than 0
		int previousIndex = (pageStartIndex - pageSize) < 0 ? 0 :  pageStartIndex - pageSize;
		
		String prevString = urlAsString.substring(0, 
				urlAsString.indexOf("pageStartIndex=") + 15) +
				(previousIndex) + 
				urlAsString.substring(urlAsString.indexOf("pageStartIndex=") + 16, 
	    				urlAsString.length()); 
		
		String lastString = urlAsString.substring(0, 
				urlAsString.indexOf("pageStartIndex=") + 15) +
				// Below is inaccurate but ok for now.
				(totalQuerySize - pageSize) + 
				urlAsString.substring(urlAsString.indexOf("pageStartIndex=") + 16, 
	    				urlAsString.length()); 
		
		try {
			paginationLinks.setFirst(new URL(firstString));
			paginationLinks.setNext(new URL(nextString));
			paginationLinks.setPrev(new URL(prevString));
			paginationLinks.setLast(new URL(lastString));
		} catch (MalformedURLException me) {
			/// very unlikely to happen.
    		// so swallow, for prototype
    		me.printStackTrace();
		}
		
		return paginationLinks;
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
    
    /**
     * To execute this:
     *  curl -X POST -v -d "{"""age""":27, """name""", """Tony Magoo"""}" localhost:8080/players/123/replace --header "Content-Type:application/json"
     * @param id
     * @param editCommand
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/replace")
    public Response replace(@PathParam("id") String id,  
    		WritePlayerCommand replacePlayerCommand) {
    	Player player = playerService.replace(id, replacePlayerCommand);
    	return Response.status(200).header("location", player.getLinks().get(LinkHolder.SELF)).entity(player).build();
    }
    
    /**
     * To execute this:
     *  curl -X POST -v -d "{"""age""":27, """name""", """Tony Magoo"""}" localhost:8080/players/store --header "Content-Type:application/json"
     * @param id
     * @param editCommand
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/replace")
    public Response store(WritePlayerCommand writePlayerCommand) {
    	// Does the player exist. 
    	Optional<Player> foundPlayer =  playerService.getPlayerByName(writePlayerCommand.getName());
    	Player player = null;
    	int status = 0;
    	if (foundPlayer.isPresent()) {
    		player =  playerService.replace(foundPlayer.get().getId(), writePlayerCommand);
    		status = 200;
    	} else {
    		player = playerService.create(writePlayerCommand);
    		status = 201;
    	}
    		
  
    	return Response.status(status).header("location", player.getLinks().get(LinkHolder.SELF)).entity(player).build();
    }
    
    /**
     * To execute this:
     *  curl -X POST -v -d localhost:8080/players/123/remove --header "Content-Type:application/json"
     * @param id
     * @param editCommand
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}/replace")
    public Response edit(@PathParam("id") String playerId) {
        playerService.remove(playerId);
    	return Response.status(204).build();
    }
}
