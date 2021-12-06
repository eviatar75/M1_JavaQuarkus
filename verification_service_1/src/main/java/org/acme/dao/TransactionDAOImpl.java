package org.acme.dao;

import org.acme.exception.PersonneNotFound;
import org.acme.domain.Personne;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.control.ActivateRequestContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
@ActivateRequestContext
public class TransactionDAOImpl implements TransactionDAO{

    @PersistenceContext(name = "mysql")
    EntityManager em;
    @Override
    @ActivateRequestContext
    public List<Personne> getAnciensProprietaires(int idBien) throws PersonneNotFound {
        try {
            List ancienPropretaires = em.createQuery("select acheteur from Transaction where Transaction.id=:idBien").setParameter("idBien",idBien).getResultList();
            return ancienPropretaires;
        }
        catch (NoResultException e){
            throw new PersonneNotFound();
        }
    }

    @Override
    @ActivateRequestContext
    public List<Personne> getPersonnesFromIds(List<Long> ids)throws PersonneNotFound{
        try {
            List personnes = em.createQuery("select p from Personne p where p.id in :ids").setParameter("ids",ids).getResultList();
            return personnes;
        }
        catch (NoResultException e){
            throw new PersonneNotFound();
        }

    }
}
