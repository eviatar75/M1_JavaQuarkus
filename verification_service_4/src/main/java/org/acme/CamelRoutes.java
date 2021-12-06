package org.acme;

import com.fasterxml.jackson.databind.ObjectMapper;
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


    @Override
    public void configure() {

        context.setTracing(true);
        from("jms:queue/ServiceDeVerification4")
                .process(new Processoring())
                .setBody(simple("success"))
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
            Service4DTO isUnmarshal = obj.readValue(exchange.getMessage().getBody(String.class), Service4DTO.class);
            exchange.getMessage().setBody(isUnmarshal);

        }
    }

}