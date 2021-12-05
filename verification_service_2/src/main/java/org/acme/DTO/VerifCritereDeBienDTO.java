package org.acme.DTO;

import java.time.LocalDate;

public class VerifCritereDeBienDTO {

    private int superficie;
    private String chauffage;
    private boolean isolation;
    private boolean amiante;
    private LocalDate date_construction;
    private String indice_perf_energetique;


    public VerifCritereDeBienDTO() {

    }

    public int getSuperficie() {
        return superficie;

    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
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

    public LocalDate getDate_construction() {
        return date_construction;
    }

    public void setDate_construction(LocalDate date_construction) {
        this.date_construction = date_construction;
    }

    public String getIndice_perf_energetique() {
        return indice_perf_energetique;
    }

    public void setIndice_perf_energetique(String indice_perf_energetique) {
        this.indice_perf_energetique = indice_perf_energetique;
    }

    @Override
    public String toString() {
        return "VerifCritereDeBienDTO{" +
                "superficie=" + superficie +
                ", chauffage='" + chauffage + '\'' +
                ", isolation=" + isolation +
                ", amiante=" + amiante +
                ", dateConstruction=" + date_construction +
                ", indice_perf_energetique='" + indice_perf_energetique + '\'' +
                '}';
    }
}