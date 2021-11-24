package org.acme.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Table(name = "Acte_de_vente")
@Entity
public class ActeDeVente extends PanacheEntity {

    @Column(name = "statue_pdf", nullable = false)
    private Boolean statuePdf = false;

    @Column(name = "statut_mail", nullable = false)
    private Boolean statutMail = false;

    @Column(name = "url_pdf")
    private String urlPdf;

    @ManyToOne(optional = false)
    @JoinColumn(name = "acheteur", nullable = false)
    private Personne acheteur;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vendeur", nullable = false)
    private Personne vendeur;

    public ActeDeVente (){}

    public Personne getVendeur() {
        return vendeur;
    }

    public void setVendeur(Personne vendeur) {
        this.vendeur = vendeur;
    }

    public Personne getAcheteur() {
        return acheteur;
    }

    public void setAcheteur(Personne acheteur) {
        this.acheteur = acheteur;
    }

    public String getUrlPdf() {
        return urlPdf;
    }

    public void setUrlPdf(String urlPdf) {
        this.urlPdf = urlPdf;
    }

    public Boolean getStatutMail() {
        return statutMail;
    }

    public void setStatutMail(Boolean statutMail) {
        this.statutMail = statutMail;
    }

    public Boolean getStatuePdf() {
        return statuePdf;
    }

    public void setStatuePdf(Boolean statuePdf) {
        this.statuePdf = statuePdf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ActeDeVente{" +
                "statuePdf=" + statuePdf +
                ", statutMail=" + statutMail +
                ", urlPdf='" + urlPdf + '\'' +
                ", acheteur=" + acheteur +
                ", vendeur=" + vendeur +
                ", id=" + id +
                '}';
    }
}