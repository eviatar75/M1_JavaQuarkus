package org.acme.service;

import org.acme.DTO.PrixDeVenteDTO;
import org.acme.exception.PrixLieuDitNotFound;

public interface PrixLieuDitService {
    boolean comparePrixLieuDit(double prixM2BienImmobilier, int codePostal) throws PrixLieuDitNotFound;
    String checkService3(PrixDeVenteDTO prixDeVenteDTO) throws PrixLieuDitNotFound;
    }
