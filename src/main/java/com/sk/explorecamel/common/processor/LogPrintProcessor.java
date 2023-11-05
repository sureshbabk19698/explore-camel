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
    public void process(Exchange exchange) {
        Object data = exchange.getProperty(Constants.allData);
        if (data != null) {
            List<UserModel> allData = (List<UserModel>) data;
            log.info(Thread.currentThread().getName() + " Total records {}", allData.size());
        }
    }

}
