package com.sk.explorecamel.util;

public interface Constants {

	String QUES_BRIDGE_ENDPOINT = "?bridgeEndpoint=true";
	String AND_BRIDGE_ENDPOINT = "&bridgeEndpoint=true";
	String DIRECT = "direct:";
	String APP_JSON = "application/json";
	String ACCEPT = "Accept";
	String CONTENT_TYPE = "Content-Type";
	String camelHttp = "CamelHttp*";
	
	interface NotifyRoutes {
		String hostAndPort = "http://localhost:8080";
		String notification = "/notification";
		String updateNotification = "/updateTodaysFeed";
		String getFeedByContent_ViaDynamicURI = "/getFeedByContent_ViaDynamicURI";
		String getFeedByContent = "/getFeedByContent";
	}

}
