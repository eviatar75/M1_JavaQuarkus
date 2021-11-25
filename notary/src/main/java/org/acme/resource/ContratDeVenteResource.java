package org.acme.resource;

import org.acme.service.ActeDeVenteException;
import org.acme.service.ContratDeVenteService;
import org.acme.DTO.ContratPostDTO;
import org.acme.domain.ActeDeVente;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.constraints.Positive;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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
    @Produces({MediaType.TEXT_PLAIN})
    @Transactional

    public String generateActe(ContratPostDTO a ) throws Exception {
        System.out.println(a);
        service.createActeVente(a);
        return "valide";
    }

}