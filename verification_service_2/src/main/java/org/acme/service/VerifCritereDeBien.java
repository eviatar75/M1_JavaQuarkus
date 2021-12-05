package org.acme.service;

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
                || indicePerfEnergetique=="C" || indicePerfEnergetique=="D"){
            return true;
        }else if(indicePerfEnergetique=="E" || indicePerfEnergetique=="F" ||
                indicePerfEnergetique=="G"){
            //a besoin de travaux d'isolation
        }
        return false;

    }


    public void VerifCritereChauffage(String chauffage) {
        if(chauffage == "électrique"){
            // <3000kWh par an
        }else if(chauffage=="gaz"){

        }else if(chauffage=="fioul"){

        }else if(chauffage=="au bois"){

        }else if(chauffage=="pompes à chaleur aérothermiques"){

        }else if(chauffage=="solaire"){

        }
    }


    public boolean VerifCritereDate(LocalDate dateConstruction) {
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
}
