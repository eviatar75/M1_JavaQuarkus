package org.acme.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.acme.DTO.ContratPostDTO;
import org.acme.domain.ActeDeVente;
import org.acme.domain.Personne;
import org.acme.gateway.ActeDeVenteGatewayImpl;
import org.apache.camel.Exchange;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Calendar;

@ApplicationScoped
public class PDFService {

    @Inject
    ActeDeVenteGatewayImpl gateway;

    @Inject
    MailService serviceMailer;

    @Transactional
    public void creationPDF(int idActe) throws Exception {

        try {


            String json = Files.readString(Path.of("saveContratPost/queue/" + idActe + ".dto"));
            System.out.println("\n\n\n" + json + "\n\n\n");
            ObjectMapper obj = new ObjectMapper();
            obj.findAndRegisterModules();
            ContratPostDTO isUnmarshal = obj.readValue(json, ContratPostDTO.class);
            System.out.println(isUnmarshal);


            PDDocument pdDocument = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A4);
            pdDocument.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(pdDocument, page);
            PDImageXObject pdImage = PDImageXObject.createFromFile("data/image/up1.jpg", pdDocument);
            contentStream.drawImage(pdImage, 115, 675);
            contentStream.beginText();

            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.setLeading(14.5f);
            contentStream.newLineAtOffset(25, 700);
            contentStream.newLine();
            contentStream.newLine();
            String line1 = "Acheteur : ";
            contentStream.showText(line1);

            contentStream.newLine();


            contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);

            String line2 = "Pr??nom : "
                    + isUnmarshal.getAcheteur().getPrenom();
            contentStream.showText(line2);
            contentStream.newLine();

            String line3 = "Nom : "
                    + isUnmarshal.getAcheteur().getNom();
            contentStream.showText(line3);
            contentStream.newLine();

            String line4 = "S??curit?? sociale : "
                    + isUnmarshal.getAcheteur().getSecurite_sociale();
            contentStream.showText(line4);
            contentStream.newLine();

            String line5 = "Adresse : "
                    + isUnmarshal.getAcheteur().getNumero_rue() + " " + isUnmarshal.getAcheteur().getAdresse() + ", " + isUnmarshal.getAcheteur().getCode_postal();
            contentStream.showText(line5);
            contentStream.newLine();

            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.newLine();
            String line6 = "Vendeur : ";
            contentStream.showText(line6);

            contentStream.newLine();


            contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);

            String line7 = "Pr??nom : "
                    + isUnmarshal.getVendeur().getPrenom();
            contentStream.showText(line7);
            contentStream.newLine();

            String line8 = "Nom : "
                    + isUnmarshal.getVendeur().getNom();
            contentStream.showText(line8);
            contentStream.newLine();

            String line9 = "S??curit?? sociale : "
                    + isUnmarshal.getVendeur().getSecurite_sociale();
            contentStream.showText(line9);
            contentStream.newLine();

            String line10 = "Adresse : "
                    + isUnmarshal.getVendeur().getNumero_rue() + " " + isUnmarshal.getVendeur().getAdresse() + ", " + isUnmarshal.getVendeur().getCode_postal();
            contentStream.showText(line10);
            contentStream.newLine();

            if (!isUnmarshal.getAncienProprietaires().isEmpty()) {
                contentStream.newLine();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                String line11 = "Anciens propri??taires : ";
                contentStream.showText(line11);
                contentStream.newLine();
                contentStream.newLine();
            }

            contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);



            for (Personne personne:isUnmarshal.getAncienProprietaires() ){
                String lineproprio="Nom : "+personne.getNom();
                String lineproprio2="Pr??nom : "+personne.getPrenom();
                String lineproprio3="Date de naissance : "+personne.getDateNaissance().toString();
                contentStream.showText(lineproprio);
                contentStream.newLine();
                contentStream.showText(lineproprio2);
                contentStream.newLine();
                contentStream.showText(lineproprio3);
                contentStream.newLine();


            }


            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            String line12 = "Descriptif du bien : ";
            contentStream.newLine();
            contentStream.showText(line12);
            contentStream.newLine();

            contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);

            String line13 = "Adresse : "
                    + isUnmarshal.getNumero_rue() + " " + isUnmarshal.getRue() + ", " + isUnmarshal.getCode_postal() + " ?? la porte " + isUnmarshal.getPorte();
            contentStream.showText(line13);
            contentStream.newLine();

            String line14 = "Prix : "
                    + isUnmarshal.getPrix();
            contentStream.showText(line14);
            contentStream.newLine();

            String line15 = "Nombre de pi??ces : "
                    + isUnmarshal.getNb_piece();
            contentStream.showText(line15);
            contentStream.newLine();

            String line16 = "Superficie : "
                    + isUnmarshal.getSuperficie() + " m2";
            contentStream.showText(line16);
            contentStream.newLine();

            String line17 = "Indice de performance ??nerg??tique : "
                    + isUnmarshal.getIndice_perf_energetique();
            contentStream.showText(line17);
            contentStream.newLine();

            String line18 = "Chauffage : "
                    + isUnmarshal.getChauffage();
            contentStream.showText(line18);
            contentStream.newLine();

            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.newLine();
            String line19 = "Dates : ";
            contentStream.showText(line19);
            contentStream.newLine();

            contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);

            String line20 = "Date de construction : "
                    + isUnmarshal.getDate_construction();
            contentStream.showText(line20);
            contentStream.newLine();

            String line21 = "Date de compromis de la vente : "
                    + isUnmarshal.getDate_compromis_vente();
            contentStream.showText(line21);
            contentStream.newLine();

            String line22 = "Date de signature de la vente : "
                    + isUnmarshal.getDate_signature_vente();
            contentStream.showText(line22);
            contentStream.newLine();

            contentStream.endText();
            contentStream.close();


            PDPage page2 = new PDPage(PDRectangle.A4);
            pdDocument.addPage(page2);
            PDPageContentStream contentStream2 = new PDPageContentStream(pdDocument, page2);
            PDImageXObject pdImage2 = PDImageXObject.createFromFile("data/image/picture_1.jpeg", pdDocument);
            contentStream2.drawImage(pdImage2, 70, 200);
            contentStream2.close();


            pdDocument.save("data/pdf/Recap_Acte_De_Vente_" + idActe + ".pdf");
            pdDocument.close();

            ActeDeVente acte = ActeDeVente.findById((long)idActe);
            acte.setStatuePdf(true);
            acte.setUrlPdf("data/pdf/Recap_Acte_De_Vente_" + idActe + ".pdf");
            gateway.sendPDF(pdDocument);
            serviceMailer.sendEmail(isUnmarshal.getAcheteur().getMail(), "data/pdf/Recap_Acte_De_Vente_" + idActe + ".pdf",isUnmarshal.getAcheteur().getNom());
            serviceMailer.sendEmail(isUnmarshal.getVendeur().getMail(), "data/pdf/Recap_Acte_De_Vente_" + idActe + ".pdf",isUnmarshal.getVendeur().getNom());
            acte.setStatutMail(true);

        } catch (Exception e ) {

            throw new Exception("impossible de cr??e le mail et de l'envoyer");

        }
    }

    @Transactional
    public void creationPDF2(Exchange message) throws Exception {

        try {

            String json = Files.readString(Path.of("saveContratPost/queue/" + message.getIn().getHeader("ActeID") + ".dto"));
            System.out.println("\n\n\n" + json + "\n\n\n");
            ObjectMapper obj = new ObjectMapper();
            obj.findAndRegisterModules();
            ContratPostDTO isUnmarshal = obj.readValue(json, ContratPostDTO.class);
            System.out.println(isUnmarshal);



            PDDocument pdDocument = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A4);
            pdDocument.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(pdDocument, page);

            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 7);
            contentStream.setLeading(14.5f);
            contentStream.newLineAtOffset(25, 700);


            String line0 = message.getIn().getBody(String.class);
            String line1= line0.substring(1,line0.length()-1);
            String[] words = line1.split("\"\"");

            for (String word : words){
                contentStream.showText(word);
                contentStream.newLine();
            }





            contentStream.endText();
            contentStream.close();

            pdDocument.save("data/pdf/Recap_Acte_De_Vente_" + message.getIn().getHeader("ActeID") + ".pdf");
            pdDocument.close();






            ActeDeVente acte = ActeDeVente.findById((long)((Integer.parseInt((String) message.getIn().getHeader("ActeID")))));
            acte.setStatuePdf(true);
            acte.setUrlPdf("data/pdf/Recap_Acte_De_Vente_" + message.getIn().getHeader("ActeID") + ".pdf");
            gateway.sendPDF(pdDocument);
            serviceMailer.sendEmail(isUnmarshal.getAcheteur().getMail(), "data/pdf/Recap_Acte_De_Vente_" + message.getIn().getHeader("ActeID")  + ".pdf",isUnmarshal.getAcheteur().getNom());
            serviceMailer.sendEmail(isUnmarshal.getVendeur().getMail(), "data/pdf/Recap_Acte_De_Vente_" + message.getIn().getHeader("ActeID")  + ".pdf",isUnmarshal.getVendeur().getNom());
            acte.setStatutMail(true);

        } catch (Exception e ) {

            throw new Exception("impossible de cr??e le mail et de l'envoyer");

        }
    }



    public File recuperationPdf(int idActeDeVente){
        try {
            return new File("data/pdf/Recap_Acte_De_Vente_" + idActeDeVente + ".pdf");
        }catch (Exception e){
           throw new NotFoundException("Nous n'avons trouv?? aucun acte de vente pour cette id"+idActeDeVente);
        }
    }
}
