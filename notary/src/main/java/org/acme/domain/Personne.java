package org.acme.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Personne extends PanacheEntityBase {
    @Id
    @Column(name = "securite_sociale", nullable = false)
    private Long id;

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
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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