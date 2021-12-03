package org.acme.service;

import org.acme.DAO.PrixLieuDitDAO;
import org.acme.domain.PrixLieuDit;
import org.acme.exception.PrixLieuDitNotFound;

import javax.inject.Inject;

public class PrixLieuDitServiceImpl implements PrixLieuDitService{

    @Inject
    PrixLieuDitDAO pldd;

    @Override
    public boolean comparePrixLieuDit(double prixM2, int codePostal) throws PrixLieuDitNotFound {
        PrixLieuDit prixLieuDit = pldd.findByCodePostal(codePostal);
        return(prixLieuDit.getPrixMinM2()==prixM2);
    }
}
