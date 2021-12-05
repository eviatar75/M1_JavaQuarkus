package org.acme.DTO;

import java.time.LocalDate;

public class dateAVerifDTO {

    private int idActeDeVente;
    private LocalDate date_compromis_vente;
    private LocalDate date_signature_vente;

    public dateAVerifDTO(int idActeDeVente, LocalDate date_compromis_vente, LocalDate date_signature_vente) {
        this.idActeDeVente = idActeDeVente;
        this.date_compromis_vente = date_compromis_vente;
        this.date_signature_vente = date_signature_vente;
    }

    public int getIdActeDeVente() {
        return idActeDeVente;
    }

    public void setIdActeDeVente(int idActeDeVente) {
        this.idActeDeVente = idActeDeVente;
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

    public boolean verifDate(){

        boolean res = date_signature_vente.isAfter(date_compromis_vente.plusMonths(2));

        if (res){

            System.out.println("Vérification date succés");

        }else
            System.out.println("La vérif de la date n'eest pas passé");


        return res;

    } //A approfondir
}
