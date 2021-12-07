package org.acme.gateway;

import org.acme.DTO.ContratDeVenteBrokerDTO;
import org.acme.DTO.ContratPostDTO;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.OutputStream;

public interface gatewayActeDeVente {

    void sendActeDeVente(ContratDeVenteBrokerDTO contrat);
    void sendPDF(PDDocument pdDocument);
    void saveActeDeVente(ContratPostDTO contrat, int id);
}
