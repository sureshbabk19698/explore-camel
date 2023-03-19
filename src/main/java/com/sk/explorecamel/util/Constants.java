package com.sk.explorecamel.util;

public interface Constants {

	String BRIDGE_ENDPOINT = "?bridgeEndpoint=true";
	String DIRECT = "direct:";
	String APP_JSON = "application/json";
	String ACCEPT = "Accept";
	String CONTENT_TYPE = "Content-Type";
	
	interface NotifyRoutes {
		String hostAndPort = "http://localhost:8080";
		String notification = "/notification";
		String updateNotification = "/updateTodaysFeed";
	}

}
