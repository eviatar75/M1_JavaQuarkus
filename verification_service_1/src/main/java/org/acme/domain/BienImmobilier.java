package org.acme.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "Bien_immobilier")
@Entity
public class BienImmobilier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "rue", length = 30)
    private String rue;

    @Column(name = "numero_rue", length = 3)
    private String numeroRue;

    @Column(name = "code_postal", nullable = false, length = 5)
    private String codePostal;

    @Column(name = "etage")
    private Integer etage;

    @Column(name = "porte", length = 30)
    private String porte;

    @Column(name = "nb_piece", nullable = false)
    private Integer nbPiece;

    @Column(name = "superficie", nullable = false)
    private Integer superficie;

    @Column(name = "statut_hypoteque", nullable = false)
    private Boolean statutHypoteque = false;

    @Column(name = "date_construction")
    private LocalDate dateConstruction;

    @Lob
    @Column(name = "indice_perf_energetique")
    private String indicePerfEnergetique;

    @Lob
    @Column(name = "chauffage")
    private String chauffage;

    @Column(name = "isolation")
    private Boolean isolation;

    @Column(name = "amiante")
    private Boolean amiante;

    public Boolean getAmiante() {
        return amiante;
    }

    public void setAmiante(Boolean amiante) {
        this.amiante = amiante;
    }

    public Boolean getIsolation() {
        return isolation;
    }

    public void setIsolation(Boolean isolation) {
        this.isolation = isolation;
    }

    public String getChauffage() {
        return chauffage;
    }

    public void setChauffage(String chauffage) {
        this.chauffage = chauffage;
    }

    public String getIndicePerfEnergetique() {
        return indicePerfEnergetique;
    }

    public void setIndicePerfEnergetique(String indicePerfEnergetique) {
        this.indicePerfEnergetique = indicePerfEnergetique;
    }

    public LocalDate getDateConstruction() {
        return dateConstruction;
    }

    public void setDateConstruction(LocalDate dateConstruction) {
        this.dateConstruction = dateConstruction;
    }

    public Boolean getStatutHypoteque() {
        return statutHypoteque;
    }

    public void setStatutHypoteque(Boolean statutHypoteque) {
        this.statutHypoteque = statutHypoteque;
    }

    public Integer getSuperficie() {
        return superficie;
    }

    public void setSuperficie(Integer superficie) {
        this.superficie = superficie;
    }

    public Integer getNbPiece() {
        return nbPiece;
    }

    public void setNbPiece(Integer nbPiece) {
        this.nbPiece = nbPiece;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public Integer getEtage() {
        return etage;
    }

    public void setEtage(Integer etage) {
        this.etage = etage;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getNumeroRue() {
        return numeroRue;
    }

    public void setNumeroRue(String numeroRue) {
        this.numeroRue = numeroRue;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}