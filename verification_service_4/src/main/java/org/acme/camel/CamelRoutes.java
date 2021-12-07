package org.acme.camel;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.acme.DTO.dateAVerifDTO;
import org.acme.service.ServiceVerifDate;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {

    @Inject
    CamelContext context;

    @Inject
    ServiceVerifDate service;


    @Override
    public void configure() {

        context.setTracing(true);
        from("jms:queue/ServiceDeVerification4")
                .process(new Processoring())
                .bean(service,"checkService2")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        exchange.getMessage().setHeader("SERVICE","4");
                    }
                })
                .marshal().json()
                .log("Incoming request : ${body} ")
                .to("jms:queue/responseToLandService");
    }

    private static class Processoring implements Processor {
        @Override
        public void process(Exchange exchange) throws Exception {
            ObjectMapper obj = new ObjectMapper();
            //La ligne qui permet de faire passer les LocalDate
            obj.findAndRegisterModules();
            dateAVerifDTO isUnmarshal = obj.readValue(exchange.getMessage().getBody(String.class), dateAVerifDTO.class);
            exchange.getMessage().setBody(isUnmarshal);

        }
    }

}