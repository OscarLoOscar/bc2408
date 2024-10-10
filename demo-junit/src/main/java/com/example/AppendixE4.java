package com.example;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

public class AppendixE4 {

    private static void addPage28Content(PDDocument document, PDPage page ) throws IOException {
        PDDocument pdfDocument = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        pdfDocument.addPage(page);

        // Load custom fonts
        PDType0Font timesNewRoman = PDType0Font.load(pdfDocument,
                new File("/Users/oscarlo/contract_system/Times_New_Roman.ttf"));
        PDType0Font timesNewRomanBold = PDType0Font.load(pdfDocument, new File(
                "/Users/oscarlo/contract_system/Times_New_Roman_Bold.ttf"));

        try (PDPageContentStream contentStream =
                new PDPageContentStream(pdfDocument, page)) {
            float margin = 70; // Consistent left margin for alignment
            float baseStartX = margin; // Base X position for "To:" and "(1)"
            float startY = page.getMediaBox().getHeight() - 108; // Starting Y position
            float fontSize = 12;
            float leading = 14;

            // Title (centered)
            contentStream.setFont(timesNewRomanBold, 13);
            String title = "DEED OF UNDERTAKING";
            float titleWidth =
                    timesNewRomanBold.getStringWidth(title) / 1000 * 13;
            contentStream.beginText();
            contentStream.newLineAtOffset(
                    (page.getMediaBox().getWidth() - titleWidth) / 2, startY);
            contentStream.showText(title);
            contentStream.endText();

            // Add underscore
            contentStream.setLineWidth(0.5f);
            contentStream.moveTo(
                    page.getMediaBox().getWidth() / 2 - titleWidth / 2,
                    startY - 2);
            contentStream.lineTo(
                    page.getMediaBox().getWidth() / 2 + titleWidth / 2,
                    startY - 2);
            contentStream.stroke();

            startY -= 30;
            contentStream.setFont(timesNewRoman, fontSize);

            // Date (right-aligned)
            String date = "Date: {{todaysDate}}";
            float dateWidth =
                    timesNewRoman.getStringWidth(date) / 1000 * fontSize;
            contentStream.beginText();
            contentStream.newLineAtOffset(
                    page.getMediaBox().getWidth() - dateWidth - 50, startY);
            contentStream.showText(date);
            contentStream.endText();

            startY -= leading;

            // "To:" section (base for alignment)
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX, startY);
            contentStream.showText("To:");
            contentStream.endText();

            startY -= 32; // Move down for "(1)"

            // "(1)" section (aligned with "To:")
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX, startY);
            contentStream.showText("(1)");
            contentStream.endText();

