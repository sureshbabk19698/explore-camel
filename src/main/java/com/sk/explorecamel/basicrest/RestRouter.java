package com.sk.explorecamel.basicrest;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sk.explorecamel.util.Constants;
import com.sk.explorecamel.util.Constants.NotifyRoutes;

@Component
public class RestRouter extends RouteBuilder {

	@Value("${server.port}")
	private int port;

	@Override
	public void configure() throws Exception {
		restConfiguration().component("servlet").host("localhost").port(port).bindingMode(RestBindingMode.json);

		rest(NotifyRoutes.notification)
			.post(NotifyRoutes.updateNotification).to(Constants.DIRECT + NotifyRoutes.updateNotification);

	}

}