package org.acme;

import io.quarkus.resteasy.reactive.jsonb.runtime.serialisers.JsonbMessageBodyReader;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.acme.DTO.ContratPostDTO;
import org.acme.domain.ActeDeVente;
import org.acme.domain.Personne;
import org.jboss.resteasy.reactive.common.providers.serialisers.jsonp.JsonObjectHandler;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.constraints.Positive;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;

@ApplicationScoped
@Path("/ActeDeVente")
public class ContratDeVenteResource {
    @Inject
    ContratDeVenteService service;

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
    public void generateActe( ContratPostDTO a ) throws Exception {
        System.out.println(a);

        service.createActeVente(a);


    }

}