package com.donabate.staveley.alex.pojos.team;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import com.donabate.staveley.alex.pojos.Query;

public class TeamQuery implements Query {
	
    @QueryParam("name")
    private String name;
    
    @Context 
    private UriInfo uriInfo;

    public UriInfo getUriInfo() {
		return uriInfo;
	}

	public void setUriInfo(UriInfo uriInfo) {
		this.uriInfo = uriInfo;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
    	System.out.println (">>setName()");
        this.name = name;
    }

}
