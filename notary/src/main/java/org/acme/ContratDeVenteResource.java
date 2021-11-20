package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.acme.domain.ActeDeVente;
import org.acme.domain.Personne;

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
    public ActeDeVente contract(@Positive @PathParam("contratActeDeVente") int idContract ) {


        return service.getActeById(idContract);
    }


    @PUT
    @Path("{modifActeDeVente}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void updateTrain (@Positive @PathParam("modifActeDeVente") int idContract, ActeDeVente acte) {
        try {

            service.update(idContract,acte);
        }
        catch (ActeDeVenteException e ){
            throw new WebApplicationException("La mise à jour de l'acte de vente ne s'est pas déroulé",403);
        }
    }


    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void createTrain(ActeDeVente acte) throws Exception {
        service.createTrain(acte);
    }

}