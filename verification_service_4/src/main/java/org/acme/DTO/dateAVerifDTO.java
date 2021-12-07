package org.acme.DTO;

import java.time.LocalDate;

public class dateAVerifDTO {

    private LocalDate date_compromis_vente;
    private LocalDate date_signature_vente;

    public dateAVerifDTO(int idActeDeVente, LocalDate date_compromis_vente, LocalDate date_signature_vente) {
        this.date_compromis_vente = date_compromis_vente;
        this.date_signature_vente = date_signature_vente;
    }
    public dateAVerifDTO() {
    }

    public LocalDate getDate_compromis_vente() {
        return date_compromis_vente;
    }

    public void setDate_compromis_vente(LocalDate date_compromis_vente) {
        this.date_compromis_vente = date_compromis_vente;
    }

    public LocalDate getDate_signature_vente() {
        return date_signature_vente;
    }

    public void setDate_signature_vente(LocalDate date_signature_vente) {
        this.date_signature_vente = date_signature_vente;
    }


}
