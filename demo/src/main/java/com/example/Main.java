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

    //static String myFile = "/Users/erwinpalma/Documents/GitHub/jbtpdf01/demo/pdf.pdf";
    static String myFile = "";
    static String example = "";

    public static void readPDF() {
        String string = null;
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
        if (getArgs(args)) {

            try {
                if (args[2].contains("-example")) {
                    example2();
                } else {
                    readPDF();
                }
            } catch (Exception e) {
                readPDF();
            }
        }
    }

    public static boolean getArgs(String[] args) throws IOException {
        // Checking if length of args array is
        // greater than 0
        String fileArg = "";
        String fileArgValue = "";

        if (args.length > 0) {
            fileArg = args[0];
            fileArgValue = args[1];

            

            if (fileArg.contains("-f")) {
                File f = new File(args[1]);
                if (f.exists()) {
                    // Show if the file exists
                    System.out.println("Exists");
                    myFile = fileArgValue;
                    return true;
                } else {
                    // Show if the file does not exists
                    System.out.println("Does not Exists");
                    return false;
                }
            } else {
                System.out.println("Arguments -f not found.");
                return false;
            }

        } else {
            // Print statements
            System.out.println("No command line arguments found.");
            return false;
        }
        //return false;
    }

    public static void example2() throws IOException {

        try (PDDocument document = PDDocument.load(new File(myFile))) {

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
}