package org.acme.camel;


import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {



    @Inject
    CamelContext camelContext;


    @Override
    public void configure() throws Exception {
        camelContext.setTracing(true);
        from("direct:cli")
                .marshal().json()//, "onBookedResponseReceived"
                .to("jms:NotaireToLandServicequeue?exchangePattern=InOut")
                .choice()
                .when(header("success").isEqualTo(false))
                .setBody(simple("L'acte de vente ne peut être validé "))
                .otherwise()
                .log("à faire");


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





    }
}

