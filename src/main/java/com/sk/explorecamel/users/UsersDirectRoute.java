package com.sk.explorecamel.users;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.ListJacksonDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sk.explorecamel.common.processor.LogPrintProcessor;
import com.sk.explorecamel.model.UserModel;
import com.sk.explorecamel.util.Constants;
import com.sk.explorecamel.util.Constants.UsersRoutes;

@Component
public class UsersDirectRoute extends RouteBuilder {

	private final String basePath = UsersRoutes.hostAndPort + UsersRoutes.user;
	private String findByGenderUrl = basePath + UsersRoutes.findByGender
			+ "/${header.gender}" + Constants.QUES_BRIDGE_ENDPOINT;

	@Autowired
	private LogPrintProcessor logPrintProcessor;

	@Override
	public void configure() throws Exception {

		from(Constants.DIRECT + UsersRoutes.findByGender)
				.routeId(UsersRoutes.findByGender)
				.removeHeader(Constants.camelHttp)
				.setHeader(Exchange.HTTP_METHOD, constant("GET"))
				.setHeader(Constants.ACCEPT, constant(Constants.APP_JSON))
				.process(exchange -> exchange.getIn().setHeader("gender",
						exchange.getIn().getHeader("gender", String.class)))
				.log("Find by gender : ${header.gender}")
				.toD(findByGenderUrl)
				.unmarshal(new ListJacksonDataFormat(UserModel.class))
				.split(body(), new SplitAggregatorStrategy())
				.streaming()
					.parallelProcessing()
					.log("${body}")
				.end()
				.bean(logPrintProcessor);

	}

}
