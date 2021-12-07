package org.acme.DAO;

import org.acme.domain.PrixLieuDit;

import java.util.List;

public interface PrixLieuDitDAO {
    PrixLieuDit findByCodePostal(int codePostal) ;

    List<PrixLieuDit> getAllPrixLieuDit();


}

