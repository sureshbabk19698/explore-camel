package com.sk.explorecamel.basic;

import java.time.LocalDateTime;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class BasicRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		from("timer:first-timer")
		.setBody().constant("Hello")
//		.bean(BasicRouter.class, "printState(After from)")
//		.log(printState("After from"))
//	    .transform().constant("Time now is " + LocalDateTime.now())
	    .process(new BasicProcessor())
		.to("log:first-timer");
	}

	public String printState(String state) {
		return state + "-" + LocalDateTime.now();
	}

}
