package com.donabate.staveley.alex.pojos;

import java.util.List;

public class ResourceListWrapper<T extends Resource> {
	public List<T> elements;
	
	public List<T> getElements() {
		return elements;
	}
	
	public ResourceListWrapper(List<T> elements) {
		this.elements = elements;
	}
}
