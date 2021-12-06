package org.acme.camel;

import org.acme.DTO.ContratDTO;
import org.acme.service.CheckerServiceImpl;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {

    @Inject
    CamelContext context;

    @Inject
    CheckerServiceImpl service;

    @Override
    public void configure() throws Exception {
        context.addService(service);
        context.setTracing(true);
        from("jms:queue/ServiceDeVerification1")
                .unmarshal().json(ContratDTO.class)
                .bean(service,"Checker")
                .marshal().json()
                .to("jms:queue/responseToLandService");
    }
}
