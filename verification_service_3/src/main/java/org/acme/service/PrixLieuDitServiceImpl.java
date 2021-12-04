package org.acme.service;

import org.acme.DAO.PrixLieuDitDAO;
import org.acme.DTO.PrixDeVenteDTO;
import org.acme.domain.PrixLieuDit;
import org.acme.exception.PrixLieuDitNotFound;

import javax.inject.Inject;

public class PrixLieuDitServiceImpl implements PrixLieuDitService{

    @Inject
    PrixLieuDitDAO pldd;

    @Override
    public boolean comparePrixLieuDit(double prixM2BienImmobilier, int codePostal) throws PrixLieuDitNotFound {
        PrixLieuDit prixLieuDit = pldd.findByCodePostal(codePostal);
        return(prixLieuDit.getPrixMinM2()<=prixM2BienImmobilier);
    }

    @Override
    public String checkService3(PrixDeVenteDTO prixDeVenteDTO) throws PrixLieuDitNotFound {
        return (comparePrixLieuDit(prixDeVenteDTO.getPrixM2(), prixDeVenteDTO.getCode_postal())?"sucess":"Unsucess, prix trop bas !");
    }



}
