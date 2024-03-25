package com.example;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import io.github.jonathanlink.PDFLayoutTextStripper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.pdfbox.io.RandomAccessFile;



public class Main {

    static String myFile = "/Users/erwinpalma/Documents/GitHub/jbtpdf01/demo/pdf.pdf";

    public static void uno() {
        String string = null;
        File file = new File(myFile, "r");
        try {
            PDFParser pdfParser = new PDFParser(new RandomAccessFile(new File(myFile), "r"));
            pdfParser.parse();
            PDDocument pdDocument = new PDDocument(pdfParser.getDocument());
            PDFTextStripper pdfTextStripper = new PDFLayoutTextStripper();
            string = pdfTextStripper.getText(pdDocument);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ;
        System.out.println(string);
    }

    public static void main(String[] args) throws IOException {
        uno();
    }
    public static void main2(String[] args) throws IOException {

        try (PDDocument document = PDDocument.load(new File(
                "/Users/erwinpalma/Documents/GitHub/jbtpdf01/demo/pdf.pdf"))) {

            document.getClass();

            if (!document.isEncrypted()) {

                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);

                PDFTextStripper tStripper = new PDFTextStripper();

                String pdfFileInText = tStripper.getText(document);
                // System.out.println("Text:" + st);

                // split by whitespace
                String lines[] = pdfFileInText.split("\\r?\\n");
                for (String line : lines) {
                    System.out.println(line);
                }

            }

        }
    }

    String file = "/Users/erwinpalma/Documents/GitHub/jbtpdf01/demo/pdf.pdf";

    @Test
    public void givenSamplePdf_whenUsingApachePdfBox_thenCompareOutput() throws IOException {

        //String expectedText = "Hello World!\n";
        File file = new File(this.file);
        PDDocument document = PDDocument.load(file);
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(document);
        document.close();

        //assertEquals(expectedText, text);
    }
}