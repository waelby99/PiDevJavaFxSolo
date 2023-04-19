/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.fithnitek.entities;
import com.itextpdf.text.Document;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 *
 * @author waelb
 */
public class PDF {
    


    public void GeneratePdf(String filename, Sponsor s, int id) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException {

        Document document = new Document() {
        };
        PdfWriter.getInstance(document, new FileOutputStream(filename + ".pdf"));
        document.open();

        //document.add();
          
        Image image = Image.getInstance("C:/Users/waelb/OneDrive/Documents/NetBeansProjects/Fithnitek/My project(2).png" );  
        document.add(image);
        /*LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
        String formatDateTime = now.format(format);    
        document.add(new Paragraph("Date :"+format));*/
        document.add(new Paragraph("Le sponsor :"+s.getSponsore()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("_________________________________________________________________________"));

        document.add(new Paragraph("L'identifiant de ce Sponsor  :" + s.getId()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("Le nom du Société :" + s.getSponsore()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("La date de signature du contrat:" + s.getDateSignature()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("L'adresse de la société :" + s.getAdresse()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("Le montant donné par cette société :" + s.getMontant()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("L'adresse EMAIL: " + s.getEmail()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("                              Fi Thnitek                     "));
        document.close();
        Process pro = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + filename + ".pdf");
    }
}