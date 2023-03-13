package com.sk.explorecamel.basic;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sk.explorecamel.util.CamelConstants;
import com.sk.explorecamel.util.CamelConstants.SecurityRoutes;

@Component
public class RestRouter extends RouteBuilder {

	@Value("${server.port}")
	private int port;

	@Override
	public void configure() throws Exception {
		restConfiguration().component("servlet").host("localhost").port(port).bindingMode(RestBindingMode.json);

		rest().get("/notices/getDetails").to(CamelConstants.DIRECT + SecurityRoutes.notices);

	}

}
