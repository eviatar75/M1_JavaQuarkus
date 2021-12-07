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

    /*public Boolean isHypotheque(ContratDTO unContrat){
            BienImmobilier bienImmobilier = BienImmobilierGetter(unContrat);
            return bienImmobilier.getStatutHypoteque();
    }*/

    public Boolean isHypotheque(ContratDTO unContrat){
        BienImmobilier bienImmobilier = BienImmobilierGetter(unContrat);
        return bienImmobilier!=null?bienImmobilier.getStatutHypoteque():false;
    }

    /*public Boolean isAncienProprietairesMatching(ContratDTO unContrat){
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
    }*/

    public Boolean isAncienProprietairesMatching(ContratDTO unContrat){
        BienImmobilier bienImmobilier = BienImmobilierGetter(unContrat);

        //nouveau bien non repertorié en base
        if (bienImmobilier==null) {
            if(unContrat.getAncienProprietaires().size()==0)
                return true;
            else
                return false;
        }

        //Ancien propriétaire répertorié aupres de mon service
        List<Personne> ancienProprietaireLocal=new ArrayList<>();
        ancienProprietaireLocal = transactionDAO.getAnciensProprietaires(bienImmobilier.getId());

        //S'il n'y a aucun ancien propriétaire au bien apres du service nous verifions que le DTO avait aussi cela vide
        if(ancienProprietaireLocal.size()==0){
            if(unContrat.getAncienProprietaires().size()==0)
                return true;
            else
                return false;
        }

        //Ancien proprietaire pour un bien
        List<Personne> ancienProprietaireContrat=new ArrayList<>();
        if(unContrat.getAncienProprietaires().size()!=0)
            ancienProprietaireContrat = transactionDAO.getPersonnesFromIds(unContrat.getAncienProprietaires());

        //si ancien propriétaire rempli du coté service et coté DTO alors je compare le contenu des deux;
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
