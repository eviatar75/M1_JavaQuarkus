package org.acme.camel;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
//import org.acme.service.VerifCritereDeBien;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.*;
import java.time.LocalDate;

@ApplicationScoped
public class CamelRoutes implements Runnable {

    @Inject
    ConnectionFactory connectionFactory;

    boolean running;

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
            /**
             VerifCritereDeBien a = new VerifCritereDeBien();
             a.VerifCritereSuperficie(3);
             System.out.println("e");
             **/
        }

    }
}