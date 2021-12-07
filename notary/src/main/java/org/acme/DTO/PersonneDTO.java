package org.acme.DTO;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

@XmlRootElement
public class PersonneDTO {


    private long securite_sociale;
    private int code_postal;
    private int numero_rue;
    private String adresse;
    private String date_naissance;
    private String nom;
    private String prenom;
    private String mail;


    public long getSecurite_sociale() {
        return securite_sociale;
    }

    public void setSecurite_sociale(long securite_sociale) {
        this.securite_sociale = securite_sociale;
    }

    public int getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(int code_postal) {
        this.code_postal = code_postal;
    }

    public int getNumero_rue() {
        return numero_rue;
    }

    public void setNumero_rue(int numero_rue) {
        this.numero_rue = numero_rue;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "PersonneDTO{" +
                "securite_sociale=" + securite_sociale +
                ", code_postal=" + code_postal +
                ", numero_rue=" + numero_rue +
                ", adresse='" + adresse + '\'' +
                ", date_naissance='" + date_naissance + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
