package com.sk.explorecamel.common.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LogPrintProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		log.info("Printing data from processor {}", exchange.getIn().getBody());
	}

}
