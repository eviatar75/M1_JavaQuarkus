package org.acme.camel;


import org.acme.DTO.PrixDeVenteDTO;
import org.acme.service.PrixLieuDitService;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {

    @Inject
    PrixLieuDitService plds;

    @Override
    public void configure() throws Exception {
        from("jms:queue/ServiceDeVerification3")
                .unmarshal().json(PrixDeVenteDTO.class)
                .log("json unmarshal to prixdeventedto")
                .bean(plds,"checkService3")
                .log("voici le contenu du service 3 ${body}")
                .to("jms:queue/responseToLandService")
        ;

    }
}
