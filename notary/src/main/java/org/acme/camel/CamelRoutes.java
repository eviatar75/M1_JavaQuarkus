package org.acme.camel;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.acme.DTO.ContratDeVenteBrokerDTO;
import org.acme.service.GeneratePDFService;
import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.spi.DataFormat;
import org.apache.pdfbox.pdmodel.PDDocument;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import java.util.ArrayList;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {

    @Inject
    CamelContext camelContext;

    @Inject
    GeneratePDFService gps;

    @Override
    public void configure() throws Exception {
        camelContext.setTracing(true);
        from("direct:cli")
                .process(new Processoring())
                .to("jms:queue/NotaireToLandService");

        from("jms:queue/responseToNotary")
                .choice()

                .when(((body()).isEqualTo("success")))
                .log(body().toString())
                .process(new ProcessoringPdf())
                .to("file:data/pdf")

                .otherwise()
                .log(body().toString());




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
                ContratDeVenteBrokerDTO echangedto= (ContratDeVenteBrokerDTO) exchange.getMessage().getBody();
                ObjectMapper obj = new ObjectMapper();
                obj.findAndRegisterModules();
                String jsondto = obj.writeValueAsString(echangedto);


                exchange.getMessage().setBody(jsondto);

                String acteID= Integer.toString(echangedto.getId());

                exchange.getMessage().setHeader("ActeID",acteID);
                System.out.println("\n"+"\n"+"LA VIE C'EST DU BUSINESSSSSSS "+"\nL'id de l'acte de vente qui a ete processer est:"+exchange.getMessage().getHeader("ActeID"));
            }
        }

        private class ProcessoringPdf implements Processor{

            @Override
            public void process(Exchange exchange) throws Exception {

                System.out.println("passe bien pour le pdf");
                String text = "test";
                PDDocument document= gps.creationPDF(text);
                exchange.getMessage().setBody(document);
                System.out.println("pdf bien créé");
            }
        }






    }


