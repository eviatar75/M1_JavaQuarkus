package org.acme.dao;

import org.acme.domain.BienImmobilier;
import org.acme.exception.PersonneNotFound;

public interface BienImmobillierDAO {

    public BienImmobilier findFromDTO(String rue, String numeroRue, String porte, int etage, String codePostal) throws PersonneNotFound;
}
