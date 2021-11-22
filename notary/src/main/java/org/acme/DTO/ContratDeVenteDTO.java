package org.acme.DTO;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement
public class ContratDeVenteDTO {
    int id;
    String rue;
    String numeroRue;
    String codePostal;
    int etage;
    String porte;
    int nbPiece;
    int superficie;
    Date dateConstruction;
    String indicePerfEnergetique;
    String chauffage;
    boolean isolation;
    boolean amiante;
    Long acheteur;
    Long vendeur;
    List<Long> ancienProprietaires = new ArrayList<Long>();

    public ContratDeVenteDTO() {
    }

    public ContratDeVenteDTO(int id, String rue, String numeroRue, String codePostal, int etage, String porte, int nbPiece, int superficie, boolean statutHypotheque, Date dateConstruction, String indicePerfEnergetique, String chauffage, boolean isolation, boolean amiante, Long acheteur, Long vendeur) {
        this.id = id;
        this.rue = rue;
        this.numeroRue = numeroRue;
        this.codePostal = codePostal;
        this.etage = etage;
        this.porte = porte;
        this.nbPiece = nbPiece;
        this.superficie = superficie;
        this.dateConstruction = dateConstruction;
        this.indicePerfEnergetique = indicePerfEnergetique;
        this.chauffage = chauffage;
        this.isolation = isolation;
        this.amiante = amiante;
        this.acheteur = acheteur;
        this.vendeur = vendeur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getNumeroRue() {
        return numeroRue;
    }

    public void setNumeroRue(String numeroRue) {
        this.numeroRue = numeroRue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
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

    public int getNbPiece() {
        return nbPiece;
    }

    public void setNbPiece(int nbPiece) {
        this.nbPiece = nbPiece;
    }

    public int getSuperficie() {
        return superficie;
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    public Date getDateConstruction() {
        return dateConstruction;
    }

    public void setDateConstruction(Date dateConstruction) {
        this.dateConstruction = dateConstruction;
    }

    public String getIndicePerfEnergetique() {
        return indicePerfEnergetique;
    }

    public void setIndicePerfEnergetique(String indicePerfEnergetique) {
        this.indicePerfEnergetique = indicePerfEnergetique;
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

    public Long getAcheteur() {
        return acheteur;
    }

    public void setAcheteur(Long acheteur) {
        this.acheteur = acheteur;
    }

    public Long getVendeur() {
        return vendeur;
    }

    public void setVendeur(Long vendeur) {
        this.vendeur = vendeur;
    }

    public List<Long> getAncienProprietaires() {
        return ancienProprietaires;
    }

    public void setAncienProprietaires(List<Long> ancienProprietaires) {
        this.ancienProprietaires = ancienProprietaires;
    }
}
