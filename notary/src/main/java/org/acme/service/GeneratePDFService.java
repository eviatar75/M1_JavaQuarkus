package org.acme.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.acme.gateway.ActeDeVenteGatewayImpl;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@ApplicationScoped
public class GeneratePDFService {

    @Inject
    ActeDeVenteGatewayImpl advgi;

    /*
        Dans l'idéal trouver un contratDeVenteBrokerDTO
        Pour pouvoir avoir les différents champs du bien immobilier
     */
    public void creationPDF(String text) throws IOException {
        PDDocument pdDocument = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        pdDocument.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(pdDocument,page);
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.beginText();
        contentStream.newLineAtOffset(20, 400);
        contentStream.showText(text);
        contentStream.endText();
        contentStream.close();
        pdDocument.save("test.pdf");
        pdDocument.close();
        advgi.sendActeDeVentePDF(pdDocument);
    }
}
