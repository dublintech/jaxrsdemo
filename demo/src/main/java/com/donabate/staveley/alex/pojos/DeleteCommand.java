package com.donabate.staveley.alex.pojos;

import javax.validation.constraints.NotNull;

public class DeleteCommand {
	@NotNull
	private String id;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
}
