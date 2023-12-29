package com.sk.explorecamel.test;

import com.sk.explorecamel.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TestAggregationStrategyRoute extends RouteBuilder implements Constants, Constants.TestRoutes {

    @Override
    public void configure() {

        from(DIRECT + testAggregationStrategy)
                .enrich("direct:enrich", new MyAggregationStrategy())
                .to("direct:processEnriched")
                .end();

        from("direct:enrich")
                .setBody().constant("Enriched Data");

        from("direct:processEnriched")
                .log("Processed: ${body}");

    }
}

@Slf4j
class MyAggregationStrategy implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        log.info("Inside Aggregate Strategy");
        if (oldExchange == null) {
            // First time, return the enriched message as is
            log.info("Inside OldExchange");
            return newExchange;
        } else {
            // Combine the original and enriched messages
            log.info("Inside NewExchange");
            String originalBody = oldExchange.getIn().getBody(String.class);
            String enrichedBody = newExchange.getIn().getBody(String.class);
            String combinedBody = originalBody + " - " + enrichedBody;

            oldExchange.getIn().setBody(combinedBody);
            return oldExchange;
        }
    }

}


