package org.acme.dao;

import org.acme.domain.BienImmobilier;
import org.acme.exception.PersonneNotFound;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.control.ActivateRequestContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@ApplicationScoped
@ActivateRequestContext
public class BienImmobilierDAOImpl implements  BienImmobillierDAO{
    @PersistenceContext(name = "mysql")
    EntityManager em;


    @Override
    @ActivateRequestContext
    public BienImmobilier findFromDTO(String rue, String numeroRue, String porte, int etage, int nbPiece) {
        int id = 1;
        BienImmobilier bien1 = (BienImmobilier) em.createQuery("select  b from  BienImmobilier b where b.id=:id").setParameter("id",id).getSingleResult();
        System.out.println(bien1);


            BienImmobilier bien = (BienImmobilier) em.createQuery("select b from BienImmobilier b where b.rue=:rue and b.numeroRue=:numeroRue and b.porte=:porte and b.etage=:etage and b.nbPiece=:nbPiece")
                    .setParameter("rue", rue)
                    .setParameter("numeroRue", numeroRue)
                    .setParameter("porte", porte)
                    .setParameter("etage", etage)
                    .setParameter("nbPiece", nbPiece).getSingleResult();

            return bien;

    }


}
