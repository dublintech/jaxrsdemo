package com.donabate.staveley.alex.api.pojos;

import java.util.List;
import java.util.ArrayList;

public class ErrorResponse {
	private List<APIError> apiErrors = new ArrayList<APIError>();
	
	public List<APIError> getApiErrors() {
		return apiErrors;
	}
	
	public void setAPIErrors(List<APIError> apiErrors) {
		this.apiErrors = apiErrors;
	}
}
