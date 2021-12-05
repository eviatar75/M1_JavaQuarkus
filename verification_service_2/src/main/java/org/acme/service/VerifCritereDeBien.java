package org.acme.service;

import org.acme.DTO.VerifCritereDeBienDTO;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.util.Objects;


@ApplicationScoped
public class VerifCritereDeBien {

    public boolean VerifCritereSuperficie(int superficie) {
        return superficie >= 8;
    }

    public boolean VerifCritereIndicePerfEnergetique(String indicePerfEnergetique) {
        // Si l'IPE>330kWHm²ans, les actes de vente devront
        // mentionner que ce seuil sera à respecter à compter du 1er janvier 2028
        return Objects.equals(indicePerfEnergetique, "A") || Objects.equals(indicePerfEnergetique, "B")
                || Objects.equals(indicePerfEnergetique, "C") || Objects.equals(indicePerfEnergetique, "D")
                || Objects.equals(indicePerfEnergetique, "E") || Objects.equals(indicePerfEnergetique, "F") ||
                Objects.equals(indicePerfEnergetique, "G");

    }


    public boolean VerifCritereChauffage(String chauffage) {
        if(Objects.equals(chauffage, "électrique")){
            // <3000kWh par an
            return true;
        }else if(Objects.equals(chauffage, "gaz")){
            return false;
        }else if(Objects.equals(chauffage, "fioul")){
            return true;
        }else if(Objects.equals(chauffage, "au bois")){
            return true;
        }else if(Objects.equals(chauffage, "pompes à chaleur aérothermiques")){
            return true;
        }else if(Objects.equals(chauffage, "solaire")){
            return true;
        }else if(Objects.equals(chauffage, "biomasse")){
            return true;
        }
        return false;
    }


    public boolean VerifCritereDate(LocalDate dateConstruction) {
        System.out.println(dateConstruction.getYear());
        return dateConstruction.getYear() >= 1800;
    }

    public boolean VerifCritereIsolation(boolean isolation) {
        return true;
    }


    public boolean VerifCritereAmiante(boolean amiante) {
        return !amiante;
    }

    public String checkService2(VerifCritereDeBienDTO verifCritereDeBienDTO){
        return((VerifCritereSuperficie(verifCritereDeBienDTO.getSuperficie()) &&
                VerifCritereIndicePerfEnergetique(verifCritereDeBienDTO.getIndice_perf_energetique())
                && VerifCritereIsolation(verifCritereDeBienDTO.isIsolation())
                && VerifCritereAmiante(verifCritereDeBienDTO.isAmiante())
                && VerifCritereChauffage(verifCritereDeBienDTO.getChauffage())
                && VerifCritereDate(verifCritereDeBienDTO.getDate_construction())
        )?"success":"unsucess");
    }
}
