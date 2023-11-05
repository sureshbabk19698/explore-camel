package com.sk.explorecamel.rest;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sk.explorecamel.util.Constants;
import com.sk.explorecamel.util.Constants.NotifyRoutes;
import com.sk.explorecamel.util.Constants.UsersRoutes;

@Component
public class ApiRoute extends RouteBuilder {

	@Value("${server.port}")
	private int port;

	@Override
	public void configure() throws Exception {
		restConfiguration().component("servlet").host("localhost").port(port).bindingMode(RestBindingMode.json);

		rest(NotifyRoutes.notification)
			.get(NotifyRoutes.getFeedByContent).to(Constants.DIRECT + NotifyRoutes.getFeedByContent)
			.get(NotifyRoutes.getFeedByContent_ViaDynamicURI).to(Constants.DIRECT + NotifyRoutes.getFeedByContent_ViaDynamicURI)
			.post(NotifyRoutes.updateNotification).to(Constants.DIRECT + NotifyRoutes.updateNotification)
			.get(NotifyRoutes.getFeedById +"/{id}").to(Constants.DIRECT + NotifyRoutes.getFeedById);

		rest(UsersRoutes.user) 
			.get(UsersRoutes.findByGender + "/{gender}").to(Constants.DIRECT + UsersRoutes.findByGender);
	}

}
