package com.sk.explorecamel.util;

public interface Constants {

    String QUES_BRIDGE_ENDPOINT = "?bridgeEndpoint=true";
    String AND_BRIDGE_ENDPOINT = "&bridgeEndpoint=true";
    String DIRECT = "direct:";
    String APP_JSON = "application/json";
    String ACCEPT = "Accept";
    String CONTENT_TYPE = "Content-Type";
    String camelHttp = "CamelHttp*";
    String allData = "allData";

    String hostAndPort = "http://localhost:8080";

    interface NotifyRoutes {

        String notification = "/notification";
        String basePath = hostAndPort + notification;
        String updateNotification = basePath + "/updateTodaysFeed";

        String getFeedByContent_ViaDynamicURI = basePath + "/getFeedByContent_ViaDynamicURI";
        String getFeedByContent = basePath + "/getFeedByContent";
        String getFeedById = basePath + "/getFeedById";
    }

    interface UsersRoutes {
        String user = "/user";
        String basePath = hostAndPort + user;
        String findByGender = "/findByGender";
    }

    interface TestRoutes {
        String test = "/test";
        String testAggregationStrategy = "/testAggregationStrategy";

    }
}
