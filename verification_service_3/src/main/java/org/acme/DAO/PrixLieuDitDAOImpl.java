package org.acme.DAO;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.acme.domain.PrixLieuDit;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class PrixLieuDitDAOImpl implements PrixLieuDitDAO, PanacheRepository<PrixLieuDit> {
    @Override
    @Transactional
    public PrixLieuDit findByCodePostal(int codePostal){
        try{

            return PrixLieuDit.findById(Integer.toString(codePostal));
        }
        catch(NoResultException e){
            return null;
        }
    }

    @Override
    @Transactional
    public List<PrixLieuDit> getAllPrixLieuDit() {
        try{
            return listAll();
        }
        catch (NoResultException e){
            return null;
        }

    }

}
