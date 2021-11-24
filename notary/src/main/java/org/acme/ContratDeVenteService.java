package org.acme;

import io.vertx.core.json.JsonObject;
import org.acme.DTO.ContratDeVenteDTO;
import org.acme.domain.ActeDeVente;
import org.acme.domain.Personne;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Session;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.serializer.JsonbSerializer;
import javax.transaction.Transactional;
import javax.validation.constraints.Positive;
import javax.ws.rs.Produces;
import java.time.LocalDate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


@ApplicationScoped
public class ContratDeVenteService {

    @Inject
    ConnectionFactory connectionFactory;



    public ActeDeVente getActeById (long id) {
        LocalDate a=LocalDate.of(1997, 7,27);
        LocalDate b=LocalDate.of(1980, 9,3);

        Personne acheteur=new Personne();
        acheteur.setId(233234890L);
        acheteur.setAdresse("rue de Tolbiac");
        acheteur.setCodePostal(75013);
        acheteur.setDateNaissance(a);
        acheteur.setNom("Guennif");
        acheteur.setPrenom("Oualid");
        acheteur.setNumeroRue(90);


        acheteur.persist();

        Personne vendeur=new Personne();
        vendeur.setId(233234L);
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

        System.out.println(acte.getId());



        return ActeDeVente.findById(id);
    }



    public void update(@Positive int idContract, ActeDeVente acte)  throws ActeDeVenteException {
        ActeDeVente a= new ActeDeVente();
        a.setAcheteur(acte.getAcheteur());
        a.setStatuePdf(acte.getStatuePdf());
        a.setVendeur(acte.getVendeur());
        a.setUrlPdf(acte.getUrlPdf());


    }
    @Produces
    @Transactional
    public void createActeVente(JsonObject a) throws Exception {
        try {
            System.out.println(a.getJsonObject("acheteur").getString("nom"));


            ActeDeVente   newActe      = new ActeDeVente();
            Personne      acheteur     = new Personne();
            Personne      vendeur      = new Personne();

            acheteur.setId(a.getJsonObject("acheteur").getLong("securite_sociale"));
            acheteur.setNumeroRue(a.getJsonObject("acheteur").getInteger("numero_rue"));
            acheteur.setAdresse(a.getJsonObject("acheteur").getString("adresse"));
            acheteur.setCodePostal(a.getJsonObject("acheteur").getInteger("code_postal"));
            acheteur.setDateNaissance(LocalDate.parse(a.getJsonObject("acheteur").getString("date_naissance")));
            acheteur.setNom(a.getJsonObject("acheteur").getString("nom"));
            acheteur.setPrenom(a.getJsonObject("acheteur").getString("prenom"));

            vendeur.setId(a.getJsonObject("vendeur").getLong("securite_sociale"));
            vendeur.setNumeroRue(a.getJsonObject("vendeur").getInteger("numero_rue"));
            vendeur.setAdresse(a.getJsonObject("vendeur").getString("adresse"));
            vendeur.setCodePostal(a.getJsonObject("vendeur").getInteger("code_postal"));
            vendeur.setDateNaissance(LocalDate.parse(a.getJsonObject("vendeur").getString("date_naissance")));
            vendeur.setNom(a.getJsonObject("vendeur").getString("nom"));
            vendeur.setPrenom(a.getJsonObject("vendeur").getString("prenom"));

            newActe.setStatutMail(false);
            newActe.setStatuePdf(false);
            newActe.setAcheteur(acheteur);
            newActe.setVendeur(vendeur);

            acheteur.persist();
            vendeur.persist();
            newActe.persist();




            System.out.println(acheteur+"\n"+vendeur+"\n"+newActe);

            System.out.println(a.getJsonObject("acheteur"));

            createActeVenteDTO(a,newActe.getId().intValue());




        }catch (Exception e){

            throw new Exception("Impossible d'ajouter l'acte de vente à la base de données");
        }

    }


    public void createActeVenteDTO(JsonObject a, int id) throws Exception {
        try {


            ContratDeVenteDTO dtoActeDeVente = new ContratDeVenteDTO();

            dtoActeDeVente.setId(id);


            dtoActeDeVente.setAcheteur(a.getJsonObject("acheteur").getLong("securite_sociale"));

            dtoActeDeVente.setVendeur(a.getJsonObject("acheteur").getLong("securite_sociale"));


            dtoActeDeVente.setNumeroRue(a.getJsonObject("description_bien").getInteger("numero_rue"));

            dtoActeDeVente.setRue(a.getJsonObject("description_bien").getString("rue"));

            dtoActeDeVente.setCodePostal(a.getJsonObject("description_bien").getInteger("code_postal"));

            dtoActeDeVente.setEtage(a.getJsonObject("description_bien").getInteger("etage"));

            dtoActeDeVente.setChauffage(a.getJsonObject("description_bien").getString("chauffage"));

            dtoActeDeVente.setAmiante(false);

            dtoActeDeVente.setIndicePerfEnergetique(a.getJsonObject("description_bien").getString("indice_perf_energetique"));

            dtoActeDeVente.setIsolation(true);

            dtoActeDeVente.setNbPiece(a.getJsonObject("description_bien").getInteger("nb_piece"));

            dtoActeDeVente.setSuperficie(a.getJsonObject("description_bien").getInteger("superficie"));



            dtoActeDeVente.setDateConstruction(LocalDate.parse(a.getJsonObject("description_bien").getString("date_construction")));

            dtoActeDeVente.setDate_compromis_vente(LocalDate.parse(a.getJsonObject("date").getString("date_compromis_vente")));
            dtoActeDeVente.setDate_signature_acte(LocalDate.parse(a.getJsonObject("date").getString("date_signature_vente")));
            dtoActeDeVente.setPrix(a.getInteger("prix"));



            try (JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)) {


                ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

                String jsonActeDeVente = ow.writeValueAsString(dtoActeDeVente);

                System.out.println(jsonActeDeVente);



                context.createProducer().send(context.createQueue("direct:acte"), jsonActeDeVente);
            }






        }catch (Exception e){

            throw new Exception("Impossible d'ajouter l'acte de vente à la base de données");
        }

    }
}
