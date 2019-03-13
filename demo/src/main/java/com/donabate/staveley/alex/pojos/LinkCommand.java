package com.donabate.staveley.alex.pojos;

import javax.validation.constraints.NotNull;

public class LinkCommand {
	@Override
	public String toString() {
		return "LinkCommand [id=" + id + ", resource=" + resource + ", relName=" + relName + "]";
	}
	
	@NotNull
	private String id;
	
	@NotNull
	private String resource;
	
	@NotNull
	private String relName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	
	public String getRelName() {
		return relName;
	}
	
	public void setRelName(String relName) {
		this.relName = relName;
	}
	

}
