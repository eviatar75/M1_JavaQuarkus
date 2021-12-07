package org.acme.DTO;

import java.time.LocalDate;

public class Service2DTO {

    int superficie;
    String chauffage;
    boolean isolation;
    boolean amiante;
    LocalDate date_construction;
    String indice_perf_energetique;


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
        return "Service2DTO{" +
                "superficie=" + superficie +
                ", chauffage='" + chauffage + '\'' +
                ", isolation=" + isolation +
                ", amiante=" + amiante +
                ", date_construction=" + date_construction +
                ", indice_perf_energetique='" + indice_perf_energetique + '\'' +
                '}';
    }
}
