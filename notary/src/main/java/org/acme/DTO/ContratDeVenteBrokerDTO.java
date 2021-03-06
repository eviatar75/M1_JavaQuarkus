package org.acme.DTO;


import javax.json.bind.annotation.JsonbProperty;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;



public class ContratDeVenteBrokerDTO {
    int id;

    int numero_rue;
    String rue;
    int codePostal;
    int etage;
    int porte;
    int prix;
    int nbPiece;
    int superficie;

    LocalDate dateConstruction;
    LocalDate date_compromis_vente;
    LocalDate date_signature_acte;

    String indicePerfEnergetique;
    String chauffage;
    boolean isolation;
    boolean amiante;

    String acheteur;
    String vendeur;

    List<String> ancienProprietaires = new ArrayList<>();

    public ContratDeVenteBrokerDTO() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero_rue() {
        return numero_rue;
    }

    public void setNumero_rue(int numeroRue) {
        this.numero_rue = numeroRue;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public int getEtage() {
        return etage;
    }

    public void setEtage(int etage) {
        this.etage = etage;
    }

    public int getPorte() {
        return porte;
    }

    public void setPorte(int porte) {
        this.porte = porte;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getNbPiece() {
        return nbPiece;
    }

    public void setNbPiece(int nbPiece) {
        this.nbPiece = nbPiece;
    }

    public int getSuperficie() {
        return superficie;
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    public LocalDate getDateConstruction() {
        return dateConstruction;
    }

    public void setDateConstruction(LocalDate dateConstruction) {
        this.dateConstruction = dateConstruction;
    }

    public LocalDate getDate_compromis_vente() {
        return date_compromis_vente;
    }

    public void setDate_compromis_vente(LocalDate date_compromis_vente) {
        this.date_compromis_vente = date_compromis_vente;
    }

    public LocalDate getDate_signature_acte() {
        return date_signature_acte;
    }

    public void setDate_signature_acte(LocalDate date_signature_acte) {
        this.date_signature_acte = date_signature_acte;
    }

    public String getIndicePerfEnergetique() {
        return indicePerfEnergetique;
    }

    public void setIndicePerfEnergetique(String indicePerfEnergetique) {
        this.indicePerfEnergetique = indicePerfEnergetique;
    }

    public String getChauffage() {
        return chauffage;
    }

    public void setChauffage(String chauffage) {
        this.chauffage = chauffage;
    }

    public boolean isIsolation() {
        return isolation;
    }

    public void setIsolation(boolean isolation) {
        this.isolation = isolation;
    }

    public boolean isAmiante() {
        return amiante;
    }

    public void setAmiante(boolean amiante) {
        this.amiante = amiante;
    }

    public String getAcheteur() {
        return acheteur;
    }

    public void setAcheteur(String acheteur) {
        this.acheteur = acheteur;
    }

    public String getVendeur() {
        return vendeur;
    }

    public void setVendeur(String vendeur) {
        this.vendeur = vendeur;
    }


    @JsonbProperty("proprietaires")
    public List<String> getAncienProprietaires() {
        return ancienProprietaires;
    }

    public void setAncienProprietaires(List<String> ancienProprietaires) {
        this.ancienProprietaires = ancienProprietaires;
    }

    @Override
    public String toString() {
        return "ContratDeVenteDTO{" +
                "id=" + id +
                ", numeroRue=" + numero_rue +
                ", rue='" + rue + '\'' +
                ", codePostal=" + codePostal +
                ", etage=" + etage +
                ", porte=" + porte +
                ", prix=" + prix +
                ", nbPiece=" + nbPiece +
                ", superficie=" + superficie +
                ", dateConstruction=" + dateConstruction +
                ", date_compromis_vente=" + date_compromis_vente +
                ", date_signature_acte=" + date_signature_acte +
                ", indicePerfEnergetique='" + indicePerfEnergetique + '\'' +
                ", chauffage='" + chauffage + '\'' +
                ", isolation=" + isolation +
                ", amiante=" + amiante +
                ", acheteur=" + acheteur +
                ", vendeur=" + vendeur +
                ", ancienProprietaires=" + ancienProprietaires +
                '}';
    }
}
