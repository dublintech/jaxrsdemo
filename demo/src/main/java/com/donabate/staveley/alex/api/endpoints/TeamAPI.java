package com.donabate.staveley.alex.api.endpoints;

import javax.inject.Inject;
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

import com.donabate.staveley.alex.api.pojos.CreateTeamCommand;
import com.donabate.staveley.alex.api.pojos.ResourceListWrapper;
import com.donabate.staveley.alex.api.pojos.Team;
import com.donabate.staveley.alex.api.pojos.TeamQuery;
import com.donabate.staveley.alex.api.service.TeamService;
import com.donabate.staveley.alex.api.service.validation.TeamValidationService;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * 
 * @author astaveley
 *
 */
@Service
@Path("/teams")
public class TeamAPI {
	
    @Autowired
    private TeamService teamService;
    
    // ToDo consider if it s better the API invoked this or if the teamService does. 
    @Autowired
    private TeamValidationService teamValidationService;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getSingle(@PathParam("username") String id) {
    	System.out.println(">>getSingle()");
    	Team team = teamService.find(id);
  
    	GenericEntity<Team> myEntity = 
    			new GenericEntity<Team>(team) {};
    	return Response.status(200).entity(myEntity).build();
    }
    
    @Path("/queryparam")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response paramQuery(@QueryParam("name") String name) {
    	System.out.println(">>paramQuery(), name=" + name);
    	Response response = null;
    	if (name == null) {
    		response = getAll();
    	} else {
    		Team team = teamService.findByName(name);
  
    		GenericEntity<Team> myEntity = 
    			new GenericEntity<Team>(team) {};
    			response = Response.status(200).entity(myEntity).build();
    	}
    	return response;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response typeQuery(@BeanParam TeamQuery teamQuery) {
    	System.out.println(">>typeQuery(), teamQuery=" + teamQuery);
    	Response response = null;
    	String name =  teamQuery.getName();
    	if (name == null) {
    		response = getAll();
    	} else {
    		Team team = teamService.findByName(name);
  
    		GenericEntity<Team> myEntity = 
    			new GenericEntity<Team>(team) {};
    			response = Response.status(200).entity(myEntity).build();
    	}
    	return response;
    }

    
    /**
     * To test do:
     * <pre>
     * curl -X POST -v -d "{"""name""":"""magoo"""}" localhost:8080/teams --header "Content-Type:application/json"
     * </pre>
     * @param createTeamCommand
     * @return
     */
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTeam(CreateTeamCommand createTeamCommand) {
    	System.out.println(">>createTeam(createTeamCommand=" + createTeamCommand + ")");
    	
    	teamValidationService.validate(createTeamCommand);
    	
    	Team team = teamService.createTeam(createTeamCommand);
    	
    	GenericEntity<Team> myTeam = 
    			new GenericEntity<Team>(team) {};
    			
    	return Response.status(201).header("location", team.getLocation()).entity(myTeam).build();

    }
    
    
    private Response getAll() {
    	System.out.println(">>getList()");
    	List<Team> teams = teamService.findAll();
    	ResourceListWrapper<Team> resourceListWrapper = new ResourceListWrapper<Team>(teams);
    	GenericEntity<ResourceListWrapper<Team>> myEntity = 
    			new GenericEntity<ResourceListWrapper<Team>>(resourceListWrapper) {};
    	return Response.status(200).entity(myEntity).build();
    }
    
}