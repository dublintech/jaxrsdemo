package com.donabate.staveley.alex.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "location" })
public interface SelfReferencing {
	 
     public String getSelf();
}
