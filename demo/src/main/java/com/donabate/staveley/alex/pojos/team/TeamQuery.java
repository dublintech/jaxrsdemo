package com.donabate.staveley.alex.pojos.team;

import javax.ws.rs.QueryParam;

import com.donabate.staveley.alex.pojos.Query;

public class TeamQuery implements Query {
	
    @QueryParam("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
    	System.out.println (">>setName()");
        this.name = name;
    }

}
