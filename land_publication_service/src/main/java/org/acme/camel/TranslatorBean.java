package org.acme.camel;

import java.util.ArrayList;
import org.acme.DTO.ContratDeVenteBrokerDTO;
import org.acme.DTO.Service1DTO;
import org.acme.DTO.Service2DTO;
import org.acme.DTO.Service3DTO;
import org.acme.DTO.Service4DTO;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class TranslatorBean {
    public  List<Object> extractTokens(ContratDeVenteBrokerDTO exchange){

        Service1DTO service1exchange= new Service1DTO();
        Service2DTO service2exchange= new Service2DTO();
        Service3DTO service3exchange= new Service3DTO();
        Service4DTO service4exchange= new Service4DTO();

        service1exchange.setAdresse(exchange.getRue());
        service1exchange.setCode_postal(exchange.getCodePostal());
        service1exchange.setEtage(exchange.getEtage());
        service1exchange.setPorte(exchange.getPorte());
        service1exchange.setNumero_rue(exchange.getNumero_rue());
        service1exchange.setVendeur(exchange.getVendeur());

        service2exchange.setAmiante(exchange.isAmiante());
        service2exchange.setChauffage(exchange.getChauffage());
        service2exchange.setDate_construction(exchange.getDateConstruction());
        service2exchange.setIndice_perf_energetique(exchange.getIndicePerfEnergetique());
        service2exchange.setIsolation(exchange.isIsolation());
        service2exchange.setSuperficie(exchange.getSuperficie());

        service3exchange.setCode_postal(exchange.getCodePostal());
        service3exchange.setPrix(exchange.getPrix());
        service3exchange.setSuperficie(exchange.getSuperficie());

        service4exchange.setDate_compromis_vente(exchange.getDate_compromis_vente());
        service4exchange.setDate_signature_vente(exchange.getDate_signature_acte());


        List<Object> objects = new ArrayList <Object>();
        objects.add(service1exchange);
        objects.add(service2exchange);
        objects.add(service3exchange);
        objects.add(service4exchange);



        return objects;
    }
}
