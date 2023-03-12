package com.sk.explorecamel.basic;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RestProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		log.info("Inside processor {} ", exchange.getMessage().getBody());
	}

}
