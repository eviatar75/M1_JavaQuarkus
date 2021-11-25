package org.acme;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import org.acme.DTO.ContratDeVenteBrokerDTO;
import org.acme.DTO.ContratPostDTO;
import org.acme.domain.ActeDeVente;
import org.acme.domain.Personne;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Session;
import javax.json.bind.JsonbBuilder;
import javax.transaction.Transactional;
import javax.validation.constraints.Positive;
import javax.ws.rs.Produces;
import java.io.Serializable;
import java.time.LocalDate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.spi.DataFormat;


@ApplicationScoped
public class ContratDeVenteService implements Serializable {

    @Inject
    ConnectionFactory connectionFactory;


    public ActeDeVente getActeById (long id) {
        System.out.println(ActeDeVente.findById(id));
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
    public void createActeVente(ContratPostDTO a) throws Exception {
        try {
            ActeDeVente   newActe      = new ActeDeVente();
            Personne      acheteur     = new Personne();
            Personne      vendeur      = new Personne();

            LocalDate acheteur_date_naissance=LocalDate.parse(a.getAcheteur().getDate_naissance());
            LocalDate vendeur_date_naissance=LocalDate.parse(a.getVendeur().getDate_naissance());

            acheteur.setId(a.getAcheteur().getSecurite_sociale());
            acheteur.setNumeroRue(a.getAcheteur().getNumero_rue());
            acheteur.setAdresse(a.getAcheteur().getAdresse());
            acheteur.setCodePostal(a.getAcheteur().getCode_postal());
            acheteur.setDateNaissance(acheteur_date_naissance);
            acheteur.setNom(a.getAcheteur().getNom());
            acheteur.setPrenom(a.getAcheteur().getPrenom());

            vendeur.setId( a.getVendeur().getSecurite_sociale());
            vendeur.setNumeroRue(a.getVendeur().getNumero_rue());
            vendeur.setAdresse(a.getVendeur().getAdresse());
            vendeur.setCodePostal(a.getVendeur().getCode_postal());
            vendeur.setDateNaissance(vendeur_date_naissance);
            vendeur.setNom(a.getVendeur().getNom());
            vendeur.setPrenom(a.getVendeur().getPrenom());

            newActe.setStatutMail(false);
            newActe.setStatuePdf(false);
            newActe.setAcheteur(acheteur);
            newActe.setVendeur(vendeur);

            acheteur.persist();
            vendeur.persist();
            newActe.persist();




            createActeVenteDTO(a,newActe.getId().intValue());


        }catch (Exception e){

            throw new Exception("Impossible d'ajouter l'acte de vente à la base de données");
        }

    }


    public void createActeVenteDTO(ContratPostDTO a, int id)  throws Exception {
        try {


            ContratDeVenteBrokerDTO dtoActeDeVente = new ContratDeVenteBrokerDTO();

            dtoActeDeVente.setId(id);
            dtoActeDeVente.setAcheteur(a.getAcheteur().getSecurite_sociale());
            dtoActeDeVente.setVendeur(a.getVendeur().getSecurite_sociale());




            dtoActeDeVente.setNumeroRue(a.getNumero_rue());
            dtoActeDeVente.setRue(a.getRue());
            dtoActeDeVente.setCodePostal(a.getCode_postal());
            dtoActeDeVente.setEtage(a.getEtage());
            dtoActeDeVente.setChauffage(a.getChauffage());
            dtoActeDeVente.setAmiante(a.isAmiante());
            dtoActeDeVente.setIndicePerfEnergetique(a.getIndice_perf_energetique());
            dtoActeDeVente.setIsolation(a.isIsolation());
            dtoActeDeVente.setNbPiece(a.getNb_piece());
            dtoActeDeVente.setSuperficie(a.getSuperficie());
            dtoActeDeVente.setDateConstruction(a.getDate_construction());
            dtoActeDeVente.setDate_compromis_vente(a.getDate_compromis_vente());
            dtoActeDeVente.setDate_signature_acte(a.getDate_signature_vente());
            dtoActeDeVente.setPrix(a.getPrix());






            try (JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)) {
                ObjectMapper mapper = new ObjectMapper();
                mapper.findAndRegisterModules();
                AnnotationIntrospector introspector = new JaxbAnnotationIntrospector(mapper.getTypeFactory());
                mapper.setAnnotationIntrospector(introspector);


                System.out.println("\n\nAAAAAAAAA_\neeeeeeee\neeeeeeee");
                // Printing JSON
                String result = mapper.writeValueAsString(dtoActeDeVente);
                System.out.println(result);

                // Parsing JSON
                // Parsing JSON
                //Recipe retr = mapper.readValue(result, Recipe.class);

                //System.out.println("Title   : " + retr.getTitle());
                //System.out.println("Duration: " + retr.getDuration());


                context.createProducer().send(context.createQueue("direct:acteVenteUNVERIFIED"), result);
            }






        }catch (Exception e){

            throw new Exception("Impossible d'ajouter l'acte de vente à la base de données");
        }

    }
}
