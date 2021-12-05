package org.acme.service;

import org.acme.DTO.VerifCritereDeBienDTO;

import java.time.LocalDate;

public class VerifCritereDeBien {

    public boolean VerifCritereSuperficie(int superficie) {
        if(superficie<8){
            return false;
        }
        return true;
    }

    public boolean VerifCritereIndicePerfEnergetique(String indicePerfEnergetique) {
        if(indicePerfEnergetique=="A" || indicePerfEnergetique=="B"
                || indicePerfEnergetique=="C" || indicePerfEnergetique=="D"
                ||indicePerfEnergetique=="E" || indicePerfEnergetique=="F" ||
                indicePerfEnergetique=="G"){
            // Si l'IPE>330kWHm²ans, les actes de vente devront
            // mentionner que ce seuil sera à respecter à compter du 1er janvier 2028
            return true;
        }
        return false;

    }


    public boolean VerifCritereChauffage(String chauffage) {
        if(chauffage == "électrique"){
            // <3000kWh par an
            return true;
        }else if(chauffage=="gaz"){
            return false;
        }else if(chauffage=="fioul"){
            return true;
        }else if(chauffage=="au bois"){
            return true;
        }else if(chauffage=="pompes à chaleur aérothermiques"){
            return true;
        }else if(chauffage=="solaire"){
            return true;
        }else if(chauffage=="biomasse"){
            return true;
        }
        return false;
    }


    public boolean VerifCritereDate(LocalDate dateConstruction) {
        if(dateConstruction.getYear()<1800){
            return false;
        }
        return true;
    }

    public boolean VerifCritereIsolation(boolean isolation) {
        if(isolation){
            return false;
        }
        return true;
    }


    public boolean VerifCritereAmiante(boolean amiante) {
        if(amiante){
            return false;
        }
        return true;
    }

    public String checkService2(VerifCritereDeBienDTO verifCritereDeBienDTO){
        return((VerifCritereSuperficie(verifCritereDeBienDTO.getSuperficie()) &&
                VerifCritereIndicePerfEnergetique(verifCritereDeBienDTO.getIndice_perf_energetique())
                && VerifCritereIsolation(verifCritereDeBienDTO.isIsolation())
                && VerifCritereAmiante(verifCritereDeBienDTO.isAmiante())
                && VerifCritereChauffage(verifCritereDeBienDTO.getChauffage())
                && VerifCritereDate(verifCritereDeBienDTO.getDateConstruction())
        )?"success":"unsucess");
    }
}
