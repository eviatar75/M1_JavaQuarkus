package org.acme.gateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.acme.DTO.ContratDeVenteBrokerDTO;
import org.acme.DTO.ContratPostDTO;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.pdfbox.pdmodel.PDDocument;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

@ApplicationScoped
public class ActeDeVenteGatewayImpl implements gatewayActeDeVente {

    @Inject
    CamelContext context;

    //@ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.vendorId")
    //Integer vendorId;

    @Override
    public void sendActeDeVente(ContratDeVenteBrokerDTO contrat) {
        try (ProducerTemplate producer = context.createProducerTemplate()) {
            producer.sendBody("direct:cli", contrat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendActeDeVentePDF(PDDocument pdfDocument){
        try (ProducerTemplate producer = context.createProducerTemplate()) {
            producer.sendBody("direct:pdfgenerator", pdfDocument);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveActeDeVente(ContratPostDTO contrat, int id){
        try {
            ObjectMapper obj = new ObjectMapper();
            obj.findAndRegisterModules();
            String jsondto = obj.writeValueAsString(contrat);
            ProducerTemplate producer = context.createProducerTemplate();
            producer.sendBody("file:saveContratPost/queue?fileName="+ id+".dto", jsondto);
         }catch (Exception e) {
            e.printStackTrace();
        }
    }

}