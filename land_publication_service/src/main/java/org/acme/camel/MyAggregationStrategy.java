package org.acme.camel;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.apache.camel.Message;

import java.util.Objects;

public class MyAggregationStrategy implements AggregationStrategy {
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        Message newIn = newExchange.getIn();
        String oldBody = oldExchange.getIn().getBody(String.class);
        String newBody = newIn.getBody(String.class);

        if (oldBody.equals("")){
            newIn.setBody(newBody);
        }
        else if (oldBody.equals("success") && newBody.equals("success")){
            newIn.setBody("success");
        }
        else if (!(oldBody.equals("success")) && !(newBody.equals("success"))){
            newIn.setBody(oldBody + newBody);
        }

        else if (!(oldBody.equals("success"))){
            newIn.setBody(oldBody);
        }

        else {
            newIn.setBody(newBody);
        }

        return newExchange;
    }
}