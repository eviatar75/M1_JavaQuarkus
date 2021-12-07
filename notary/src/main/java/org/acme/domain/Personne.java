package org.acme.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.acme.DTO.PersonneDTO;

import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Personne extends PanacheEntityBase {
    @Id
    @Column(name = "securite_sociale", nullable = false)
    private String id;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "code_postal")
    private Integer codePostal;

    @Column(name = "date_naissance", nullable = false)
    private LocalDate dateNaissance;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "numero_rue")
    private Integer numeroRue;

    @Column(name = "prenom", nullable = false)
    private String prenom;

    public Personne() {}

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Integer getNumeroRue() {
        return numeroRue;
    }

    public void setNumeroRue(Integer numeroRue) {
        this.numeroRue = numeroRue;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Integer getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(Integer codePostal) {
        this.codePostal = codePostal;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @JsonbProperty("securite_sociale")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static Personne personneFromDto(PersonneDTO p){
        Personne personne = new Personne();
        personne.setId(p.getSecurite_sociale());
        personne.setNumeroRue(p.getNumero_rue());
        personne.setAdresse(p.getAdresse());
        personne.setCodePostal(p.getCode_postal());
        personne.setDateNaissance(LocalDate.parse(p.getDate_naissance()));
        personne.setNom(p.getNom());
        personne.setPrenom(p.getPrenom());

        return personne;
    }

    @Override
    public String toString() {
        return "Personne{" +
                "id=" + id +
                ", adresse='" + adresse + '\'' +
                ", codePostal=" + codePostal +
                ", dateNaissance=" + dateNaissance +
                ", nom='" + nom + '\'' +
                ", numeroRue=" + numeroRue +
                ", prenom='" + prenom + '\'' +
                '}';
    }
}