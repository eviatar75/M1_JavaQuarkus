package org.acme.dao;

import org.acme.domain.BienImmobilier;
import org.acme.exception.PersonneNotFound;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class BienImmobilierDAOImpl implements  BienImmobillierDAO{
    @PersistenceContext(name = "mysql")
    EntityManager em;

    @Override
    public BienImmobilier findFromDTO(String rue, String numeroRue, int porte, int etage, int nbPiece) throws PersonneNotFound {
        try {
            BienImmobilier bien = (BienImmobilier) em.createQuery("select b from BienImmobilier b where b.rue=:rue and b.numeroRue=:numeroRue and b.porte=:porte and b.etage=:etage and b.nbPiece=:nbPiece")
                    .setParameter("rue", numeroRue)
                    .setParameter("numeroRue", numeroRue)
                    .setParameter("porte", porte)
                    .setParameter("etage", etage)
                    .setParameter("nbPiece", nbPiece).getSingleResult();

            return bien;
        }
        catch (NoResultException e){
            throw new PersonneNotFound();
        }
    }
}
