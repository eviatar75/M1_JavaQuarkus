package org.acme.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.acme.DTO.ContratDeVenteBrokerDTO;
import org.acme.DTO.ContratPostDTO;
import org.acme.gateway.ActeDeVenteGatewayImpl;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@ApplicationScoped
public class GeneratePDFService {

    @Inject
    ActeDeVenteGatewayImpl gateway;

    /*
        Dans l'idéal trouver un contratDeVenteBrokerDTO
        Pour pouvoir avoir les différents champs du bien immobilier
     */
    public void creationPDF(int idActe) throws IOException {

        String json= Files.readString(Path.of("saveContratPost/queue/"+idActe+".dto"));
        System.out.println("\n\n\n"+json+"\n\n\n");
        ObjectMapper obj = new ObjectMapper();
        obj.findAndRegisterModules();
        ContratPostDTO isUnmarshal = obj.readValue( json, ContratPostDTO.class);
        System.out.println(isUnmarshal);





        PDDocument pdDocument = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        pdDocument.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(pdDocument,page);
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.beginText();
        contentStream.newLineAtOffset(20, 400);
        contentStream.showText(json);
        contentStream.endText();
        contentStream.close();
        pdDocument.save("test.pdf");
        pdDocument.close();
        gateway.sendActeDeVentePDF(pdDocument);
    }
}
