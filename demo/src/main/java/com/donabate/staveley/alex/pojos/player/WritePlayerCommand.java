package com.donabate.staveley.alex.pojos.player;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final Logger LOG = 
            LoggerFactory.getLogger(WritePlayerCommand.class);
    
	@NotNull
    private String name;
	
	@NotNull
	private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        LOG.info(">>CreateTeamCommand.setName()");
        this.name = name;
    }

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
    
    
}
