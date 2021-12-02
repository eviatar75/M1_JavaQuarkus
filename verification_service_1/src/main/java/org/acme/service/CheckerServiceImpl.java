package org.acme.service;

import org.acme.DTO.ContratDTO;
import org.acme.dao.BienImmobilierDAOImpl;
import org.acme.dao.TransactionDAOImpl;
import org.acme.domain.BienImmobilier;
import org.acme.exception.PersonneNotFound;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class CheckerServiceImpl implements CheckerService{

    @Inject
    TransactionDAOImpl transactionDAO;

    @Inject
    BienImmobilierDAOImpl bienImmobilierDAO;

    public Boolean isHypotheque(ContratDTO unContrat){

        BienImmobilier bienImmobilier = BienImmobilierGetter(unContrat);
        return bienImmobilier.getStatutHypoteque();
    }

    public Boolean isAncienProprietairesMatching(ContratDTO unContrat) throws PersonneNotFound {
        BienImmobilier bienImmobilier = BienImmobilierGetter(unContrat);
        List ancienProprietaireLocal = transactionDAO.getAnciensProprietaires(bienImmobilier.getId());
        List ancienProprietaireContrat = transactionDAO.getPersonnesFromIds(unContrat.getAncienProprietaires());
        return (ancienProprietaireContrat.containsAll(ancienProprietaireLocal) && ancienProprietaireLocal.containsAll(ancienProprietaireContrat));
    }

    public Boolean Checker(ContratDTO unContrat) throws PersonneNotFound {
        return isAncienProprietairesMatching(unContrat)&&isHypotheque(unContrat);
    }

    public BienImmobilier BienImmobilierGetter(ContratDTO unContrat){
        return bienImmobilierDAO.findFromDTO(unContrat.getAdresse(),
                Integer.toString(unContrat.getNum_rue()),
                Integer.parseInt(unContrat.getPorte()),
                unContrat.getEtage(),
                unContrat.getNbPieces());
    }
}
