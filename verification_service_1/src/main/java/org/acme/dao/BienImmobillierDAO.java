package org.acme.dao;

import org.acme.domain.BienImmobilier;

public interface BienImmobillierDAO {
    public BienImmobilier findFromDTO(String rue, String numeroRue, int porte, int etage, int nbPiece);
}
