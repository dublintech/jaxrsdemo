package com.donabate.staveley.alex.pojos.resource;

import java.net.URL;

public class PaginationLinks {

	public PaginationLinks() {
		// TODO Auto-generated constructor stub
	}
	
	private URL first;
	private URL last;
	private URL prev;
	private URL next;
	
	public URL getFirst() {
		return first;
	}
	public void setFirst(URL first) {
		this.first = first;
	}

	public URL getLast() {
		return last;
	}
	public void setLast(URL last) {
		this.last = last;
	}
	public URL getPrev() {
		return prev;
	}
	public void setPrev(URL prev) {
		this.prev = prev;
	}
	public URL getNext() {
		return next;
	}
	public void setNext(URL next) {
		this.next = next;
	}



}
