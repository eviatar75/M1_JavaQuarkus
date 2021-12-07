package org.acme.service;


import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.File;

@ApplicationScoped
public class MailService {

    @Inject Mailer mailer;




    public void sendEmail(String destinaire, String path) {
        File fichier = new File(path);
        mailer.send(
                Mail.withText(destinaire,
                        "Acte de vente",""
                ).addAttachment("Acte_de_vente.pdf",fichier,"pdf")
        );
    }

}