            // "Venturenix Limited ("Contractor")"
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY); // Adjust spacing
            contentStream.showText("Venturenix Limited (");
            contentStream.setFont(timesNewRomanBold, fontSize);
            contentStream.showText("\"Contractor\"");
            contentStream.setFont(timesNewRoman, fontSize);
            contentStream.showText(")");
            contentStream.endText();

            // "37/F, Times Tower, 391-407 Jaffe Road, Wan Chai"
            startY -= leading;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 27, startY);
            contentStream.showText(
                    "37/F, Times Tower, 391-407 Jaffe Road, Wan Chai");
            contentStream.endText();

            // "and"
            startY -= leading + 2;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX, startY);
            contentStream.showText("and");
            contentStream.endText();

            startY -= leading * 2 + 2;
            // "(2)" section
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX, startY);
            contentStream.showText("(2)");
            contentStream.endText();

            // "The Government of the Hong Kong Special Administrative Region"
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText(
                    "The Government of the Hong Kong Special Administrative Region");
            contentStream.endText();

            // "as represented by Commissioner for Digital Policy ("
            startY -= leading + 2;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText(
                    "as represented by Commissioner for Digital Policy (");
            contentStream.setFont(timesNewRomanBold, fontSize);
            contentStream.showText("\"Government\"");
            contentStream.setFont(timesNewRoman, fontSize);
            contentStream.showText(")");
            contentStream.endText();

            // "[8/F., Shui On Centre, 6-8 Harbour Road, Wanchai, Hong Kong.]"
            startY -= leading;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 30, startY);
            contentStream.showText(
                    "[8/F., Shui On Centre, 6-8 Harbour Road, Wanchai, Hong Kong.]");
            contentStream.endText();

            // "Re:" section
            startY -= leading * 3 + 2;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX, startY);
            contentStream.showText("Re:");
            contentStream.endText();

            // "Contract for the Supply Services of ..."
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText(
                    "Contract for the Supply Services of Information Technology Contract Staff to");
            contentStream.endText();

            // "the Government"
            startY -= leading;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText("the Government");
            contentStream.endText();

            // Paragraph 1
            startY -= leading * 2 + 5;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX, startY);
            contentStream.setWordSpacing(2.0f); // Increase word spacing for justification
            contentStream.showText("1.");
            contentStream.endText();
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText(
                    "I have read a copy of clause 1.1 of Part I - Terms of Tender, clause 21 of Part");
            contentStream.endText();
            startY -= leading * 1.6;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText(
                    "IV - Conditions of Contract and clauses 1 and 3 of Part VI - Specifications of");
            contentStream.endText();
            startY -= leading * 1.6;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText(
                    "the T26 Contract signed between the Contractor and the Government dated");
            contentStream.endText();
            startY -= leading * 1.6;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText("13 January 2023 (");
            contentStream.setFont(timesNewRomanBold, fontSize);
            contentStream.showText("\"Contract\"");
            contentStream.setFont(timesNewRoman, fontSize);
            contentStream.showText(") in relation to the supply services of");
            contentStream.endText();
            startY -= leading * 1.6;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText(
                    "information technology contract staff to the Government for the period from");
            contentStream.endText();
            startY -= leading * 1.6;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText(
                    "1 February 2023 to 31 January 2027. Unless specified otherwise, terms and");
            contentStream.endText();
            startY -= leading * 1.6;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText(
                    "expressions defined for the purposes of the Contract have the same meanings");
            contentStream.endText();
            startY -= leading * 1.6;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText("when used in this Deed.");
            contentStream.endText();

            // Paragraph 2
            startY -= leading * 3 + 5;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX, startY);
            contentStream.showText("2.");
            contentStream.endText();
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText(
                    "I hereby undertake, acknowledge and agree in favour of the Contractor and");
            contentStream.endText();
            startY -= leading;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText(
                    "the Government jointly and severally the following duty of confidentiality");
            contentStream.endText();
            startY -= leading;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText("that:");
            contentStream.endText();
            startY -= leading * 2 + 5;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText("2.1");
            contentStream.endText();
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 63, startY);
            contentStream.showText(
                    "all Confidential Information as defined in clause 6 of this Deed shall be");
            contentStream.endText();
            startY -= leading;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 63, startY);
            contentStream.showText("treated as confidential; and");
            contentStream.endText();
            startY -= leading * 2;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText("2.2");
            contentStream.endText();
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 61, startY);
            contentStream.showText(
                    "I shall not, during the continuance of this Deed or at any time thereafter,");
            contentStream.endText();
            startY -= leading;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 63, startY);
            contentStream.showText(
                    "disclose to any person any Confidential Information.");
            contentStream.endText();

            // Paragraph 3
            startY -= leading * 2;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX, startY);
            contentStream.showText("3.");
            contentStream.endText();
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            startY -= leading + 3;
            contentStream.showText(
                    "I agree that in the event of any breach or threatened breach of the terms of");
            contentStream.endText();
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText(
                    "this Deed money damages are unlikely to be a sufficient remedy and the");
            contentStream.endText();
            startY -= leading + 3;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText(
                    "Contractor and/or the Government shall be entitled, in the discretion of the");
            contentStream.endText();
            startY -= leading + 3;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText(
                    "Court, to an injunction to restrain the said breach or threatened breach in");
            contentStream.endText();
            contentStream.beginText();


            contentStream.setWordSpacing(0); // Reset word spacing
        }

        pdfDocument.save(outputPath);
        pdfDocument.close();
    }


    private static void addPage29Content(PDDocument document, PDPage page) throws IOException {
        PDDocument pdfDocument = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        pdfDocument.addPage(page);

        // Load custom fonts
        PDType0Font timesNewRoman = PDType0Font.load(pdfDocument,
                new File("/Users/oscarlo/contract_system/Times_New_Roman.ttf"));
        PDType0Font timesNewRomanBold = PDType0Font.load(pdfDocument, new File(
                "/Users/oscarlo/contract_system/Times_New_Roman_Bold.ttf"));

        try (PDPageContentStream contentStream =
                new PDPageContentStream(pdfDocument, page)) {
            float margin = 70; // Consistent left margin for alignment
            float baseStartX = margin; // Base X position for alignment
            float startY = page.getMediaBox().getHeight() - 75; // Starting Y position
            float fontSize = 12;
            float leading = 14;
            contentStream.setFont(timesNewRoman, 12);
            // Content Paragraphs
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText(
                    "addition to and not in lieu of any other equitable or any legal relief including");
            contentStream.endText();
            startY -= leading;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText("damages.");
            contentStream.endText();
            // Paragraph 4
            startY -= leading * 2 + 4; // Add space before starting new paragraph
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX, startY);
            contentStream.showText("4.");
            contentStream.endText();
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText(
                    "Each of the Government and the Contractor shall be entitled to enforce any");
            contentStream.endText();
            startY -= leading * 1.07;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText(
                    "or all of its rights under this Deed either alone or jointly with the other.");
            contentStream.endText();

            // Paragraph 5
            startY -= leading * 2; // Add space before new paragraph
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX, startY);
            contentStream.showText("5.");
            contentStream.endText();
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText(
                    "This Deed shall be governed by and construed according to the laws of Hong");
            contentStream.endText();
            startY -= leading * 1.2;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText(
                    "Kong and the parties irrevocably submit to the exclusive jurisdiction of the");
            contentStream.endText();
            startY -= leading * 1.2;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText("Courts of Hong Kong.");
            contentStream.endText();
            // Paragraph 6
            startY -= leading * 2; // Add space before new paragraph
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX, startY);
            contentStream.showText("6.");
            contentStream.endText();
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText(
                    "\"Confidential Information\" means information contained in the Materials and");
            contentStream.endText();
            startY -= leading * 1.07;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText(
                    "all materials and data furnished by or on behalf of the Government which is");
            contentStream.endText();
            startY -= leading * 1.07;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText(
                    "received by the Contractor or comes to the Contractor’s knowledge in");
            contentStream.endText();
            startY -= leading * 1.07;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText(
                    "connection with the Contract including but not limited to the terms and");
            contentStream.endText();
            startY -= leading * 1.07;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText(
                    "conditions of the Contract. \\\"Materials\\\" includes but is not limited to all the");
            contentStream.endText();
            startY -= leading * 1.07;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText(
                    "programmes data information or materials collected compiled developed,");
            contentStream.endText();
            startY -= leading * 1.07;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText(
                    "produced or created by or on behalf of the Contractor its sub-contractors,");
            contentStream.endText();
            startY -= leading * 1.07;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText(
                    "Relevant Employees and their directors officers employees agents or sub-");
            contentStream.endText();
            startY -= leading * 1.07;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText(
                    "contractors (whether individually or jointly with the Government) in relation");
            contentStream.endText();
            startY -= leading * 1.07;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText(
                    "to and/or in the course of the performance of the Service or for the purpose of");
            contentStream.endText();
            startY -= leading * 1.07;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText(
                    "the Contract which are recorded or stored by whatever means in whatever");
            contentStream.endText();
            startY -= leading * 1.07;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText(
                    "form or media any material relating to programming documentation");
            contentStream.endText();
            startY -= leading * 1.07;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText(
                    "including source and object code listings originated and first prepared for the");
            contentStream.endText();
            startY -= leading * 1.07;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText(
                    "Government by the Relevant Employee, the pre-contractual and contractual ");
            contentStream.endText();
            startY -= leading * 1.07;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText(
                    "documents and all the drafts uncompleted versions and working papers of");
            contentStream.endText();
            startY -= leading * 1.07;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 40, startY);
            contentStream.showText("any of the above items.");
            contentStream.endText();
            // Footer Section - "IN WITNESS..."
            startY -= leading * 2; // Space before footer
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX, startY);
            contentStream.showText(
                    "IN WITNESS whereof this document has been duly executed as a Deed the day,");
            contentStream.endText();
            startY -= leading * 1.07;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX, startY);
            contentStream.showText("month and year first above written.");
            contentStream.endText();
            // Signature Section
            startY -= leading * 3 ; // Space before signature
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX, startY);
            contentStream.showText(
                    "SIGNED SEALED and DELIVERED by                     )");
            contentStream.endText();

            // Placeholder for Name
            startY -= leading * 1.07;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX, startY);
            contentStream.showText("Name: {{candidate name}}");
            contentStream.endText();

            startY -= leading * 1.407;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX + 20, startY);
            contentStream.showText(
                    "                                                                                 )");
            contentStream.endText();

            // "in the presence of:"
            startY -= leading * 1.07;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX, startY);
            contentStream.showText(
                    "in the presence of:                                                          )");
            contentStream.endText();

            // Placeholder for Witness Name
            startY -= leading * 4;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX, startY);
            contentStream.showText("Name:");
            contentStream.endText();

            startY -= leading * 1.07;
            contentStream.beginText();
            contentStream.newLineAtOffset(baseStartX , startY);
            contentStream.showText("LAW, SIN MAN");
            contentStream.endText();
        }

        pdfDocument.save(outputPath);
        pdfDocument.close();
    }

    public static void main(String[] args) throws IOException {
        String outputPath = "/Users/oscarlo/contract_system/"
                + LocalDate.now().toString() + "_output.pdf";
        String candidateName = "John Doe";
        String todayDate = LocalDate.now().format(
                DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH));

        // page29(outputPath);
        page30(outputPath);
        System.out.println("finish output");
    }
}
