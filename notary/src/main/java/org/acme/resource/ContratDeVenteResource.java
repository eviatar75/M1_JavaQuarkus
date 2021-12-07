package org.acme.resource;

import io.smallrye.mutiny.Uni;
import org.acme.service.ContratDeVenteService;
import org.acme.DTO.ContratPostDTO;
import org.acme.service.PDFService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.transaction.Transactional;
import javax.validation.constraints.Positive;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;

@ApplicationScoped
@Path("/ActeDeVente")
public class ContratDeVenteResource {
    @Inject
    ContratDeVenteService service;
    @Inject
    PDFService pdfService;


    @GET
    @Path("{contratActeDeVente}")
    @Produces({MediaType.APPLICATION_OCTET_STREAM})
    @Transactional
    public Uni<Response> getActeVente(@PathParam("contratActeDeVente") @Positive int idContract ) {
        try {
            File pdf= pdfService.recuperationPdf(idContract);
            Response.ResponseBuilder response = Response.ok((Object) pdf);
            String name="Recap_Acte_de_vente_"+idContract+".pdf";
            response.header("Content-Disposition", "attachment;filename=" + name);
            Uni<Response> outputPdf = Uni.createFrom().item(response.build());
            return outputPdf;

        } catch (Exception e) {
            throw new NotFoundException("Nous n'avons trouvé aucun acte de vente pour cette id"+idContract);

        }


    }




    @POST
    @Path("{generationActeDeVente}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    @Transactional

    public String generateActe(ContratPostDTO a ) throws Exception {
        System.out.println(a);
        try {
            service.createActeVente(a);
        }catch (Exception e){
            return "La demande n'a pas pu être envoyé";
        }
        return "La demande est valide";
    }

}