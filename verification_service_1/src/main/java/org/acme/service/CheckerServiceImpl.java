package org.acme.service;

import org.acme.DTO.ContratDTO;
import org.acme.dao.BienImmobilierDAOImpl;
import org.acme.dao.TransactionDAOImpl;
import org.acme.domain.BienImmobilier;
import org.acme.domain.Personne;
import org.acme.exception.PersonneNotFound;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;

@ApplicationScoped
public class CheckerServiceImpl implements CheckerService{

    @Inject
    TransactionDAOImpl transactionDAO;

    @Inject
    BienImmobilierDAOImpl bienImmobilierDAO;

    public Boolean isHypotheque(ContratDTO unContrat) throws PersonneNotFound {
        try {
            BienImmobilier bienImmobilier = BienImmobilierGetter(unContrat);
            return bienImmobilier.getStatutHypoteque();
        }
        catch (PersonneNotFound e){
            return false;
        }

    }

    public Boolean isAncienProprietairesMatching(ContratDTO unContrat) throws PersonneNotFound {
        BienImmobilier bienImmobilier = BienImmobilierGetter(unContrat);
        List<Personne> ancienProprietaireLocal = transactionDAO.getAnciensProprietaires(bienImmobilier.getId());
        List<Personne> ancienProprietaireContrat = transactionDAO.getPersonnesFromIds(unContrat.getAncienProprietaires());
        return ancienProprietaireContrat.size() == ancienProprietaireLocal.size() && new HashSet<Personne>(ancienProprietaireContrat).containsAll(new HashSet<Personne>(ancienProprietaireLocal));
    }

    public String Checker(ContratDTO unContrat) throws PersonneNotFound {
        return isAncienProprietairesMatching(unContrat)&&isHypotheque(unContrat)?"success":"unsuccess";
    }

    public BienImmobilier BienImmobilierGetter(ContratDTO unContrat) throws PersonneNotFound {
        return bienImmobilierDAO.findFromDTO(unContrat.getAdresse(),
                Integer.toString(unContrat.getNumero_rue()),
                unContrat.getPorte(),
                unContrat.getEtage(),
                unContrat.getNbPieces());
    }
}
