package com.sk.explorecamel.util;

import org.apache.camel.Exchange;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class CamelUtil {

    public Map getSharedData(Exchange oldExchange) {
        return oldExchange.getProperty("sharedData", Map.class);
    }
}
