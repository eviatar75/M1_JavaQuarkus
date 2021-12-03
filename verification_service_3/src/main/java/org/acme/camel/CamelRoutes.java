package org.acme.camel;


import org.acme.service.PrixLieuDitService;
import org.apache.camel.builder.RouteBuilder;

import javax.inject.Inject;

public class CamelRoutes extends RouteBuilder {

    @Inject
    PrixLieuDitService plds;

    @Override
    public void configure() throws Exception {
        from("jms:queue/ServiceDeVerification1")
                .log("Bien reçu le dto pour le service 3 ")
        ;
    }
}
