package com.donabate.staveley.alex.pojos.player;

import javax.validation.constraints.NotNull;

public class CreatePlayerCommand {

	@NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
    	System.out.println (">>CreateTeamCommand.setName()");
        this.name = name;
    }
    
	@Override
	public String toString() {
		return "CreateTeamCommand [name=" + name + "]";
	}
}
