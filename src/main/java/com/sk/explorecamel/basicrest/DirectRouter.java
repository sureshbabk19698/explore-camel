package com.sk.explorecamel.basicrest;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.component.jackson.ListJacksonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sk.explorecamel.model.NotifcationModel;
import com.sk.explorecamel.util.Constants;
import com.sk.explorecamel.util.Constants.NotifyRoutes;

@Component
public class DirectRouter extends RouteBuilder {

	private String getFeedByContentDynamicUrl = NotifyRoutes.hostAndPort + NotifyRoutes.notification
			+ NotifyRoutes.getFeedByContent + "?keyword=${header.keyword}" + Constants.AND_BRIDGE_ENDPOINT;
	private String getFeedByContentUrl = NotifyRoutes.hostAndPort + NotifyRoutes.notification
			+ NotifyRoutes.getFeedByContent + Constants.QUES_BRIDGE_ENDPOINT;
	private String updateNotification = NotifyRoutes.hostAndPort + NotifyRoutes.notification
			+ NotifyRoutes.updateNotification + Constants.QUES_BRIDGE_ENDPOINT;
	private String getFeedByIdUrl = NotifyRoutes.hostAndPort + NotifyRoutes.notification
			+ NotifyRoutes.getFeedById + "/${header.id}" +Constants.QUES_BRIDGE_ENDPOINT;

	@Autowired
	private LogPrintProcessor logPrintProcessor;
	
	@Override
	public void configure() throws Exception {

		// POST - send and receive data in JSON
		from(Constants.DIRECT + NotifyRoutes.updateNotification)
			.routeId(NotifyRoutes.updateNotification)
			.removeHeaders(Constants.camelHttp)
			.setHeader(Constants.ACCEPT, constant(Constants.APP_JSON))
			.setHeader(Constants.CONTENT_TYPE, constant(Constants.APP_JSON))
			.marshal().json(JsonLibrary.Jackson)
			.to(updateNotification) // Static EndPoint
			.unmarshal(new ListJacksonDataFormat(NotifcationModel.class))
			.process(logPrintProcessor);
		
		// GET - via Dynamic Endpoint
		from(Constants.DIRECT + NotifyRoutes.getFeedByContent_ViaDynamicURI)
			.routeId("Dynamic-->getFeedByContent")
			.removeHeaders(Constants.camelHttp)
			.setHeader(Exchange.HTTP_METHOD, constant("GET"))
			.setHeader(Constants.ACCEPT, constant(Constants.APP_JSON))
			.process(exchange-> {
					exchange.getIn().setHeader("keyword", exchange.getIn().getHeader("keyword", String.class));
			})
			.log("Searching for keyword ${header.keyword}")
			.toD(getFeedByContentDynamicUrl) // Dynamic EndPoint (i.e) ?keyword=${header.keyword}
			.unmarshal(new ListJacksonDataFormat(NotifcationModel.class))
			.process(logPrintProcessor);
		
		// GET - via Static Endpoint
		from(Constants.DIRECT + NotifyRoutes.getFeedByContent)
			.routeId("Static-->getFeedByContent")
			.removeHeaders(Constants.camelHttp)
			.process(exchange-> {
				exchange.getIn().setHeader("keyword", exchange.getIn().getHeader("keyword", String.class));
			})
			.setHeader(Exchange.HTTP_METHOD, constant("GET"))
			.setHeader(Exchange.HTTP_QUERY, simple("keyword=${header.keyword}"))
			.setHeader(Constants.ACCEPT, constant(Constants.APP_JSON))
			.log("Searching for keyword ${header.keyword}")
			.to(getFeedByContentUrl)
			.unmarshal(new ListJacksonDataFormat(NotifcationModel.class))
			.process(logPrintProcessor);
		
		// GET - via Static Endpoint
		from(Constants.DIRECT + NotifyRoutes.getFeedById)
			.routeId("Static-->getFeedById")
			.removeHeaders(Constants.camelHttp)
			.setHeader(Exchange.HTTP_METHOD, constant("GET"))
			.setHeader(Constants.ACCEPT, constant(Constants.APP_JSON))
			.process(exchange-> {
					exchange.getIn().setHeader("id", exchange.getIn().getHeader("id", Long.class));
			})
			.log("Find by id : ${header.id}")
			.toD(getFeedByIdUrl)
			.unmarshal(new JacksonDataFormat(NotifcationModel.class))
			.process(logPrintProcessor);
	}
}
