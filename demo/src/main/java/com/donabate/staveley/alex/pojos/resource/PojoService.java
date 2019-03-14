package com.donabate.staveley.alex.pojos.resource;

import java.net.MalformedURLException;
import java.net.URL;

public class PojoService {

	private PojoService() {
		// TODO Auto-generated constructor stub
	}

	public static URL createUrl(String relativePath) {
		// Server name is harder coded, but could be read from config file
		// or set at start up
		String urlAsString = "https://localhost:8080" + relativePath;
		URL url = null;
		
		try {
			url = new URL(urlAsString);
		} catch (MalformedURLException me) {
			System.err.println("exception=" + me.getMessage());
			me.printStackTrace();
		}
		
		return url;
	}
}
