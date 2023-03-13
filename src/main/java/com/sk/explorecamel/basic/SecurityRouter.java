package com.sk.explorecamel.basic;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.sk.explorecamel.util.CamelConstants;
import com.sk.explorecamel.util.CamelConstants.SecurityRoutes;

@Component
public class SecurityRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		from(CamelConstants.DIRECT + SecurityRoutes.notices)
				.process(new RestProcessor())
				.to(SecurityRoutes.hostAndPort + SecurityRoutes.notices + CamelConstants.BRIDGE_ENDPOINT);
	}
}
