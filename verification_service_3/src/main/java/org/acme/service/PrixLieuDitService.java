package org.acme.service;

import org.acme.exception.PrixLieuDitNotFound;

public interface PrixLieuDitService {
    boolean comparePrixLieuDit(double prixM2, int codePostal) throws PrixLieuDitNotFound;

    }
