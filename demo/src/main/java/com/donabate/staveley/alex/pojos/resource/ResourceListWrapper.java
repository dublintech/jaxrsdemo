package com.donabate.staveley.alex.pojos.resource;

import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResourceListWrapper<T extends Resource> implements LinkHolder {
	private List<T> elements;
	private Map<String, URL> links = new HashMap<>();
	
	public List<T> getElements() {
		return elements;
	}
	
	public ResourceListWrapper(List<T> elements, URL url) {
		this.elements = elements;
		links.put(SELF, url);
	}

	@Override
	public Map<String, URL> getLinks() {
		// TODO Auto-generated method stub
		return links;
	}
	
	public void addPagination(PaginationLinks paginationLinks) {
		links.put("first", paginationLinks.getFirst());
		links.put("last", paginationLinks.getLast());
		links.put("prev", paginationLinks.getPrev());
		links.put("next", paginationLinks.getNext());
	}
}
