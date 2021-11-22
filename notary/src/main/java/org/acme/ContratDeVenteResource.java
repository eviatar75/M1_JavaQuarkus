package org.acme;

import io.quarkus.resteasy.reactive.jsonb.runtime.serialisers.JsonbMessageBodyReader;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.acme.domain.ActeDeVente;
import org.acme.domain.Personne;
import org.jboss.resteasy.reactive.common.providers.serialisers.jsonp.JsonObjectHandler;

import javax.transaction.Transactional;
import javax.validation.constraints.Positive;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;


@Path("/ActeDeVente")
public class ContratDeVenteResource {

    ContratDeVenteService service = new ContratDeVenteService();

    @GET
    @Path("{contratActeDeVente}")
    @Produces({MediaType.APPLICATION_JSON})
    @Transactional
    public ActeDeVente getActeVente(@Positive @PathParam("contratActeDeVente") int idContract ) {
        return service.getActeById(idContract);
    }


    @PUT
    @Path("{modifActeDeVente}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces
    public void updateActeVente (@Positive @PathParam("modifActeDeVente") int idContract, ActeDeVente acte) {
        try {

            service.update(idContract,acte);
        }
        catch (ActeDeVenteException e ){
            throw new WebApplicationException("La mise à jour de l'acte de vente ne s'est pas déroulé",403);
        }
    }


    @POST
    @Path("{generationActeDeVente}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces
    @Transactional
    public void createActeVente(@PathParam("generationActeDeVente") JsonObject a ) throws Exception {


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
    }

}