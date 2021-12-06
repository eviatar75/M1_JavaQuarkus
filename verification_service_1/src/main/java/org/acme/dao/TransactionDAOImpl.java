package org.acme.dao;

import org.acme.domain.Transaction;
import org.acme.exception.PersonneNotFound;
import org.acme.domain.Personne;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.control.ActivateRequestContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@ActivateRequestContext
public class TransactionDAOImpl implements TransactionDAO{

    @PersistenceContext(name = "mysql")
    EntityManager em;
    @Override
    @ActivateRequestContext
    public List<Personne> getAnciensProprietaires(int idBien) {
        try {
            List<Transaction> transactions = em.createQuery("select t from Transaction t where t.bien.id=:idBien").setParameter("idBien", idBien).getResultList();
            List ancienPropretaires = new ArrayList<Personne>();
            for (Transaction t:transactions )
                ancienPropretaires.add(t.getAcheteur());
            return ancienPropretaires;
        }

        catch (NoResultException e){
            return  null;
        }
    }

    @Override
    @ActivateRequestContext
    public List<Personne> getPersonnesFromIds(List<Long> ids){
            try {
                List personnes = em.createQuery("select p from Personne p where p.id in :ids").setParameter("ids",ids).getResultList();
                return personnes;
            }
            catch (NoResultException e){
                return  null;
            }
    }
}
