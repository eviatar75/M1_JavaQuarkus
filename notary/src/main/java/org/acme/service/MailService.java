package org.acme.service;


import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.File;

@ApplicationScoped
public class MailService {

    @Inject Mailer mailer;




    public void sendEmail(String destinaire, String path, String name) {
        File fichier = new File(path);
        mailer.send(
                Mail.withText(destinaire,
                        "Acte de vente","Bonjour M,Mme "+name+",\n\nNous revenons vers vous afin de vous informer que l'étude de votre contrat de vente est désormais terminé.\n\nVous retrouverez en pièce-jointe un pdf récapitulatif de votre Acte de vente.\n\n\nCordialement,\n\nLe notaire de Tolbiac"
                ).addAttachment("Acte_de_vente.pdf",fichier,"pdf")
        );
    }

}
