package com.donabate.staveley.alex.pojos.team;

import javax.validation.constraints.NotNull;
import javax.ws.rs.PathParam;

public class EditTeamCommand {
	@NotNull
    private String name;
    private String country;

    public String getName() {
        return name;
    }

    public String getCountry() {
    	return country;
    }
    
    public void setCountry(String country) {
    	this.country = country;
    }
    
    public void setName(String name) {
    	System.out.println (">>EditTeamCommand.setName()");
        this.name = name;
    }
    
}
