package org.acme.domain;

import org.acme.DTO.dateAVerifDTO;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.*;
import java.time.LocalDate;

@ApplicationScoped
public class dateVerifier implements Runnable {

    @Inject
    ConnectionFactory connectionFactory;

    //indique si la classe est configurée pour recevoir les messages en boucle
    boolean running;

    //cette méthode démarre un nouveau thread exécutant l'instance en cours, jusqu'à ce que la variable running soit false.
    void onStart(@Observes StartupEvent ev) {
        running = true;
        new Thread(this).start();
    }


    void onStop(@Observes ShutdownEvent ev) {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            try (JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)) {
                //reçoit un message à partir de la queue queue/prices
                Message mess = context.createConsumer(context.createQueue("queue/service4")).receive();
                //converti ce message en int

                int idActeDeVente;
                LocalDate date_compromis_vente  ;
                LocalDate date_signature_vente;

                //dateAVerifDTO d1 = Integer.parseInt((String)mess.getBody(String.class));
                //affiche le résultat dans la console
                //$-System.out.println("from the consumer: " + price);

            }
        }
    }
}
