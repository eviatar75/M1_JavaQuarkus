package org.acme.service;

import org.acme.DTO.VerifCritereDeBienDTO;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.util.Objects;


@ApplicationScoped
public class VerifCritereDeBien {

    public String VerifCritereSuperficie(int superficie) {
        return superficie >= 8?"success":"la superficie du bien: "+superficie+"m2 est inférieure à 8m2, l'acte ne peut être valide ";
    }

    public String VerifCritereIndicePerfEnergetique(String indicePerfEnergetique) {
        return (Objects.equals(indicePerfEnergetique, "A") || Objects.equals(indicePerfEnergetique, "B")
                || Objects.equals(indicePerfEnergetique, "C") || Objects.equals(indicePerfEnergetique, "D")
                || Objects.equals(indicePerfEnergetique, "E") || Objects.equals(indicePerfEnergetique, "F") ||
                Objects.equals(indicePerfEnergetique, "G"))?"success":"L'indice de performance énergétique: "+indicePerfEnergetique+" n'est pas conforme aux indices en vigueur";
    }


    public String VerifCritereChauffage(String chauffage) {
        //A partir du 1er janvier 2022:
        //Il sera interdit d'installer une chaudière au fioul
        // ET au gaz neuve dans les logements.


        if(Objects.equals(chauffage, "électrique")){
            // <3000kWh par an
            return "success";
        }else if(Objects.equals(chauffage, "gaz")){
            return "L'acte de vente ne peut être valide, car le chauffage est alimenté par gaz";
        }else if(Objects.equals(chauffage, "fioul")){
            return "L'acte de vente ne peut être valide, car le chauffage est alimenté par fioul (en 2021 quand même..)";
        }else if(Objects.equals(chauffage, "au bois")){
            return "success";
        }else if(Objects.equals(chauffage, "pompes à chaleur aérothermiques")){
            return "success";
        }else if(Objects.equals(chauffage, "solaire")){
            return "success";
        }else if(Objects.equals(chauffage, "biomasse")){
            return "success";
        }
        return "le type d'alimentation thermique n'est pas conforme";
    }


    public String VerifCritereDate(LocalDate dateConstruction) {
        System.out.println(dateConstruction.getYear());
        return dateConstruction.getYear() >= 1800?"success":"L'acte de vente ne peut pas être valide car le bail a été construit avant le 19e siècle (veuillez contacté l'UNESCO)";
    }

    public String VerifCritereIsolation(boolean isolation) {
        //Aucune obligation nationale sur l'isolation
        return "success";
    }


    public String VerifCritereAmiante(boolean amiante) {
        return !amiante?"success":"L'acte de vente ne peut pas être valide car il contient de l'amiante";
    }

    public String checkService2(VerifCritereDeBienDTO verifCritereDeBienDTO){

        StringBuilder checking= new StringBuilder();

        if  (!(VerifCritereSuperficie(verifCritereDeBienDTO.getSuperficie()).equals("success"))){
            checking.append(VerifCritereSuperficie(verifCritereDeBienDTO.getSuperficie()));
            checking.append("\n");
        }
        if  (!(VerifCritereIndicePerfEnergetique(verifCritereDeBienDTO.getIndice_perf_energetique()).equals("success"))){
            checking.append(VerifCritereIndicePerfEnergetique(verifCritereDeBienDTO.getIndice_perf_energetique()));
            checking.append("\n");
        }
        if  (!(VerifCritereIsolation(verifCritereDeBienDTO.isIsolation()).equals("success"))){
            checking.append(VerifCritereIsolation(verifCritereDeBienDTO.isIsolation()));
            checking.append("\n");

        }if  (!(VerifCritereAmiante(verifCritereDeBienDTO.isAmiante()).equals("success"))){
            checking.append(VerifCritereAmiante(verifCritereDeBienDTO.isAmiante()));
            checking.append("\n");
        }
        if  (!(VerifCritereChauffage(verifCritereDeBienDTO.getChauffage()).equals("success"))){
            checking.append(VerifCritereChauffage(verifCritereDeBienDTO.getChauffage()));
            checking.append("\n");
        }
        if  (!(VerifCritereDate(verifCritereDeBienDTO.getDate_construction()).equals("success"))){
            checking.append(VerifCritereDate(verifCritereDeBienDTO.getDate_construction()));
            checking.append("\n");
        }

        if (checking.length()<2){
            return "success";
        }

        return checking.toString();


    }
}
