package com.sk.explorecamel.rest;

import com.sk.explorecamel.util.Constants;
import com.sk.explorecamel.util.Constants.NotifyRoutes;
import com.sk.explorecamel.util.Constants.UsersRoutes;
import com.sk.explorecamel.util.Constants.TestRoutes;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApiRoute extends RouteBuilder implements Constants, NotifyRoutes, UsersRoutes, TestRoutes {

    @Value("${server.port}")
    private int port;

    @Override
    public void configure() {
        restConfiguration().component("servlet").host("localhost").port(port).bindingMode(RestBindingMode.json);

        rest(notification)
                .get(getFeedByContent).to(DIRECT + getFeedByContent)
                .get(getFeedByContent_ViaDynamicURI).to(DIRECT + getFeedByContent_ViaDynamicURI)
                .post(updateNotification).to(DIRECT + updateNotification)
                .get(getFeedById + "/{id}").to(DIRECT + getFeedById);

        rest(user)
                .get(findByGender + "/{gender}").to(DIRECT + findByGender);

        rest(test)
                .get(testAggregationStrategy).to(DIRECT + testAggregationStrategy);
    }

}
