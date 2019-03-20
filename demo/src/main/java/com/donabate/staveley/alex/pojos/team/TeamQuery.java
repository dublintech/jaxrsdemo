package com.donabate.staveley.alex.pojos.team;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.donabate.staveley.alex.pojos.query.Query;

public class TeamQuery implements Query {
    private static final Logger LOG = LoggerFactory.getLogger(TeamQuery.class);
    
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
        LOG.info(">>setName()");
        this.name = name;
    }

}
