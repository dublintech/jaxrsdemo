package com.donabate.staveley.alex.pojos.player;

import javax.ws.rs.QueryParam;

public class PlayerQuery {
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
