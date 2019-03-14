package com.donabate.staveley.alex.pojos.player;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import com.donabate.staveley.alex.pojos.Query;

public class PlayerQuery implements Query {
    @QueryParam("name")
    private String name;
    
    @Context 
    private UriInfo uriInfo;

    @QueryParam("pageSize")
    private int pageSize;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
    	System.out.println (">>setName()");
        this.name = name;
    }
    
    public UriInfo getUriInfo() {
		return uriInfo;
	}

	public void setUriInfo(UriInfo uriInfo) {
		this.uriInfo = uriInfo;
	}
}
