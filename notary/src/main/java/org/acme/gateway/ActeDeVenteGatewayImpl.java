package org.acme.gateway;

import org.acme.DTO.ContratDeVenteBrokerDTO;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import java.io.IOException;
import java.io.OutputStream;

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
    public void sendActeDeVentePDF(PDDocument pdDocument){
        try (ProducerTemplate producer = context.createProducerTemplate()) {
            producer.sendBody("direct:pdfgenerator", pdDocument);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}