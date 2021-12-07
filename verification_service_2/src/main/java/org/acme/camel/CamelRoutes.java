package org.acme.camel;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.acme.DTO.VerifCritereDeBienDTO;
import org.acme.service.VerifCritereDeBien;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
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

    @Inject
    LocalDateSerialBean serializor;


    @Override
    public void configure() throws Exception {

        from("jms:queue/ServiceDeVerification2")
                .process(new Processoring())
                .log("json unmarshal to verifcriteredebiendto")
                .log("voici le contenu du service 2 ${body}")
                .bean(vcdb,"checkService2")
                .log("voici le contenu du service 2 ${body}")
                .bean(serializor,"serial")
                .to("jms:queue/responseToLandService");




    }


    private static class Processoring implements Processor {
        @Override
        public void process(Exchange exchange) throws Exception {
            ObjectMapper obj = new ObjectMapper();
            //La ligne qui permet de faire passer les LocalDate
            obj.findAndRegisterModules();
            VerifCritereDeBienDTO isUnmarshal = obj.readValue( exchange.getMessage().getBody(String.class), VerifCritereDeBienDTO.class);
            System.out.println("\n"+"\n"+"TEST CAMEL ALERT 2 "+"\n"+isUnmarshal);

            exchange.getMessage().setBody(isUnmarshal);
            exchange.getMessage().setHeader("SERVICE","2");


            System.out.println("\n"+"\n"+"TEST CAMEL ALERT 2 "+"\nL'id de l'acte de vente qui a ete processer est:"+exchange.getMessage().getHeader("ActeID"));


        }
    }
}