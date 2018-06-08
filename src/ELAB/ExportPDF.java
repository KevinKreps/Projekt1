package ELAB;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;



import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;

public class ExportPDF {
    private static String FILE = "c:/temp/Rechnung.pdf";
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    
    public void start() {
    	Document document = new Document();
        try {
			PdfWriter.getInstance(document, new FileOutputStream(FILE));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        document.open();
        addMetaData(document);
        try {
			addTitlePage(document);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        document.close();
    	
    }
    
    public void addMetaData(Document document) {
        document.addTitle("Rechnungen");
        document.addSubject("ELAB");
        document.addKeywords("");
        document.addAuthor("Max Mustermann");
        document.addCreator("Max Mustermann");
    }
    
    public void addTitlePage(Document document)
            throws DocumentException {
        Paragraph preface = new Paragraph();

        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Rechnungsausdruck", catFont));

        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Generiert von: " + "\n" 
        						  + System.getProperty("user.name") + ", " + new Date(), smallBold));
        
        addEmptyLine(preface, 4);
        preface.add(new Paragraph("ID: " , smallBold));
        
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Rechnungsdatum: " , smallBold));
        
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Rechnungsname: " , smallBold));
        
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Auftraggeber: " , smallBold));
        
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Ansprechpartner: " , smallBold));
        
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Topf-ID: " , smallBold));
        
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Rechnungsbetrag: " , smallBold));
        
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Bezahlart: " , smallBold));
        
        document.add(preface);
    }
    
    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }        
    }
    
}

//public static void main(String[] args) {
//try {
//  Document document = new Document();
//  PdfWriter.getInstance(document, new FileOutputStream(FILE));
//  document.open();
//  addMetaData(document);
//  addTitlePage(document);
//  document.close();
//} catch (Exception e) {
//  e.printStackTrace();
//}
//}