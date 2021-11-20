package org.acme.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;


@Table(name = "Acte_de_vente")
@Entity
public class ActeDeVente extends PanacheEntity {


    @Column(name = "url_pdf")
    private String urlPdf;

    @Column(name = "statue_pdf", nullable = false)
    private Boolean statuePdf = false;

    @Column(name = "statut_mail", nullable = false)
    private Boolean statutMail = false;

    @ManyToOne(optional = false)
    @JoinColumn(name = "acheteur", nullable = false)
    private Personne acheteur;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vendeur", nullable = false)
    private Personne vendeur;


    @JsonbProperty("vendeur")
    public Personne getVendeur() {
        return vendeur;
    }

    public void setVendeur(Personne vendeur) {
        this.vendeur = vendeur;
    }

    @JsonbProperty("acheteur")
    public Personne getAcheteur() {
        return acheteur;
    }

    public void setAcheteur(Personne acheteur) {
        this.acheteur = acheteur;
    }

    @JsonbProperty("statue_mail")
    public Boolean getStatutMail() {
        return statutMail;
    }

    public void setStatutMail(Boolean statutMail) {
        this.statutMail = statutMail;
    }

    @JsonbProperty("statue_pdf")
    public Boolean getStatuePdf() {
        return statuePdf;
    }

    public void setStatuePdf(Boolean statuePdf) {
        this.statuePdf = statuePdf;
    }

    @JsonbProperty("pdf_url")
    public String getUrlPdf() {
        return urlPdf;
    }

    public void setUrlPdf(String urlPdf) {
        this.urlPdf = urlPdf;
    }

}