package com.donabate.staveley.alex.pojos.player;

import javax.validation.constraints.NotNull;

/** Write Player command can be used for
 * <UL>
 * <LI> Creating a player
 * <LI> Replace a player
 * <LI> Create or Update (store)
 * </UL>
 * 
 * @author astaveley
 *
 */
public class WritePlayerCommand {
	@NotNull
    private String name;
	
	@NotNull
	private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
    	System.out.println (">>CreateTeamCommand.setName()");
        this.name = name;
    }

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
    
    
}
