package org.acme.DTO;

import org.acme.domain.Personne;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class ContratPostDTO {
    PersonneDTO acheteur;
    PersonneDTO vendeur;

    List<Personne> ancienProprietaires = new ArrayList<Personne>();

    int numero_rue;
    int code_postal;
    int etage;
    int porte;
    int prix;
    int nb_piece;
    int superficie;

    LocalDate date_construction;
    LocalDate date_compromis_vente;
    LocalDate date_signature_vente;

    String indice_perf_energetique;
    String chauffage;
    String rue;

    boolean isolation;
    boolean amiante;



    public ContratPostDTO() {
    }

    public PersonneDTO getAcheteur() {
        return acheteur;
    }

    public void setAcheteur(PersonneDTO acheteur) {
        this.acheteur = acheteur;
    }

    public PersonneDTO getVendeur() {
        return vendeur;
    }

    public void setVendeur(PersonneDTO vendeur) {
        this.vendeur = vendeur;
    }

    public List<Personne> getAncienProprietaires() {
        return ancienProprietaires;
    }

    public void setAncienProprietaires(List<Personne> ancienProprietaires) {
        this.ancienProprietaires = ancienProprietaires;
    }


    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
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

    public int getNb_piece() {
        return nb_piece;
    }

    public void setNb_piece(int nb_piece) {
        this.nb_piece = nb_piece;
    }

    public int getSuperficie() {
        return superficie;
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    public LocalDate getDate_construction() {
        return date_construction;
    }

    public void setDate_construction(LocalDate date_construction) {
        this.date_construction = date_construction;
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


    public int getNumero_rue() {
        return numero_rue;
    }

    public void setNumero_rue(int numero_rue) {
        this.numero_rue = numero_rue;
    }

    public int getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(int code_postal) {
        this.code_postal = code_postal;
    }

    public String getIndice_perf_energetique() {
        return indice_perf_energetique;
    }

    public void setIndice_perf_energetique(String indice_perf_energetique) {
        this.indice_perf_energetique = indice_perf_energetique;
    }

    @Override
    public String toString() {
        return "ContratPostDTO{" +
                "acheteur=" + acheteur +
                ", Vendeur=" + vendeur +
                ", ancienProprietaires=" + ancienProprietaires +
                ", numero_rue=" + numero_rue +
                ", code_postal=" + code_postal +
                ", etage=" + etage +
                ", porte=" + porte +
                ", prix=" + prix +
                ", nb_piece=" + nb_piece +
                ", superficie=" + superficie +
                ", date_construction=" + date_construction +
                ", date_compromis_vente=" + date_compromis_vente +
                ", date_signature_vente=" + date_signature_vente +
                ", indice_perf_energetique='" + indice_perf_energetique + '\'' +
                ", chauffage='" + chauffage + '\'' +
                ", rue='" + rue + '\'' +
                ", isolation=" + isolation +
                ", amiante=" + amiante +
                '}';
    }
}
