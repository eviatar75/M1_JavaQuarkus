package org.acme.camel;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.apache.camel.Message;

import java.util.Objects;

import static org.apache.camel.builder.Builder.body;
import static org.apache.camel.builder.Builder.simple;

public class MyAggregationStrategy implements AggregationStrategy {
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        Message newIn = newExchange.getIn();

        if (oldExchange==null){
            newIn.setBody(simple("unsuccess"));
            return newExchange;
        }


        String oldBody = oldExchange.getIn().getBody(String.class);
        String newBody = newIn.getBody(String.class);


        if (oldBody.equals("success") && newBody.equals("success")){
            newIn.setBody(simple("success"));
        }
        else if (!(oldBody.equals("success")) && !(newBody.equals("success"))){
            newIn.setBody(simple(oldBody + newBody));
        }
        else if (!(oldBody.equals("success"))){
            newIn.setBody(simple(oldBody));
        }
        else {
            newIn.setBody(simple(newBody));
        }
        return newExchange;
    }
}