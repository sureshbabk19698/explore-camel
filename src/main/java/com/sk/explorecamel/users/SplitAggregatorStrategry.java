package com.sk.explorecamel.users;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import com.sk.explorecamel.model.UserModel;
import com.sk.explorecamel.util.Constants;

@Component
public class SplitAggregatorStrategry implements AggregationStrategy {

	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		if (oldExchange == null) {
			UserModel oldModel = newExchange.getIn().getBody(UserModel.class);
			List<UserModel> models = new ArrayList<>();
			models.add(oldModel);
			newExchange.setProperty(Constants.allDatas, models);
			return newExchange;
		}

		UserModel newModel = newExchange.getIn().getBody(UserModel.class);
		List<UserModel> allDatas = (List<UserModel>) oldExchange.getProperty(Constants.allDatas);
		allDatas.add(newModel);
		oldExchange.setProperty(Constants.allDatas, allDatas);
		return oldExchange;
	}

}
