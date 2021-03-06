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
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Produces;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.acme.gateway.ActeDeVenteGatewayImpl;


@ApplicationScoped
public class ContratDeVenteService implements Serializable {

    @Inject
    ConnectionFactory connectionFactory;
    @Inject
    ActeDeVenteGatewayImpl gateway;


    public ActeDeVente getActeById (long id) throws NotFoundException{
        System.out.println(ActeDeVente.findById(id));
        ActeDeVente acte= ActeDeVente.findById(id);
        if (acte==null){
            throw new NotFoundException("Acte "+id+" introuvable");
        }
        return acte;
    }



    public void update(@Positive int idContract, ActeDeVente acte)  throws ActeDeVenteException {

    }


    @Produces
    @Transactional
    public void createActeVente(ContratPostDTO a) throws Exception {
        try {
            ActeDeVente   newActe      = new ActeDeVente();
            Personne acheteur = Personne.personneFromDto(a.getAcheteur());
            Personne vendeur = Personne.personneFromDto(a.getVendeur());


            if (Personne.findById(a.getAcheteur().getSecurite_sociale())==null) {
                acheteur.persist();
            }

            if (Personne.findById(a.getVendeur().getSecurite_sociale())==null) {

                vendeur.persist();
            }


            newActe.setAcheteur(acheteur);
            newActe.setVendeur(vendeur);
            newActe.persist();

            createActeVenteDTO(a,newActe.getId().intValue());
            gateway.saveActeDeVente(a,newActe.getId().intValue());



        }catch (Exception e){

            throw new Exception("Impossible d'ajouter l'acte de vente ?? la base de donn??es");
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

            List<Personne> personnes = a.getAncienProprietaires();
            List<String> ids=new ArrayList<>();
            for(Personne p : personnes)
                ids.add(p.getId());
            dtoActeDeVente.setAncienProprietaires(ids);

            gateway.sendActeDeVente(dtoActeDeVente);


        }catch (Exception e){

            throw new Exception("Impossible d'ajouter l'acte de vente ?? la base de donn??es");
        }

    }
}
