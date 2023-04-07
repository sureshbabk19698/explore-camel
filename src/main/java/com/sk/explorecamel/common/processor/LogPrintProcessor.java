package com.sk.explorecamel.common.processor;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.sk.explorecamel.model.UserModel;
import com.sk.explorecamel.util.Constants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LogPrintProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		List<UserModel> allDatas = (List<UserModel>) exchange.getProperty(Constants.allDatas);
		log.info(Thread.currentThread().getName() + " Total records {}", allDatas.size());
	}

}
