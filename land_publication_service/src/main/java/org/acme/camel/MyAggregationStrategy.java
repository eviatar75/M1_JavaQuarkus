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
            return newExchange;
        }


        String oldBody = oldExchange.getIn().getBody(String.class);
        System.out.println(oldBody+"\n");
        String newBody = newIn.getBody(String.class);
        System.out.println(newBody+"\n");


        if (oldBody.trim().equals("\"success\"") && newBody.trim().equals("\"success\"")
                || (oldBody.trim().equals("success") && newBody.trim().equals("success"))
                ||(oldBody.trim().equals("\"success\"") && newBody.trim().equals("success"))
                ||(oldBody.trim().equals("success") && newBody.trim().equals("\"success\""))){
            System.out.println("success/success\n");
            newIn.setBody("success");
        }
        else if ((!oldBody.trim().equals("\"success\"")&&!(oldBody.trim().equals("success") ))&& ((!newBody.trim().equals("\"success\"")&&!(newBody.trim().equals("success") )))){
            System.out.println("unsuccess/unsuccess\n");
            newIn.setBody(oldBody + newBody);
        }
        else if (!(oldBody.trim().equals("\"success\""))&&!(oldBody.trim().equals("success"))){
            System.out.println("unsuccess/success\n");
            newIn.setBody(oldBody);
        }
        else {
            System.out.println("success/unsuccess\n");
            newIn.setBody(newBody);
        }
        return newExchange;
    }
}