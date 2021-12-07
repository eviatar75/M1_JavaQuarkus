package org.acme.DTO;

import java.util.ArrayList;
import java.util.List;

public class Service1DTO {

    String vendeur;
    int numero_rue;
    String adresse;
    int code_postal;
    int etage;
    int porte;
    List<String> ancienProprietaires = new ArrayList<>();



    public int getNumero_rue() {
        return numero_rue;
    }

    public void setNumero_rue(int num_rue) {
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
    public int getPorte() {
        return porte;
    }

    public void setPorte(int porte) {
        this.porte = porte;
    }

    public String getVendeur() {
        return vendeur;
    }

    public void setVendeur(String vendeur) {
        this.vendeur = vendeur;
    }

    public List<String> getAncienProprietaires() {
        return ancienProprietaires;
    }

    public void setAncienProprietaires(List<String> ancienProprietaires) {
        this.ancienProprietaires = ancienProprietaires;
    }

    @Override
    public String toString() {
        return "Service1DTO{" +
                "vendeur=" + vendeur +
                ", numero_rue=" + numero_rue +
                ", adresse='" + adresse + '\'' +
                ", code_postal=" + code_postal +
                ", etage=" + etage +
                ", porte=" + porte +
                ", ancienProprietaires=" + ancienProprietaires +
                '}';
    }
}
