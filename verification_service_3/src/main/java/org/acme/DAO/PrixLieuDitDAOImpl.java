package org.acme.DAO;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.acme.domain.PrixLieuDit;
import org.acme.exception.PrixLieuDitNotFound;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class PrixLieuDitDAOImpl implements PrixLieuDitDAO, PanacheRepository<PrixLieuDit> {
    @Override
    @Transactional
    public PrixLieuDit findByCodePostal(int codePostal) throws PrixLieuDitNotFound {
        try{

            return PrixLieuDit.findById(Integer.toString(codePostal));
        }
        catch(NoResultException e){
            throw new PrixLieuDitNotFound();
        }
    }

    @Override
    public List<PrixLieuDit> getAllPrixLieuDit() {
        return listAll();
    }

}
