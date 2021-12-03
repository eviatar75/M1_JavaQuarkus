package org.acme.DAO;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.acme.domain.PrixLieuDit;
import org.acme.exception.PrixLieuDitNotFound;

import javax.persistence.NoResultException;
import java.util.List;

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

    @Override
    public void createBasePrixLieuDit() {
        String codePostalParis = new String("75000");
        Double prixMinM2Paris = Double.valueOf("9960");
        String codePostalMarseille = new String("13000");
        Double prixMinM2Marseille = Double.valueOf("2156");
        String codePostalLyon = new String("69000");
        Double prixMinM2Lyon = Double.valueOf("4567");

        PrixLieuDit prixLieuDitParis = new PrixLieuDit();
        prixLieuDitParis.setPrixMinM2(prixMinM2Paris);
        prixLieuDitParis.setId(codePostalParis);
        PrixLieuDit prixLieuDitMarseille = new PrixLieuDit();
        prixLieuDitMarseille.setPrixMinM2(prixMinM2Marseille);
        prixLieuDitMarseille.setId(codePostalMarseille);
        PrixLieuDit prixLieuDitLyon = new PrixLieuDit();
        prixLieuDitLyon.setPrixMinM2(prixMinM2Lyon);
        prixLieuDitLyon.setId(codePostalLyon);
        persist(prixLieuDitParis);
        persist(prixLieuDitMarseille);
        persist(prixLieuDitLyon);
    }
}
