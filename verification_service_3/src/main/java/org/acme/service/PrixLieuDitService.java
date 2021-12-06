package org.acme.service;

import org.acme.DTO.PrixDeVenteDTO;

public interface PrixLieuDitService {
    boolean comparePrixLieuDit(double prixM2BienImmobilier, int codePostal);
    String checkService3(PrixDeVenteDTO prixDeVenteDTO);
    }
