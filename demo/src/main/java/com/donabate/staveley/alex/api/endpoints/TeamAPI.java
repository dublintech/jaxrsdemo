package com.donabate.staveley.alex.api.endpoints;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donabate.staveley.alex.api.exceptions.APIException;
import com.donabate.staveley.alex.api.validation.TeamApiValidator;
import com.donabate.staveley.alex.pojos.command.DeleteCommand;
import com.donabate.staveley.alex.pojos.command.LinkCommand;
import com.donabate.staveley.alex.pojos.command.UnlinkCommand;
import com.donabate.staveley.alex.pojos.error.APIError;
import com.donabate.staveley.alex.pojos.error.ErrorResponse;
import com.donabate.staveley.alex.pojos.resource.LinkHolder;
import com.donabate.staveley.alex.pojos.resource.ResourceListWrapper;
import com.donabate.staveley.alex.pojos.team.CreateTeamCommand;
import com.donabate.staveley.alex.pojos.team.EditTeamCommand;
import com.donabate.staveley.alex.pojos.team.Team;
import com.donabate.staveley.alex.pojos.team.TeamQuery;
import com.donabate.staveley.alex.service.TeamService;
import com.donabate.staveley.alex.service.validation.BusinessLogicException;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author astaveley
 */
@Service
@Path("/teams")
public class TeamApi {
	
    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamApiValidator teamApiValidator;
    
    /**
     * On command line do:
     * <pre>
     * > curl localhost:8080/teams/123
     * </pre>
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getSingle(@PathParam("id") String id) {
    	System.out.println(">>getSingle(), id=" + id);
    	Team team = teamService.findTeam(id);
  
    	GenericEntity<Team> myEntity = 
    			new GenericEntity<Team>(team) {};
    	return Response.status(200).entity(myEntity).build();
    }
    
    
    /**
     * On command line do:
     * <pre>
     * > curl localhost:8080/teams
     * </pre>
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response query(@BeanParam TeamQuery teamQuery) {
    	System.out.println(">>query(), query=" + teamQuery);
    	
    	List<Team> teams = teamService.findTeams(teamQuery);
    	URL url = null;
    	try {
    		url = teamQuery.getUriInfo().getRequestUri().toURL();
    	}  catch (MalformedURLException me) {
    		// very unlikely to happen.
    		// so swallow, for prototype
    		me.printStackTrace();
    	}
    	
    	ResourceListWrapper<Team> resourceListWrapper = new ResourceListWrapper<Team>(teams, url);
    	GenericEntity<ResourceListWrapper<Team>> myEntity = 
    			new GenericEntity<ResourceListWrapper<Team>>(resourceListWrapper) {};
    	return Response.status(200).entity(myEntity).build();
    }

    
    /**
     * To test do:
     * <pre>
     * > curl -X POST -v -d "{"""name""":"""magoo"""}" localhost:8080/teams --header "Content-Type:application/json"
     * </pre>
     * To simulate the first error handling pattern, bean validation, do:
     * <pre>
     *  curl -X POST -v -d "{"""name2""":"""magoo"""}" localhost:8080/teams --header "Content-Type:application/json
     * </pre>
     * To simulate the second error handling pattern, where the payload fails for a reason
     * that the annotations can't validate, do:
     * <pre>
     * curl -X POST -v -d "{"""name""":"""422ex"""}" localhost:8080/teams --header "Content-Type:application/json"
     * </pre>
     * @param createTeamCommand
     * @return
     */   
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTeam(@Valid CreateTeamCommand createTeamCommand) {
    	System.out.println(">>createTeam(createTeamCommand=" + createTeamCommand + ")");
    	teamApiValidator.validate(createTeamCommand);
    	Team team = null;
    	try {
    		team = teamService.createTeam(createTeamCommand);
    	}  catch (BusinessLogicException ble) {
    		APIException.throwApiException(ble);
    	}
		GenericEntity<Team> myTeam = 
				new GenericEntity<Team>(team) {};
    	return Response.status(201).header("location", team.getLinks().get(LinkHolder.SELF)).entity(myTeam).build();
    	
    }
    
    /*
    * <pre>
    * > curl -X POST -v localhost:8080/teams/123/remove --header "Content-Type:application/json"
    * </pre>
    */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}/remove")
    public Response deleteTeam(@PathParam("id") String id) {
    	teamService.deleteTeam(id);
       	return Response.status(204).build();
    }
    
    /*
    * <pre>
    * > curl -X POST -v -d "{"""name""":"""magoo2"""}" localhost:8080/teams/123/edit --header "Content-Type:application/json"
    * </pre>
    */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/edit")
    public Response editTeam(@PathParam("id") String id, @Valid EditTeamCommand editTeamCommand) {
    	Team team = teamService.editTeam(id, editTeamCommand);
    	System.out.println("editTeam(), team=" + team);
    	GenericEntity<Team> myTeam = 
    			new GenericEntity<Team>(team) {};
    	return Response.status(200).header("location", team.getLinks().get(LinkHolder.SELF)).entity(myTeam).build();
    }
    
    /*
     * <pre>
     * curl -X POST -v -d "{"""relName""":"""players""", """resource""":"""player""", """id""":"""27"""}" localhost:8080/teams/100/link --header "Content-Type:application/json"
     * </pre>
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/link")
    public Response link(@PathParam("id") String id, @Valid LinkCommand linkCommand) {
		System.out.println(">>link(), id=" + id + ",linkCommand=" + linkCommand);
    	teamApiValidator.validate(linkCommand);
    	// For now we are only linking players.
 
		Team team = teamService.addPlayerToTeam(id, linkCommand);

		GenericEntity<Team> myTeam = 
			new GenericEntity<Team>(team) {};
	
		return Response.status(200).header("location", team.getLinks().get(LinkHolder.SELF)).entity(myTeam).build();
    }
    
    /*
     * <pre>
     * curl -X POST -v -d "{"""relName""":"""players""", """resource""":"""player""", """id""":"""27"""}" localhost:8080/teams/100/unlink --header "Content-Type:application/json"
     * </pre>
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/unlink")
    public Response unlink(@PathParam("id") String id, @Valid UnlinkCommand unlinkCommand) {
    	teamApiValidator.validate(unlinkCommand);
    	// For now we are only linking players.
 
		Team team = teamService.removePlayerFromTeam(id, unlinkCommand);
		System.out.println("link(), team=" + team);
		GenericEntity<Team> myTeam = 
			new GenericEntity<Team>(team) {};
	
		return Response.status(200).header("location", team.getLinks().get(LinkHolder.SELF)).entity(myTeam).build();
    }
    
    
}