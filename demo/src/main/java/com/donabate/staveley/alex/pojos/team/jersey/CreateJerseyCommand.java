package com.donabate.staveley.alex.pojos.team.jersey;

import javax.validation.constraints.NotNull;

public class CreateJerseyCommand {
	@NotNull
    private String type;
	
	@NotNull
    private String colour;

    public String getType() {
        return type;
    }

    public String getColour() {
    	return colour;
    }
    
    public void setColour(String colour) {
    	this.colour = colour;
    }
    
    public void setType(String type) {
        this.type = type;
    }
}
