package org.acme.service;

import org.acme.DAO.PrixLieuDitDAO;
import org.acme.DTO.PrixDeVenteDTO;
import org.acme.domain.PrixLieuDit;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class PrixLieuDitServiceImpl implements PrixLieuDitService{

    @Inject
    PrixLieuDitDAO pldd;

    @Override
    public boolean comparePrixLieuDit(double prixM2BienImmobilier, int codePostal){
        PrixLieuDit prixLieuDit = pldd.findByCodePostal(codePostal);
        return(prixLieuDit.getPrixMinM2()<=prixM2BienImmobilier);
    }

    @Override
    public String checkService3(PrixDeVenteDTO prixDeVenteDTO){
        return (comparePrixLieuDit(prixDeVenteDTO.getPrixM2(), prixDeVenteDTO.getCode_postal())?"success":"Unsucess, prix trop bas !");
    }



}
