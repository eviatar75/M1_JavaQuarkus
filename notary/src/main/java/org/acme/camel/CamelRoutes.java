package org.acme.camel;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.acme.DTO.ContratDeVenteBrokerDTO;
import org.acme.service.OnStart;
import org.acme.service.PDFService;
import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {

    @Inject
    CamelContext camelContext;

    @Inject
    PDFService pdfGenerator;

    //Don't delete
    @Inject
    OnStart verySmartThings;




    @Override
    public void configure() throws Exception {
        camelContext.setTracing(true);
        from("direct:cli")
                .process(new Processoring())
                .to("jms:queue/NotaireToLandService");

        from("jms:queue/responseToNotary")
                .choice()
                .when(header("success").isEqualTo("true"))
                .log(body().toString())
                .bean(pdfGenerator,"creationPDF(${header.ActeID})")
                .otherwise()
                .bean(pdfGenerator,"creationPDF2")
                .log(body().toString());

        from("direct:pdfgenerator")
                .log("${body}");

    }




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
                System.out.println("\n"+"\nL'id de l'acte de vente qui a ete process est:"+exchange.getMessage().getHeader("ActeID"));
            }
        }

    }


