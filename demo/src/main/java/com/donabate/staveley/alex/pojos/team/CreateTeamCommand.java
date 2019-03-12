package com.donabate.staveley.alex.pojos.team;

import javax.validation.constraints.NotNull;

public class CreateTeamCommand {

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
    	System.out.println (">>CreateTeamCommand.setName()");
        this.name = name;
    }
    
	@Override
	public String toString() {
		return "CreateTeamCommand [name=" + name + ", country=" + country + "]";
	}
}
