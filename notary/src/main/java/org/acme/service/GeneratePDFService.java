package org.acme.service;

import javax.enterprise.context.ApplicationScoped;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

@ApplicationScoped
public class GeneratePDFService {

    public PDDocument creationPDF(String text) throws IOException {
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
        return pdDocument;
    }
}
