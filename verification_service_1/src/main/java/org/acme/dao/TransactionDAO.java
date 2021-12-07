package org.acme.dao;

import org.acme.exception.PersonneNotFound;
import org.acme.domain.Personne;

import java.util.List;

public interface TransactionDAO  {

    public List<Personne> getAnciensProprietaires(int idBien) throws PersonneNotFound;
    public List<Personne> getPersonnesFromIds(List<String> ids) throws PersonneNotFound;
}
