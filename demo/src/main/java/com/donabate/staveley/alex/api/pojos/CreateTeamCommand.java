package com.donabate.staveley.alex.api.pojos;

public class CreateTeamCommand {
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
}
