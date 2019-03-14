package com.donabate.staveley.alex.pojos.command;

public class UnlinkCommand {
	@Override
	public String toString() {
		return "LinkCommand [id=" + id + ", resource=" + resource + ", relName=" + relName + "]";
	}
	private String id;
	private String resource;
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
