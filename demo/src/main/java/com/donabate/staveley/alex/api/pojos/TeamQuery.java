package com.donabate.staveley.alex.api.pojos;

import javax.ws.rs.QueryParam;

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
