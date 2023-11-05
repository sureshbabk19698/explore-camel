package com.sk.explorecamel.users;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import com.sk.explorecamel.model.UserModel;
import com.sk.explorecamel.util.Constants;

@Component
public class SplitAggregatorStrategy implements AggregationStrategy {

	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		UserModel newModel = newExchange.getIn().getBody(UserModel.class);
		if (oldExchange == null) {
			List<UserModel> models = new ArrayList<>();
			models.add(newModel);
			newExchange.setProperty(Constants.allData, models);
			return newExchange;
		}

		List<UserModel> allData = (List<UserModel>) oldExchange.getProperty(Constants.allData);
		allData.add(newModel);
		oldExchange.setProperty(Constants.allData, allData);
		return oldExchange;
	}

}
