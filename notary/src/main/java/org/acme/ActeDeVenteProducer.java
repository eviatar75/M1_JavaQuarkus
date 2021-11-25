package org.acme;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import org.acme.DTO.ContratDeVenteBrokerDTO;
import org.acme.DTO.ContratPostDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Session;


@ApplicationScoped
public class ActeDeVenteProducer {

    @Inject
    ConnectionFactory connectionFactory;


    public void createActeVenteDTO(ContratDeVenteBrokerDTO brokerDTO, int id) throws Exception {


        try {

            try (
                    JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)) {
                ObjectMapper mapper = new ObjectMapper();
                mapper.findAndRegisterModules();
                AnnotationIntrospector introspector = new JaxbAnnotationIntrospector(mapper.getTypeFactory());
                mapper.setAnnotationIntrospector(introspector);


                System.out.println("\n\nAAAAAAAAA_\neeeeeeee\neeeeeeee");
                // Printing JSON
                String result = mapper.writeValueAsString(brokerDTO);
                System.out.println(result);

                // Parsing JSON
                // Parsing JSON
                //Recipe retr = mapper.readValue(result, Recipe.class);

                //System.out.println("Title   : " + retr.getTitle());
                //System.out.println("Duration: " + retr.getDuration());




                context.createProducer().send(context.createQueue("direct:acteVenteUNVERIFIED"), result);
            }


        } catch (Exception e) {

            throw new Exception("Impossible d'envoyer l'acte de vente pour une vérification");
        }
    }

}


