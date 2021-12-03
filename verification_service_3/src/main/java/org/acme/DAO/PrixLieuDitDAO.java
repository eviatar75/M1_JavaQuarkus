package org.acme.DAO;

import org.acme.domain.PrixLieuDit;
import org.acme.exception.PrixLieuDitNotFound;

import java.util.List;

public interface PrixLieuDitDAO {
    PrixLieuDit findByCodePostal(int codePostal) throws PrixLieuDitNotFound;

    List<PrixLieuDit> getAllPrixLieuDit();

    void createBasePrixLieuDit();

}

