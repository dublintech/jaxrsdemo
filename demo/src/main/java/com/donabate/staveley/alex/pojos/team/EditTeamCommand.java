package com.donabate.staveley.alex.pojos.team;

import javax.validation.constraints.NotNull;
import javax.ws.rs.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.donabate.staveley.alex.api.endpoints.TeamApi;

public class EditTeamCommand {
    private static final Logger LOG = LoggerFactory.getLogger(EditTeamCommand.class);
    
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
        LOG.info(">>EditTeamCommand.setName()");
        this.name = name;
    }
    
}
