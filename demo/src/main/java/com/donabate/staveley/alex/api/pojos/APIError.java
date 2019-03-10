package com.donabate.staveley.alex.api.pojos;

import java.net.URI;
import java.util.Map;

public class APIError {
	/**
	 * An unique identifier for this particular occurrence of the problem.
	 */
	private String id;
	
	/**
	 * a links object containing links to more errors about problem
	 */
	private Map <String, URI> links; 
	

	/**
	 * The HTTP status code applicable to this problem, expressed as a string
	 */
	private String status;
	
	/**
	 * an application-specific error code, expressed as a string value.
	 */
	private String code;
		
	/**
	 * a short, human-readable summary of the problem that SHOULD NOT change from occurrence to occurrence of the problem, except for purposes of localization.
	 */
	private String title;
	
	/**
	 * human-readable explanation specific to this occurrence of the problem. Like title, this field’s value can be localized.
	 */
	private String detail;
		
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, URI> getLinks() {
		return links;
	}

	public void setLinks(Map<String, URI> links) {
		this.links = links;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public ErrorSource getSource() {
		return source;
	}

	public void setSource(ErrorSource source) {
		this.source = source;
	}

	public Map getMeta() {
		return meta;
	}

	public void setMeta(Map meta) {
		this.meta = meta;
	}

	/**
	 * 	an object containing references to the source of the error, optionally including any of the following members:
	 */
	private ErrorSource source;

	/**
	 * a meta object containing non-standard meta-information about the error.
	 */
	private Map meta;
	
	
}
