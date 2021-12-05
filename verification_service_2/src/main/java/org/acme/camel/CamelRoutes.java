package org.acme.camel;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.acme.DTO.VerifCritereDeBienDTO;
import org.acme.service.VerifCritereDeBien;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
//import org.acme.service.VerifCritereDeBien;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.*;
import java.time.LocalDate;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {
    @Inject
    VerifCritereDeBien vcdb;

    @Inject
    CamelContext context;

    @Override
    public void configure() throws Exception {
        context.addService(vcdb);
        context.setTracing(true);
        from("jms:queue/ServiceDeVerification2")
                .unmarshal().json(VerifCritereDeBienDTO.class)
                .log("json unmarshal to verifcriteredebiendto")
                .bean(vcdb,"checkService2")
                .to("jms:queue/responseToLandService")
        ;

    }
}