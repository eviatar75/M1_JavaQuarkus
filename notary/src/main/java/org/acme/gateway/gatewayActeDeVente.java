package org.acme.gateway;

import org.acme.DTO.ContratDeVenteBrokerDTO;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.OutputStream;

public interface gatewayActeDeVente {

    void sendActeDeVente(ContratDeVenteBrokerDTO contrat);
    void sendActeDeVentePDF(PDDocument pdDocument);

}
