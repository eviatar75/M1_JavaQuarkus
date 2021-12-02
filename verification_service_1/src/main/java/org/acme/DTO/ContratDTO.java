package org.acme.DTO;

import java.util.ArrayList;
import java.util.List;


public class ContratDTO {

    Long vendeur;
    int numero_rue;
    String adresse;
    int code_postal;
    int etage;
    String porte;

    public int getNbPieces() {
        return nbPieces;
    }

    public void setNbPieces(int nbPieces) {
        this.nbPieces = nbPieces;
    }

    int nbPieces;
    List<Long> ancienProprietaires = new ArrayList<Long>();


    public List<Long> getAncienProprietaires() {
        return ancienProprietaires;
    }

    public void setAncienProprietaires(List<Long> ancienProprietaires) {
        this.ancienProprietaires = ancienProprietaires;
    }

    public Long getVendeur() {
        return vendeur;
    }

    public void setVendeur(Long vendeur) {
        this.vendeur = vendeur;
    }

    public int getNum_rue() {
        return numero_rue;
    }

    public void setNum_rue(int num_rue) {
        this.numero_rue = num_rue;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(int code_postal) {
        this.code_postal = code_postal;
    }

    public int getEtage() {
        return etage;
    }

    public void setEtage(int etage) {
        this.etage = etage;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    @Override
    public String toString() {
        return "\n\nService1DTO{" +
                "vendeur=" + vendeur +
                ", num_rue=" + numero_rue +
                ", adresse='" + adresse + '\'' +
                ", code_postal=" + code_postal +
                ", etage=" + etage +
                ", porte='" + porte + '\'' +
                '}'+"\n\n";
    }
}
