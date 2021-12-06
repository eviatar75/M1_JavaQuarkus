package org.acme.service;

import org.acme.DTO.ContratDTO;
import org.acme.dao.BienImmobilierDAOImpl;
import org.acme.dao.TransactionDAOImpl;
import org.acme.domain.BienImmobilier;
import org.acme.domain.Personne;
import org.acme.exception.PersonneNotFound;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.HashSet;
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

    public Boolean isAncienProprietairesMatching(ContratDTO unContrat){
        BienImmobilier bienImmobilier = BienImmobilierGetter(unContrat);
        System.out.println("debug voici l'id du bien "+bienImmobilier.getId());
        List<Personne> ancienProprietaireLocal=new ArrayList<>();
        if (bienImmobilier!=null)
            ancienProprietaireLocal = transactionDAO.getAnciensProprietaires(bienImmobilier.getId());
        List<Personne> ancienProprietaireContrat=new ArrayList<>();
        if(unContrat.getAncienProprietaires().size()!=0)
            ancienProprietaireContrat = transactionDAO.getPersonnesFromIds(unContrat.getAncienProprietaires());
        if(ancienProprietaireContrat.size()==0 && ancienProprietaireLocal.size()==0)
            return true;
        return ancienProprietaireContrat.size() == ancienProprietaireLocal.size() && new HashSet<Personne>(ancienProprietaireContrat).containsAll(new HashSet<Personne>(ancienProprietaireLocal));
    }

    public String Checker(ContratDTO unContrat){

        boolean match = isAncienProprietairesMatching(unContrat);
        boolean hypoteque = isHypotheque(unContrat);

        if (match && !hypoteque)
            return "success";
        String error = "";
        if (!match)
            error+="liste d'anciens propriétaire éronnée ";
        if (hypoteque)
            error+="bien hypotequé";
        return error;

    }

    public BienImmobilier BienImmobilierGetter(ContratDTO unContrat){
        return  bienImmobilierDAO.findFromDTO(
                    unContrat.getAdresse(),
                    Integer.toString(unContrat.getNumero_rue()),
                    Integer.toString(unContrat.getPorte()),
                    unContrat.getEtage(),
                    Integer.toString(unContrat.getCode_postal()));
    }
}
