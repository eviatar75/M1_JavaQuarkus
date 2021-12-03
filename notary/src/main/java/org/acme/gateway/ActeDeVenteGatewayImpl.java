package org.acme.gateway;

import org.acme.DTO.ContratDeVenteBrokerDTO;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
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

}