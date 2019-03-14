package com.donabate.staveley.alex.pojos.resource;

import java.net.URL;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(value = { "location" })

public interface LinkHolder {

	public final static String SELF = "self";
	

	@JsonProperty("_links")
	public Map<String, URL> getLinks();

}
