package org.acme.service;

import org.acme.DTO.dateAVerifDTO;

import java.time.LocalDate;

public class ServiceVerifDate {

    public boolean verifDate(LocalDate date_compromis_vente, LocalDate date_signature_vente){

        boolean res = date_signature_vente.isAfter(date_compromis_vente.plusMonths(2));

        if (res){

            System.out.println("Vérification date success");


        }else
            System.out.println("La vérif de la date n'eest pas passé");


        return res;

    } 

    public String checkService2(dateAVerifDTO verifCritereDeBienDTO){
        return(verifDate(verifCritereDeBienDTO.getDate_compromis_vente(), verifCritereDeBienDTO.getDate_signature_vente()))
        ?"success":"La date de signature de vente doit être au minimum 2 mois après la date de compromis de vente";
    }
}
