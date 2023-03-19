package com.sk.explorecamel.basic;

import java.util.List;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.ListJacksonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import com.sk.explorecamel.model.NotifcationModel;
import com.sk.explorecamel.util.Constants;
import com.sk.explorecamel.util.Constants.NotifyRoutes;

@Component
public class DirectRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		from(Constants.DIRECT + NotifyRoutes.updateNotification)
			.process(new RestProcessor())
			.removeHeaders("*")
			.setHeader(Constants.CONTENT_TYPE, constant(Constants.APP_JSON))
			.marshal().json(JsonLibrary.Jackson)
			.to(NotifyRoutes.hostAndPort + NotifyRoutes.notification + NotifyRoutes.updateNotification + Constants.BRIDGE_ENDPOINT)
			.unmarshal(new ListJacksonDataFormat(NotifcationModel.class))
			.process(exchange ->  {
				List<NotifcationModel> body = (List<NotifcationModel>) exchange.getIn().getBody();
				body.forEach(System.out::println);
			});
	}
}
