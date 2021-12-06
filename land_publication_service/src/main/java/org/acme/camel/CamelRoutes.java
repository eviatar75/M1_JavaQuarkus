package org.acme.camel;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.acme.DTO.ContratDeVenteBrokerDTO;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {

    @Inject
    CamelContext camelContext;

    @Inject
    TranslatorBean translator;

    @Inject
    LocalDateSerialBean serializer;


    @Override
    public void configure() throws Exception {

        String result;

        //Ici on reçoit les informations complétes de notre acte de vente
        from("jms:queue/NotaireToLandService")
                //translate to dto
                .process(new Processoring())
                //translate Big dto to a list of dto for our 4 services
                .bean(translator, "extractTokens")
                //split
                .split(body())
                //log
                .log("DEPART VERS LES SERVICES: SPLITTED request acte ID : ${header.ActeID} ")
                .log("DEPART VERS LES SERVICES:  SPLITTED request body : ${body} ")
                .to("direct:splitqueue");




        from("direct:splitqueue")

                .choice()
                .when(simple("${body} is 'org.acme.DTO.Service1DTO'"))
                .bean(serializer,"serialDTO")
                //.log("Incoming request dto1 : ${body} ")
                .to("jms:queue/ServiceDeVerification1")

                .when(simple("${body} is 'org.acme.DTO.Service2DTO'"))
                .bean(serializer,"serialDTO")
                //.log("Incoming request dto2 : ${body} ")
                .to("jms:queue/ServiceDeVerification2")


                .when(simple("${body} is 'org.acme.DTO.Service3DTO'"))
                .bean(serializer,"serialDTO")
                //.log("Incoming request dto3 : ${body} ")
                .to("jms:queue/ServiceDeVerification3")

                .when(simple("${body} is 'org.acme.DTO.Service4DTO'"))
                .bean(serializer,"serialDTO")
                //.log("Incoming request dto4 : ${body} ")
                .to("jms:queue/ServiceDeVerification4");


        from("jms:queue/responseToLandService")
                .log("le body reçu par l'aggragator: ${body} ")
                .log("les hearders reçu par l'agrregator : ${headers} ")
                .aggregate(header("ActeID"),new MyAggregationStrategy()).completionSize(4).completionTimeout("5000").discardOnCompletionTimeout()
                .log("le body crée par l'aggragator après l'aggregation: ${body} ")
                .choice()
                .when(body().isEqualTo("success"))
                .setHeader("success",simple("true"))
                .log("les hearders crée par l'aggregator : ${headers} ")
                .to("jms:queue/responseToNotary")
                .otherwise()
                .setHeader("success",simple("false"))
                .log("les hearders crée par l'aggregator en false : ${headers} ")
                .to("jms:queue/responseToNotary");
    }


    private static class Processoring implements Processor {
        @Override
        public void process(Exchange exchange) throws Exception {
            ObjectMapper obj = new ObjectMapper();

            //La ligne qui permet de faire passer les LocalDate
            obj.findAndRegisterModules();

            ContratDeVenteBrokerDTO isUnmarshal = obj.readValue( exchange.getMessage().getBody(String.class), ContratDeVenteBrokerDTO.class);

            System.out.println("\n"+"\n"+"TEST CAMEL ALERT 2 "+"\n"+isUnmarshal);

            exchange.getMessage().setBody(isUnmarshal);

            String acteID= Integer.toString(isUnmarshal.getId());


            System.out.println("\n"+"\n"+"TEST CAMEL ALERT 2 "+"\nL'id de l'acte de vente qui a ete processer est:"+exchange.getMessage().getHeader("ActeID"));


        }
        }






    }


