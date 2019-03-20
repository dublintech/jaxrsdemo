package com.donabate.staveley.alex.pojos.player;

import javax.validation.constraints.Pattern;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.donabate.staveley.alex.api.endpoints.JerseyApi;
import com.donabate.staveley.alex.pojos.query.PageableQuery;
import com.donabate.staveley.alex.pojos.query.Query;
import com.donabate.staveley.alex.pojos.query.SortableQuery;

public class PlayerQuery implements Query, SortableQuery, PageableQuery {
    private static final Logger LOG = LoggerFactory.getLogger(PlayerQuery.class);
    
    @Override
	public String toString() {
		return "PlayerQuery [name=" + name + ", age=" + age + ", sort=" + sort + ", pageStartIndex=" + pageStartIndex
				+ ", pageSize=" + pageSize + ", uriInfo=" + uriInfo + "]";
	}

	@QueryParam("name")
    private String name;
    
    @QueryParam("age")
    private Integer age;
    
    @Pattern(message="Sort field invalid", regexp="-?name")
    @QueryParam("sort")
    private String sort;
    
    @QueryParam("pageStartIndex")
    private Integer pageStartIndex;
    
    @QueryParam("pageSize")
    private Integer pageSize;

    public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Context 
    private UriInfo uriInfo;

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		System.out.println(">>setPageSize=" + pageSize);
		this.pageSize = pageSize;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
    	System.out.println (">>setName()");
        this.name = name;
    }
    
    public UriInfo getUriInfo() {
		return uriInfo;
	}

	public void setUriInfo(UriInfo uriInfo) {
		this.uriInfo = uriInfo;
	}

	@Override
	public Integer getPageStartIndex() {
		// TODO Auto-generated method stub
		return this.pageStartIndex;
	}
	
	public void setPageStartIndex(int pageStartIndex) {
		// TODO Auto-generated method stub
	    LOG.info(">>setPageStartIndex=" + pageStartIndex);
		this.pageStartIndex = pageStartIndex;
	}
	

}
