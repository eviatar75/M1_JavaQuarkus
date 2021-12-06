package org.acme;

import java.time.LocalDate;

public class Service4DTO {

    LocalDate date_compromis_vente;
    LocalDate date_signature_vente;

    public LocalDate getDate_compromis_vente() {
        return date_compromis_vente;
    }

    public void setDate_compromis_vente(LocalDate date_compromis_vente) {
        this.date_compromis_vente = date_compromis_vente;
    }


    @Override
    public String toString() {
        return "Service4DTO{" +
                "date_compromis_vente=" + date_compromis_vente +
                ", date_signature_vente=" + date_signature_vente +
                '}';
    }

    public LocalDate getDate_signature_vente() {
        return date_signature_vente;
    }

    public void setDate_signature_vente(LocalDate date_signature_vente) {
        this.date_signature_vente = date_signature_vente;
    }
}
