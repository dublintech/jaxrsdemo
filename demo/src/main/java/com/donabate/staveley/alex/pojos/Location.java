package com.donabate.staveley.alex.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "location" })
public interface Location {
	 
     public String getLocation();
}
