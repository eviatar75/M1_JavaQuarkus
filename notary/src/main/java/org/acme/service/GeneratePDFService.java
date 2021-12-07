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

        contentStream.beginText();
        /*contentStream.newLineAtOffset(0,);
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.showText("Récapitulatif de l'acte de vente !");

        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.showText("Nom et prénom de l'acheteur : ");
        contentStream.endText();*/


        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(25, 700);
        String line1 = "Acheteur : ";
        contentStream.showText(line1);

        contentStream.newLine();


        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);

        String line2 = "Prénom : "
                + isUnmarshal.getAcheteur().getPrenom();
        contentStream.showText(line2);
        contentStream.newLine();

        String line3 = "Nom : "
                + isUnmarshal.getAcheteur().getNom();
        contentStream.showText(line3);
        contentStream.newLine();

        String line4 = "Sécurité sociale : "
                + isUnmarshal.getAcheteur().getSecurite_sociale();
        contentStream.showText(line4);
        contentStream.newLine();

        String line5 = "Adresse : "
                + isUnmarshal.getAcheteur().getNumero_rue()+" "+isUnmarshal.getAcheteur().getAdresse()+", "+isUnmarshal.getAcheteur().getCode_postal();
        contentStream.showText(line5);
        contentStream.newLine();

        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.newLine();
        String line6 = "Vendeur : ";
        contentStream.showText(line6);

        contentStream.newLine();


        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);

        String line7 = "Prénom : "
                + isUnmarshal.getVendeur().getPrenom();
        contentStream.showText(line7);
        contentStream.newLine();

        String line8 = "Nom : "
                + isUnmarshal.getVendeur().getNom();
        contentStream.showText(line8);
        contentStream.newLine();

        String line9 = "Sécurité sociale : "
                + isUnmarshal.getVendeur().getSecurite_sociale();
        contentStream.showText(line9);
        contentStream.newLine();

        String line10 = "Adresse : "
                + isUnmarshal.getVendeur().getNumero_rue()+" "+isUnmarshal.getVendeur().getAdresse()+", "+isUnmarshal.getVendeur().getCode_postal();
        contentStream.showText(line10);
        contentStream.newLine();

        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
        contentStream.newLine();
        String line11 = "Anciens propriétaires : " + isUnmarshal.getAncienProprietaires();
        contentStream.showText(line11);
        contentStream.newLine();

        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        String line12 = "Descriptif du bien : ";
        contentStream.newLine();
        contentStream.showText(line12);
        contentStream.newLine();

        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);

        String line13 = "Adresse : "
                + isUnmarshal.getNumero_rue()+" "+isUnmarshal.getRue()+", "+isUnmarshal.getCode_postal()+" à la porte "+isUnmarshal.getPorte();
        contentStream.showText(line13);
        contentStream.newLine();

        String line14 = "Prix : "
                + isUnmarshal.getPrix();
        contentStream.showText(line14);
        contentStream.newLine();

        String line15 = "Nombre de pièces : "
                + isUnmarshal.getNb_piece();
        contentStream.showText(line15);
        contentStream.newLine();

        String line16 = "Superficie : "
                + isUnmarshal.getSuperficie()+" m2";
        contentStream.showText(line16);
        contentStream.newLine();

        String line17 = "Indice de performance énergétique : "
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
        pdDocument.save("data/pdf/Recap_Acte_De_Vente_"+idActe+".pdf");

        pdDocument.close();
        gateway.sendActeDeVentePDF(pdDocument);
    }
}
