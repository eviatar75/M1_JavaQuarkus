package org.acme.DAO;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.acme.domain.PrixLieuDit;
import org.acme.exception.PrixLieuDitNotFound;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NoResultException;
import java.util.List;

@ApplicationScoped
public class PrixLieuDitDAOImpl implements PrixLieuDitDAO, PanacheRepository<PrixLieuDit> {
    @Override
    public PrixLieuDit findByCodePostal(int codePostal) throws PrixLieuDitNotFound {
        try{
            return find("codePostal", codePostal).firstResult();
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
