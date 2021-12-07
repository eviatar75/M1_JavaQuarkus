package org.acme.service;

import org.acme.DTO.ContratDeVenteBrokerDTO;
import org.acme.DTO.ContratPostDTO;
import org.acme.domain.ActeDeVente;
import org.acme.domain.Personne;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.transaction.Transactional;
import javax.validation.constraints.Positive;
import javax.ws.rs.Produces;
import java.io.Serializable;

import org.acme.gateway.ActeDeVenteGatewayImpl;


@ApplicationScoped
public class ContratDeVenteService implements Serializable {

    @Inject
    ConnectionFactory connectionFactory;
    @Inject
    ActeDeVenteGatewayImpl gateway;


    public ActeDeVente getActeById (long id) {
        System.out.println(ActeDeVente.findById(id));
        return ActeDeVente.findById(id);
    }



    public void update(@Positive int idContract, ActeDeVente acte)  throws ActeDeVenteException {

    }


    @Produces
    @Transactional
    public void createActeVente(ContratPostDTO a) throws Exception {
        try {
            ActeDeVente   newActe      = new ActeDeVente();
            Personne      acheteur     = Personne.personneFromDto(a.getAcheteur());
            Personne      vendeur      = Personne.personneFromDto(a.getVendeur());

            newActe.setStatutMail(false);
            newActe.setStatuePdf(false);
            newActe.setAcheteur(acheteur);
            newActe.setVendeur(vendeur);

            acheteur.persist();
            vendeur.persist();
            newActe.persist();

            createActeVenteDTO(a,newActe.getId().intValue());
            gateway.saveActeDeVente(a,newActe.getId().intValue());



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

            dtoActeDeVente.setNumero_rue(a.getNumero_rue());
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
            dtoActeDeVente.setPorte(a.getPorte());

            gateway.sendActeDeVente(dtoActeDeVente);


        }catch (Exception e){

            throw new Exception("Impossible d'ajouter l'acte de vente à la base de données");
        }

    }
}
