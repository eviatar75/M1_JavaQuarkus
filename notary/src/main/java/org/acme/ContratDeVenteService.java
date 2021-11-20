package org.acme;

import org.acme.domain.ActeDeVente;
import org.acme.domain.Personne;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.Transactional;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.chrono.AbstractChronology;


public class ContratDeVenteService {



    public ActeDeVente getActeById (long id) {
        LocalDate a=LocalDate.of(1997, 7,27);
        LocalDate b=LocalDate.of(1980, 9,3);

        Personne acheteur=new Personne();
        acheteur.setAdresse("rue de Tolbiac");
        acheteur.setCodePostal(75013);
        acheteur.setDateNaissance(a);
        acheteur.setNom("Guennif");
        acheteur.setPrenom("Oualid");
        acheteur.setNumeroRue(90);

        acheteur.persist();

        Personne vendeur=new Personne();
        vendeur.setAdresse("rue de montaubant");
        vendeur.setCodePostal(77540);
        vendeur.setDateNaissance(b);
        vendeur.setNom("Kane");
        vendeur.setPrenom("Smith");
        vendeur.setNumeroRue(5);

        vendeur.persist();

        ActeDeVente acte = new ActeDeVente();
        acte.setUrlPdf("/localpdf");
        acte.setVendeur(vendeur);
        acte.setAcheteur(acheteur);
        acte.setStatuePdf(false);
        acte.setStatutMail(false);

        acte.persist();

        System.out.println(acte.id);



        return ActeDeVente.findById(id);
    }



    public void update(@Positive int idContract, ActeDeVente acte)  throws ActeDeVenteException {
        ActeDeVente a= new ActeDeVente();
        a.setAcheteur(acte.getAcheteur());
        a.setStatuePdf(acte.getStatuePdf());
        a.setVendeur(acte.getVendeur());
        a.setUrlPdf(acte.getUrlPdf());


    }

    public void createTrain(ActeDeVente acte) throws Exception {
        try {



        }catch (Exception e){

            throw new Exception("Impossible d'ajouter l'acte de vente à la base de données");
        }

    }
}
