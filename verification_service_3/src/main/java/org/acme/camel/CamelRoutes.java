package org.acme.camel;


import org.acme.DTO.PrixDeVenteDTO;
import org.acme.service.PrixLieuDitService;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import javax.inject.Inject;

public class CamelRoutes extends RouteBuilder {

    @Inject
    PrixLieuDitService plds;

    @Inject
    CamelContext context;

    @Override
    public void configure() throws Exception {
        context.addService(plds);
        context.setTracing(true);
        from("jms:queue/ServiceDeVerification3")
                .unmarshal().json(PrixDeVenteDTO.class)
                .log("json unmarshal to prixdeventedto")
                .bean(plds,"checkService3")
                .to("jms:queue/responseToLandService")
        ;

    }
}
