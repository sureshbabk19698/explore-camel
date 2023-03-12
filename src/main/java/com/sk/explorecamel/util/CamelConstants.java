package com.sk.explorecamel.util;

public interface CamelConstants {

	String BRIDGE_ENDPOINT = "?bridgeEndpoint=true";
	String DIRECT = "direct:";

	interface SecurityRoutes {
		String hostAndPort = "http://localhost:8080";
		String notices = "/notices/getDetails";
	}

}
