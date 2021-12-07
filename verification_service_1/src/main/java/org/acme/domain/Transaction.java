package org.acme.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "Transaction", indexes = {
        @Index(name = "acheteur", columnList = "acheteur"),
        @Index(name = "bien_id", columnList = "bien_id"),
        @Index(name = "vendeur", columnList = "vendeur")
})
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "date_debut", nullable = false)
    private LocalDate dateDebut;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    @Column(name = "prix_vente", nullable = false)
    private Double prixVente;

    @Column(name = "date_compromis_vente")
    private LocalDate dateCompromisVente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "acheteur", nullable = false)
    private org.acme.domain.Personne acheteur;

    @ManyToOne(optional = true)
    @JoinColumn(name = "vendeur", nullable = true)
    private org.acme.domain.Personne vendeur;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bien_id", nullable = false)
    private BienImmobilier bien;

    public BienImmobilier getBien() {
        return bien;
    }

    public void setBien(BienImmobilier bien) {
        this.bien = bien;
    }

    public org.acme.domain.Personne getVendeur() {
        return vendeur;
    }

    public void setVendeur(org.acme.domain.Personne vendeur) {
        this.vendeur = vendeur;
    }

    public org.acme.domain.Personne getAcheteur() {
        return acheteur;
    }

    public void setAcheteur(org.acme.domain.Personne acheteur) {
        this.acheteur = acheteur;
    }

    public LocalDate getDateCompromisVente() {
        return dateCompromisVente;
    }

    public void setDateCompromisVente(LocalDate dateCompromisVente) {
        this.dateCompromisVente = dateCompromisVente;
    }

    public Double getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(Double prixVente) {
        this.prixVente = prixVente;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}