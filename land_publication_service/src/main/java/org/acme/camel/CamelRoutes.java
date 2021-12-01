package org.acme.camel;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.acme.DTO.ContratDeVenteBrokerDTO;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {

    @Inject
    CamelContext camelContext;

    @Override
    public void configure() throws Exception {
        camelContext.setTracing(true);
        from("jms:queue/NotaireToLandServicequeue")
                .process(new Processoring())
                .log("Incoming request : ${body} ")
                .to("direct:unmarshalobject");

    }


/*
        from("direct:cli")
                .marshal().json()//, "onBookedResponseReceived"
                .to("jms:NotaireToLandServicequeue?exchangePattern=InOut")//
                .choice()
                .when(header("success").isEqualTo(false))
                .setBody(simple("not enough quota for this vendor"))
                .bean(eCommerce, "showErrorMessage").stop()
                .otherwise()
                .unmarshal().json(Booking.class)
                .bean(BookingResponseHandler)
                .log("response received ${in.body}")
                .bean(ticketingService, "fillTicketsWithCustomerInformations")
                .split(body())
                .marshal().json(ETicket.class)
                .to("jms:" + jmsPrefix + "ticket?exchangePattern=InOut")
                .choice()
                .when(header("success").isEqualTo(false))
                .bean(eCommerce, "showErrorMessage").stop()
                .otherwise()
                .bean(ticketingService, "notifyCreatedTicket");


 */

    private static class Processoring implements Processor {
        @Override
        public void process(Exchange exchange) throws Exception {
            ObjectMapper obj = new ObjectMapper();
            //La ligne qui permet de faire passer les LocalDate
            obj.findAndRegisterModules();
            ContratDeVenteBrokerDTO isUnmarshal = obj.readValue( (String) exchange.getMessage().getBody().toString(), ContratDeVenteBrokerDTO.class);

            System.out.println("\n"+"\n"+"TEST CAMEL ALERT 2 "+"\n"+isUnmarshal);

            exchange.getMessage().setBody(isUnmarshal);

            String acteID= Integer.toString(isUnmarshal.getId());
            System.out.println("\n"+"\n"+"TEST CAMEL ALERT 2 "+"\nL'id de l'acte de vente qui a ete processer est:"+exchange.getMessage().getHeader("ActeID"));


        }
        }






    }


