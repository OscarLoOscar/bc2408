package com.example;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.pdfbox.multipdf.LayerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;

public class VenturenixOfferLetterPDFGenerator {

  /**
   * This array defines three columns:
   * 
   * The first column is 150 units wide .
   * 
   * The second column is 20 units wide .
   * 
   * The third column takes up the remaining space (tableWidth - 170)
   */
  final static float firstColumnWide = 130f;
  final static float secondColumnWide = 20f;
  private static PDType0Font timesNewRoman;
  private static PDType0Font timesNewRomanBold;

  public static void generateOfferLetter(String templatePath, //
      String fullTemplatePath, //
      ByteArrayOutputStream outputStream, // String outputPath,
      Map<String, String> dynamicData) throws IOException {
    try (PDDocument templateDoc = PDDocument.load(new File(templatePath));
        PDDocument outputDocument = new PDDocument();
        PDDocument fullTemplateDoc =
            PDDocument.load(new File(fullTemplatePath))) {


      timesNewRoman = PDType0Font.load(templateDoc,
          new File("/Users/oscarlo/contract_system/Times_New_Roman.ttf"));

      timesNewRomanBold = PDType0Font.load(templateDoc,
          new File("/Users/oscarlo/contract_system/Times_New_Roman_Bold.ttf"));

      LayerUtility layerUtility = new LayerUtility(outputDocument);

      // Page 1
      PDPage page1 = new PDPage(PDRectangle.A4);
      outputDocument.addPage(page1);
      addTemplateToPage(templateDoc, outputDocument, page1, layerUtility);
      addPage1Content(outputDocument, page1, dynamicData);

      // Page 2
      PDPage page2 = new PDPage(PDRectangle.A4);
      outputDocument.addPage(page2);
      addTemplateToPage(templateDoc, outputDocument, page2, layerUtility);
      addPage2Content(outputDocument, page2, dynamicData);

      // Page 3
      PDPage page3 = new PDPage(PDRectangle.A4);
      outputDocument.addPage(page3);
      addTemplateToPage(templateDoc, outputDocument, page3, layerUtility);
      addPage3Content(outputDocument, page3, dynamicData);

      // Page 4
      PDPage page4 = new PDPage(PDRectangle.A4);
      outputDocument.addPage(page4);
      addTemplateToPage(templateDoc, outputDocument, page4, layerUtility);
      addPage4Content(outputDocument, page4, dynamicData);

      addPageNumber(outputDocument, page1, 1);
      addPageNumber(outputDocument, page2, 2);
      addPageNumber(outputDocument, page3, 3);
      addPageNumber(outputDocument, page4, 4);

      // Pages 5 to 27: Load from full template PDF
      for (int i = 0; i < fullTemplateDoc.getNumberOfPages(); i++) {
        PDPage page = fullTemplateDoc.getPage(i);
        outputDocument.addPage(page);
      }

      // Page 28: Add manually created content
      PDPage page28 = new PDPage(PDRectangle.A4);
      outputDocument.addPage(page28);
      addPage28Content(outputDocument, page28);

      // Page 29: Add manually created content
      PDPage page29 = new PDPage(PDRectangle.A4);
      outputDocument.addPage(page29);
      addPage29Content(outputDocument, page29, dynamicData);

      PDPage page19 = outputDocument.getPage(18); // Pages are zero-indexed
      PDPage page20 = outputDocument.getPage(19);

      insertNameOnPage(outputDocument, page19, dynamicData.get("Name"), 162,
          540); // Adjust x, y to correct position
      insertNameAndHKIDOnPage(outputDocument, page20, dynamicData.get("Name"), //
          dynamicData.get("HKID"), 162, 270, 162, 240, 162, 175);

      outputDocument.save(outputStream);
    }

  }

  private static void insertNameOnPage(PDDocument document, PDPage page,
      String name, float x, float y) throws IOException {
    try (PDPageContentStream contentStream = new PDPageContentStream(document,
        page, PDPageContentStream.AppendMode.APPEND, true, true)) {
      contentStream.setFont(timesNewRoman, 12); // Set your desired font and size
      contentStream.beginText();
      contentStream.newLineAtOffset(x, y); // Specify the position
      contentStream.showText(name);
      contentStream.endText();
    }
  }

  private static void insertNameAndHKIDOnPage(PDDocument document, PDPage page,
      String name, String hkid, float xName, float yName, float xHKID,
      float yHKID, float xDate, float yDate) throws IOException {
    try (PDPageContentStream contentStream = new PDPageContentStream(document,
        page, PDPageContentStream.AppendMode.APPEND, true, true)) {
      contentStream.setFont(timesNewRoman, 12); // Set your desired font and size

      // Insert Name
      contentStream.beginText();
      contentStream.newLineAtOffset(xName, yName); // Position for Name
      contentStream.showText(name);
      contentStream.endText();

      // Insert HKID
      contentStream.beginText();
      contentStream.newLineAtOffset(xHKID, yHKID); // Position for HKID
      contentStream.showText(hkid);
      contentStream.endText();

      // Insert Date
      contentStream.beginText();
      contentStream.newLineAtOffset(xDate, yDate); // Position for HKID
      contentStream.showText(LocalDate.now()
          .format(DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH))
          .toString());
      contentStream.endText();
    }
  }

  private static void addTemplateToPage(PDDocument templateDoc,
      PDDocument outputDocument, PDPage page, LayerUtility layerUtility)
      throws IOException {
    PDFormXObject form = layerUtility.importPageAsForm(templateDoc, 0);
    try (PDPageContentStream contentStream =
        new PDPageContentStream(outputDocument, page,
            PDPageContentStream.AppendMode.APPEND, true, true)) {
      contentStream.drawForm(form);
    }
  }

  private static void addPage1Content(PDDocument document, PDPage page,
      Map<String, String> dynamicData) throws IOException {
    try (PDPageContentStream contentStream = new PDPageContentStream(document,
        page, PDPageContentStream.AppendMode.APPEND, true, true)) {
      float margin = 50;
      float startY = page.getMediaBox().getHeight() - margin;
      float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
      float yPosition = startY - 120;

      contentStream.beginText();
      contentStream.setFont(timesNewRomanBold, 10);
      contentStream.newLineAtOffset(margin, yPosition);
      contentStream.showText("PRIVATE & CONFIDENTIAL");
      contentStream.endText();
      float privateConfidentialWidth =
          timesNewRoman.getStringWidth("PRIVATE & CONFIDENTIAL   ") / 1000 * 10;
      contentStream.setLineWidth(1f);
      contentStream.moveTo(margin, yPosition - 2); // Adjust yPosition as needed
      contentStream.lineTo(margin + privateConfidentialWidth, yPosition - 2);
      contentStream.stroke();

      yPosition -= 15;
      contentStream.beginText();
      contentStream.setFont(PDType1Font.TIMES_ROMAN, 10);
      contentStream.newLineAtOffset(margin, yPosition);
      contentStream.showText(dynamicData.getOrDefault("Name", ""));
      contentStream.endText();
      yPosition -= 15;

      contentStream.beginText();
      contentStream.setFont(PDType1Font.TIMES_ROMAN, 10);
      contentStream.newLineAtOffset(margin, yPosition);
      contentStream
          .showText("HKID no.:" + dynamicData.getOrDefault("HKID", ""));
      contentStream.endText();
      yPosition -= 25;

      LocalDate date = LocalDate.now(); // Set the year to 2024
      DateTimeFormatter formatter =
          DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH);
      String formattedDate = date.format(formatter);

      contentStream.beginText();
      contentStream.setFont(PDType1Font.TIMES_ROMAN, 10);
      contentStream.newLineAtOffset(margin, yPosition);
      contentStream.showText(formattedDate);
      contentStream.endText();
      yPosition -= 25;

      contentStream.beginText();
      contentStream.setFont(PDType1Font.TIMES_ROMAN, 10);
      contentStream.newLineAtOffset(margin, yPosition);
      contentStream
          .showText("Dear " + dynamicData.getOrDefault("Name", "") + " , ");
      contentStream.endText();
      yPosition -= 25;

      contentStream.beginText();
      contentStream.setFont(PDType1Font.TIMES_ROMAN, 10);
      contentStream.newLineAtOffset(margin, yPosition);
      contentStream.showText(
          "We are pleased to offer you a position with Venturenix Limited with the following terms and conditions: ");
      contentStream.endText();
      yPosition -= 25;

      String[] rowTitles = {"Contract Period", "Position", "Place of Work",
          "Salary", "Payroll Administration", "Working Hours"};
      /**
       * This array defines three columns:
       * 
       * The first column is 150 units wide .
       * 
       * The second column is 20 units wide .
       * 
       * The third column takes up the remaining space (tableWidth - 170)
       */
      float[] columnWidths =
          {firstColumnWide, secondColumnWide, tableWidth - 170};

      for (int i = 0; i < rowTitles.length; i++) {
        drawTableRow(contentStream, margin, yPosition, columnWidths,
            new String[] {rowTitles[i], ":", ""}, PDType1Font.TIMES_ROMAN, 10);
        yPosition = addMultiLineText(contentStream,
            margin + columnWidths[0] + columnWidths[1], yPosition,
            columnWidths[2], getTextForPage1Row(i, dynamicData),
            PDType1Font.TIMES_ROMAN, 10);
        // yPosition -= 0.5f;
      }
      yPosition += 10;
      contentStream.beginText();
      contentStream.newLineAtOffset(margin + columnWidths[0] + columnWidths[1],
          yPosition);
      contentStream.showText("Your normal working hours:");
      contentStream.endText();

      float leading = 14;

      yPosition -= leading; // Adjust line spacing
      contentStream.beginText();
      contentStream.newLineAtOffset(margin + columnWidths[0] + columnWidths[1],
          yPosition);
      contentStream.showText(dynamicData.get("WorkingHours"));
      contentStream.endText();

      yPosition -= leading; // Adjust line spacing
      contentStream.beginText();
      contentStream.newLineAtOffset(margin + columnWidths[0] + columnWidths[1],
          yPosition);
      contentStream.showText(
          "Subject to " + dynamicData.get("department") + " arrangement");
      contentStream.endText();

      yPosition -= leading; // Adjust line spacing
      contentStream.beginText();
      contentStream.newLineAtOffset(margin + columnWidths[0] + columnWidths[1],
          yPosition);
      contentStream
          .showText("Forty-four (44) hours per week including meal breaks.");
      contentStream.endText();
    }
  }

  private static void addPage2Content(PDDocument document, PDPage page,
      Map<String, String> dynamicData) throws IOException {
    try (PDPageContentStream contentStream = new PDPageContentStream(document,
        page, PDPageContentStream.AppendMode.APPEND, true, true)) {
      float margin = 50;
      float startY = page.getMediaBox().getHeight() - margin;
      float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
      float yPosition = startY - 110;

      String[] rowTitles = {"Overtime Payment",
          "Typhoon/ Rainstorm\nWarning Arrangement", "Annual Leave", "Holiday",
          "Rest day", "Sick Leave", "Leave of Absence"};
      float[] columnWidths =
          {firstColumnWide, secondColumnWide, tableWidth - 170};

      for (int i = 0; i < rowTitles.length; i++) {
        yPosition = drawMultiLineTableRow(contentStream, margin, yPosition,
            columnWidths, new String[] {rowTitles[i], ":", ""},
            PDType1Font.TIMES_ROMAN, 10);
        yPosition = addMultiLineText(contentStream,
            margin + columnWidths[0] + columnWidths[1], yPosition,
            columnWidths[2], getTextForPage2Row(i, dynamicData),
            PDType1Font.TIMES_ROMAN, 10);
      }
      // Move the context up by 10 units
      yPosition = 515; // Move up the yPosition
      contentStream.beginText();
      contentStream.newLineAtOffset(margin + columnWidths[0] + columnWidths[1],
          yPosition);
      contentStream.showText("Follow the instruction of the "
          + dynamicData.get("department") + " .");
      contentStream.endText();
    }
  }

  private static void addPage3Content(PDDocument document, PDPage page,
      Map<String, String> dynamicData) throws IOException {
    try (PDPageContentStream contentStream = new PDPageContentStream(document,
        page, PDPageContentStream.AppendMode.APPEND, true, true)) {
      float margin = 50;
      float startY = page.getMediaBox().getHeight() - margin;
      float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
      float yPosition = startY - 120;

      // Medical Benefits
      String needMedic = dynamicData.get("needMedic");
      String medicalBenefits = getMedicalBenefits(needMedic);
      if ("Y".equalsIgnoreCase(needMedic)) {
        drawTableRow(contentStream, margin, yPosition,
            new float[] {130, 20, tableWidth - 150},
            new String[] {"Medical Benefits", ":", ""}, PDType1Font.TIMES_ROMAN,
            10);
        yPosition = addMultiLineText(contentStream, margin + 150, yPosition,
            tableWidth - 150, medicalBenefits, PDType1Font.TIMES_ROMAN, 10);
      }

      String[] rowTitles = {"Mandatory Provident Fund", "Learning allowance",
          "Attendance bonus", "Welcome bonus", "Transportation allowance"};
      float[] columnWidths =
          {firstColumnWide, secondColumnWide, tableWidth - 170};

      for (int i = 0; i < rowTitles.length; i++) {
        drawTableRow(contentStream, margin, yPosition, columnWidths,
            new String[] {rowTitles[i], ":", ""}, PDType1Font.TIMES_ROMAN, 10);
        yPosition = addMultiLineText(contentStream,
            margin + columnWidths[0] + columnWidths[1], yPosition,
            columnWidths[2], getTextForPage3Row(i, dynamicData),
            PDType1Font.TIMES_ROMAN, 10);
      }
      // Notice of Termination
      String title = dynamicData.get("title");
      String noticeOfTermination =
          getDifferentNoticeOfTermination(TitleType.valueOf(title));
      drawTableRow(contentStream, margin, yPosition,
          new float[] {130, 20, tableWidth - 150},
          new String[] {"Notice of Termination", ":", ""},
          PDType1Font.TIMES_ROMAN, 10);
      yPosition = addMultiLineText(contentStream, margin + 150, yPosition,
          tableWidth - 150, noticeOfTermination, PDType1Font.TIMES_ROMAN, 10);

    }

  }

  private static void addPage4Content(PDDocument document, PDPage page,
      Map<String, String> dynamicData) throws IOException {
    try (PDPageContentStream contentStream = new PDPageContentStream(document,
        page, PDPageContentStream.AppendMode.APPEND, true, true)) {
      float margin = 50;
      float startY = page.getMediaBox().getHeight() - margin;
      float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
      float yPosition = startY - 120;

      // Reference Check
      drawTableRow(contentStream, margin, yPosition,
          new float[] {130, 20, tableWidth - 150},
          new String[] {"Reference Check", ":", ""}, PDType1Font.TIMES_ROMAN,
          10);
      yPosition = addMultiLineText(contentStream, margin + 150, yPosition,
          tableWidth - 150,
          "You will be required to provide relevant documents of background checks, including but not limited to your past employment history, background check reference contacts, education certificate, identity card or passport. The information given should be true and correct. Any willful misrepresentation for false information may subject yourself to immediate dismissal by the company without any compensation.",
          PDType1Font.TIMES_ROMAN, 10);

      yPosition -= 10;

      // Governing Law
      drawTableRow(contentStream, margin, yPosition,
          new float[] {130, 20, tableWidth - 150},
          new String[] {"Governing Law", ":", ""}, PDType1Font.TIMES_ROMAN, 10);
      yPosition = addMultiLineText(contentStream, margin + 150, yPosition,
          tableWidth - 150,
          "This Agreement is governed by and shall be construed in accordance with the laws of Hong Kong for the time being in force and the parties hereto hereby irrevocably submit to the non-exclusive jurisdiction of the Hong Kong courts in connection herewith.",
          PDType1Font.TIMES_ROMAN, 10);

      yPosition -= 10;
      // Additional paragraphs
      String[] paragraphs = {
          "Our offer of employment is subject to the satisfactory checking of former employment records and personal references. Terms of employment shall also be governed by clauses mentioned in Appendix A \"Interpretation\", Appendix B \"Code of Conduct\", Appendix C \"Declaration of Criminal Conviction and Prosecution Record\", Appendix D \"Terms and Conditions to be Incorporated in the Contracts of Employment of the Relevant Employees\" and Appendix E \"Deed of Undertaking\" of this agreement.",
          "You acknowledge and understand upon signing of the employment contract in acceptance of the terms herein.",
          "This contract supersedes all other agreements and contracts to date."};

      for (String paragraph : paragraphs) {
        yPosition = addMultiLineText(contentStream, margin, yPosition,
            tableWidth, paragraph, PDType1Font.TIMES_ROMAN, 10);
        yPosition -= 10;
      }

      // Signature section
      yPosition -= 30;
      float columnWidth = (tableWidth - margin) / 2;

      contentStream.beginText();
      contentStream.setFont(PDType1Font.TIMES_ROMAN, 10);
      contentStream.newLineAtOffset(margin, yPosition);
      contentStream.showText("Yours Sincerely,");
      contentStream.endText();

      contentStream.beginText();
      contentStream.setFont(PDType1Font.TIMES_ROMAN, 10);
      contentStream.newLineAtOffset(margin + columnWidth, yPosition);
      contentStream.showText("Accepted by:");
      contentStream.endText();

      yPosition -= 60;

      // Signature lines and details
      String[] leftDetails =
          {"Dicky Yuen", "Director", "Venturenix Limited", "Date : "};
      String[] rightDetails = {"Name : ", "HKID no. : ", "Date : "};

      LocalDate date = LocalDate.now(); // Set the year to 2024
      DateTimeFormatter formatter =
          DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH);
      String formattedDate = date.format(formatter);



      float signatureYPosition = yPosition;
      contentStream.moveTo(margin, signatureYPosition - 5);
      contentStream.lineTo(margin + 110, signatureYPosition - 5);
      contentStream.stroke();

      contentStream.moveTo(margin + columnWidth, signatureYPosition - 5);
      contentStream.lineTo(margin + columnWidth + 110, signatureYPosition - 5);
      contentStream.stroke();


      for (int i = 0; i < Math.max(leftDetails.length,
          rightDetails.length); i++) {
        if (i < leftDetails.length) {
          contentStream.beginText();
          contentStream.setFont(PDType1Font.TIMES_ROMAN, 10);
          contentStream.newLineAtOffset(margin, yPosition - 15);
          contentStream.showText(leftDetails[i]);

          if (leftDetails[i].contains("Date"))
            contentStream.showText(formattedDate);

          contentStream.endText();

        }
        if (i < rightDetails.length) {
          contentStream.beginText();
          contentStream.setFont(PDType1Font.TIMES_ROMAN, 10);
          contentStream.newLineAtOffset(margin + columnWidth, yPosition - 15);
          contentStream.showText(rightDetails[i]);

          if (rightDetails[i].contains("Name"))
            contentStream.showText(dynamicData.get("Name"));
          if (rightDetails[i].contains("HKID"))
            contentStream.showText(dynamicData.get("HKID"));
          contentStream.endText();

        }
        yPosition -= 15;
      }

      // InputStream imageStream = VenturenixOfferLetterPDFGenerator.class
      // .getResourceAsStream("/Users/oscarlo/contract_system/spring_backend/src/main/resources/static/director_signature.png");
      // if (imageStream == null) {
      // throw new IOException("Signature image file not found");
      // }
      // PDImageXObject pdImage = PDImageXObject.createFromByteArray(document,
      // IOUtils.toByteArray(imageStream), "director_signature");

      float imageWidth = 180; // Adjust as needed
      float imageHeight = 50; // Adjust as needed
      // contentStream.drawImage(pdImage, margin, signatureYPosition, imageWidth,
      // imageHeight);

      // Add page number
      contentStream.beginText();
      contentStream.setFont(PDType1Font.TIMES_ROMAN, 10);
      contentStream.newLineAtOffset(page.getMediaBox().getWidth() / 2, 30);
      contentStream.showText("4");
      contentStream.endText();
    }

  }


  private static void addPage28Content(PDDocument document, PDPage page)
      throws IOException {
    // Load custom fonts
    PDType0Font timesNewRoman = PDType0Font.load(document,
        new File("/Users/oscarlo/contract_system/Times_New_Roman.ttf"));
    PDType0Font timesNewRomanBold = PDType0Font.load(document,
        new File("/Users/oscarlo/contract_system/Times_New_Roman_Bold.ttf"));

    try (PDPageContentStream contentStream =
        new PDPageContentStream(document, page)) {
      float margin = 70; // Consistent left margin for alignment
      float baseStartX = margin; // Base X position for "To:" and "(1)"
      float startY = page.getMediaBox().getHeight() - 108; // Starting Y position
      float fontSize = 12;
      float leading = 14;

      // Title (centered)
      contentStream.setFont(timesNewRomanBold, 13);
      String title = "DEED OF UNDERTAKING";
      float titleWidth = timesNewRomanBold.getStringWidth(title) / 1000 * 13;
      contentStream.beginText();
      contentStream.newLineAtOffset(
          (page.getMediaBox().getWidth() - titleWidth) / 2, startY);
      contentStream.showText(title);
      contentStream.endText();

      // Add underscore
      contentStream.setLineWidth(0.5f);
      contentStream.moveTo(page.getMediaBox().getWidth() / 2 - titleWidth / 2,
          startY - 2);
      contentStream.lineTo(page.getMediaBox().getWidth() / 2 + titleWidth / 2,
          startY - 2);
      contentStream.stroke();

      startY -= 30;
      contentStream.setFont(timesNewRoman, fontSize);

      // Date (right-aligned)
      String date = "Date: " + LocalDate.now()
          .format(DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH))
          .toString();
      float dateWidth = timesNewRoman.getStringWidth(date) / 1000 * fontSize;
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
      contentStream.showText("37/F, Times Tower, 391-407 Jaffe Road, Wan Chai");
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
      contentStream
          .showText("as represented by Commissioner for Digital Policy (");
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
      contentStream
          .showText("disclose to any person any Confidential Information.");
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
  }


  private static void addPage29Content(PDDocument document, PDPage page,
      Map<String, String> dynamicData) throws IOException {

    // Load custom fonts
    PDType0Font timesNewRoman = PDType0Font.load(document,
        new File("/Users/oscarlo/contract_system/Times_New_Roman.ttf"));
    PDType0Font timesNewRomanBold = PDType0Font.load(document,
        new File("/Users/oscarlo/contract_system/Times_New_Roman_Bold.ttf"));

    try (PDPageContentStream contentStream =
        new PDPageContentStream(document, page)) {
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
          "conditions of the Contract. \"Materials\" includes but is not limited to all the");
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
      startY -= leading * 3; // Space before signature
      contentStream.beginText();
      contentStream.newLineAtOffset(baseStartX, startY);
      contentStream
          .showText("SIGNED SEALED and DELIVERED by                     )");
      contentStream.endText();

      // Placeholder for Name
      startY -= leading * 1.07;
      contentStream.beginText();
      contentStream.newLineAtOffset(baseStartX, startY);
      contentStream.showText("Name: " + dynamicData.get("Name"));
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
      contentStream.newLineAtOffset(baseStartX, startY);
      contentStream.showText("LAW, SIN MAN");
      contentStream.endText();
    }
  }

  private static void drawTableRow(PDPageContentStream contentStream, float x,
      float y, float[] columnWidths, String[] texts, PDType1Font font,
      int fontSize) throws IOException {
    float nextX = x;
    for (int i = 0; i < texts.length; i++) {
      contentStream.beginText();
      contentStream.setFont(font, fontSize);
      contentStream.newLineAtOffset(nextX, y);
      contentStream.showText(texts[i]);
      contentStream.endText();
      nextX += columnWidths[i];
    }
  }

  private static float addMultiLineText(PDPageContentStream contentStream,
      float x, float y, float width, String text, PDType1Font font,
      int fontSize) throws IOException {
    String[] paragraphs = text.split("\n");
    float leading = 1.2f * fontSize;

    for (String paragraph : paragraphs) {
      String[] words = paragraph.split("\\s+");
      StringBuilder line = new StringBuilder();

      for (String word : words) {
        if (font.getStringWidth(line + " " + word) / 1000 * fontSize > width) {
          contentStream.beginText();
          contentStream.setFont(font, fontSize);
          contentStream.newLineAtOffset(x, y);
          contentStream.showText(line.toString());
          contentStream.endText();
          y -= leading;
          line = new StringBuilder(word);
        } else {
          if (line.length() > 0)
            line.append(" ");
          line.append(word);
        }
      }

      if (line.length() > 0) {
        contentStream.beginText();
        contentStream.setFont(font, fontSize);
        contentStream.newLineAtOffset(x, y);
        contentStream.showText(line.toString());
        contentStream.endText();
        y -= leading;
      }

      // Add extra space between paragraphs
      y -= leading;
    }

    return y;
  }

  private static float drawMultiLineTableRow(PDPageContentStream contentStream,
      float x, float y, float[] columnWidths, String[] texts, PDType1Font font,
      int fontSize) throws IOException {
    float nextX = x;
    float lowestY = y;
    for (int i = 0; i < texts.length; i++) {
      String[] lines = texts[i].split("\\n");
      float lineY = y;
      for (String line : lines) {
        contentStream.beginText();
        contentStream.setFont(font, fontSize);
        contentStream.newLineAtOffset(nextX, lineY);
        contentStream.showText(line.trim());
        contentStream.endText();
        lineY -= fontSize * 1.5f;
      }
      lowestY = Math.min(lowestY, lineY + fontSize * 1.5f);
      nextX += columnWidths[i];
    }
    return lowestY;
  }

  private static String getTextForPage1Row(int rowIndex,
      Map<String, String> dynamicData) {
    switch (rowIndex) {
      case 0: // Contract Period
        return dynamicData.get("contractPeriod");
      case 1: // Position
        return dynamicData.get("position");// expect Staff Category: 2 - Analyst Programmer
      case 2: // Place of Work
        return dynamicData.get("placeOfWork"); // expect department + unit
      case 3: // Salary
        return "HKD " + dynamicData.get("Salary") + " per month\n"
            + " Monthly salary calculation for that month = Monthly Salary x (total number of days the employee actually worked that month + entitled annual leaves taken* / total number of working days in that month.\n Working days will include public holiday and statutory holidays, excluding Saturdays and Sundays.\n *(support by timesheet that approved and signed by the Department. Public holiday and statutory holidays do not require approval)\n The adjustment of the salary will take effect on the same date that the Contract Ceiling Rate applicable to you is adjusted. The company shall adjust the salary payable upwards by a percentage no less than the percentage of upward adjustment to the Contract Ceiling Rates determined by the Government.";
      case 4: // Payroll Administration
        return "Salary will be credited to such bank account in Hong Kong as you shall designate on the 5th of the calendar month after receipt of your certified timesheet.";
      // case 5: // Working Hours
      // return "Your normal working hours:\n" + dynamicData.get("WorkingHours")
      // + "\nSubject to " + dynamicData.get("department")
      // + " Department\nForty-four (44) hours per week including meal breaks.\n";
      default:
        return "";
    }
  }

  private static String getTextForPage2Row(int rowIndex,
      Map<String, String> dynamicData) {
    switch (rowIndex) {
      case 0: // Overtime Payment
        return "If you are required to work overtime at the request of your immediate supervisor, the company shall provide time-off equivalent to the said period. The time and date for you to have the time- off shall be subject to your immediate supervisor's absolute discretion.\n An hourly rate equivalent to one-eighth (1/8)  of the Monthly Salary divided by 20.5 days at the time of overtime working will be paid if the Government elects to pay for working overtime in lieu of granting time off.  Payment of overtime service is payable on a half-hourly basis, subject to a minimum of one hour overtime worked outside normal office hours.\nOvertime working shall be written down on the timesheet and endorsed by your immediate supervisor.";
      case 1: // Typhoon/ Rainstorm Warning
        return "";
      case 2: // Annual Leave
        return "You are entitled to have " + dynamicData.get("annualLeave")
            + " working days paid leave per annum or on a pro-rata basis for an incomplete year.\nOutstanding Annual Leave balance will be paid in lieu of leave and reimbursed to you at the end of contract period. It shall not be used to offset the notice period of termination.\nThe rate of one annual leave day will be calculated at Annual Salary divided by 365 days. For not having any sick leave of 4 or more than 4 consecutive days taken, the rate of one annual leave day will be calculated at Monthly Salary divided by 20.5 days.";
      case 3: // Holiday
        return "You are entitled to public holiday (i.e. bank holiday) and paid Statutory Holiday in accordance with the Employment Ordinance.";
      case 4: // Rest day
        return "You are entitled to not less than 1 rest day in every period of 7 days, in accordance with the Employment Ordinance. In general, the rest days will fall on Sunday";
      case 5: // Sick Leave
        return "You are entitled to sick leave in accordance with the Employment Ordinance. You must inform your immediate supervisor and the company as soon as possible if you are intent to take sick leave. Sick leave taken shall be written down on the timesheet and endorsed by your immediate supervisor. Medical certificates are required for timesheet submission.";
      case 6: // Leave of Absence
        return "Leave of absence will be calculated on a daily basis and deducted from salary at the rate of Monthly Salary divided by (Number of working days & statutory holiday) per day.";
      default:
        return "";
    }
  }

  private static String getTextForPage3Row(int rowIndex,
      Map<String, String> dynamicData) {
    switch (rowIndex) {
      case 0:
        return "The employee is mandatory to be enrolled in the MPF Scheme under which both the employer and employee are mandatory to make a contribution equal to 5% of the employee’s monthly relevant salary (currently maximum contribution HKD 1,500). ";
      case 1:
        return "You are entitled to enjoy the proprietary training course provided by VenturenixLab at an 80% off discount rate on the original price, up to HKD 15,000. (i.e. HKD 18,750 value by paying HKD3,750) upon successful completion of every 2 years contract period. Partnership courses are excluded from the learning allowance scheme. ";
      case 2:
        return "You will be granted an attendance bonus HKD " + //
            dynamicData.get("Salary")
            + " per annum or on a pro-rata basis (divided by 365 days) for an incomplete year upon successful completion of the contract period on the condition of not taking 4 days or more than 4 days of paid sick leave and not taking more than 13 days no pay leave per annum. Early termination of contract by serving notice period are eligible for such attendance bonus and on pro rata basis.";
      case 3:
        return "You are entitled to HKD " + //
            dynamicData.get("Salary")
            + " one off welcome bonus upon successful completion for service period of 3 months, the salary will be paid to gether with the Salary of the following month. For the avoidance of doubt, the welcome bonus will be take out from upcoming contract renewal or extension.";

      case 4:
        return "You are entitled to transportation allowance under special circumstances where you are, with the prior authorization and certification of your immediate supervisor, (i) required to take journey(s) in Hong Kong other than normal home-office journey; or (ii) on rare occasions required to work in Guangdong Province on same day return journey; or (iii) on specified situations as authorised by the Government in writing. The company will reimburse the travelling expenses spent by you on such journey(s) which is/are made by means of public land transport or other authorised expenses based on prior agreement given by the Government.\nItems to be reimbursed shall be written down on the timesheet and endorsed by your immediate supervisor.";
      default:
        return "";
    }
  }

  private static String getTextForPage4Row(int rowIndex,
      Map<String, String> dynamicData) {
    switch (rowIndex) {
      case 0:
        return "You will be required to provide relevant documents of background checks, including but not limited to your past employment history, background check reference contacts, education certificate, identity card or passport. The information given should be true and correct. Any willful misrepresentation for false information may subject yourself to immediate dismissal by the company without any compensation.";
      case 1:
        return "This Agreement is governed by and shall be construed in accordance with the laws of Hong Kong for the time being in force and the parties hereto hereby irrevocably submit to the non-exclusive jurisdiction of the Hong Kong courts in connection herewith.";
      default:
        return "";
    }
  }

  // Method to add page number in the right corner
  private static void addPageNumber(PDDocument document, PDPage page,
      int pageNumber) throws IOException {
    try (PDPageContentStream contentStream = new PDPageContentStream(document,
        page, PDPageContentStream.AppendMode.APPEND, true, true)) {
      contentStream.setFont(PDType1Font.TIMES_ROMAN, 10);
      float pageWidth = page.getMediaBox().getWidth();
      float margin = 50; // Adjust as needed
      float yPosition = 30; // Distance from bottom
      contentStream.beginText();
      contentStream.newLineAtOffset(pageWidth - margin, yPosition);
      contentStream.showText(String.valueOf(pageNumber));
      contentStream.endText();
    }
  }

  private static String getMedicalBenefits(String needMedic) {
    if (needMedic.equals("Y")) {
      return "You are entitled to join the medical allowance scheme provided by the company.";
    }
    return "";
  }

  private static String getDifferentNoticeOfTermination(TitleType title) {

    switch (title) {
      case CSSA:
        return "If either party wishes to terminate this contract, 2 calendar months' notice or forfeit salary in lieu of notice must be given to the other party. Annual leave cannot be used to offset the termination notice period. \n"
            + " If you terminate this contract with not less than 1 month but less than 2 months notice, you are liable to pay for a compensation at HKD 30,000 for the damage of goodwill for Venturenix Limited on the failure to provide continuous service to our client.\n"
            + "If you terminate this contract with less than 1 month notice you are liable to pay for a compensation at HKD 60,000 for the damage of goodwill for Venturenix Limited on the failure to provide continuous service to our client. ";
      case CSA:
        return "If either party wishes to terminate this contract, 2 calendar months' notice or forfeit salary in lieu of notice must be given to the other party. Annual leave cannot be used to offset the termination notice period.\n"
            + " If you terminate this contract with not less than 1 month but less than 2 months notice, you are liable to pay for a compensation at HKD 30,000 for the damage of goodwill for Venturenix Limited on the failure to provide continuous service to our client.\n"
            + "If you terminate this contract with less than 1 month notice you are liable to pay for a compensation at HKD 59,000 for the damage of goodwill for Venturenix Limited on the failure to provide continuous service to our client. ";
      case CPM:
        return "If either party wishes to terminate this contract, 2 calendar months' notice or forfeit salary in lieu of notice must be given to the other party. Annual leave cannot be used to offset the termination notice period.\n"
            + " If you terminate this contract with not less than 1 month but less than 2 months notice, you are liable to pay for a compensation at HKD 30,000 for the damage of goodwill for Venturenix Limited on the failure to provide continuous service to our client.\n"
            + "If you terminate this contract with less than 1 month notice you are liable to pay for a compensation at HKD 61,000 for the damage of goodwill for Venturenix Limited on the failure to provide continuous service to our client.";
      case CP:
        return "If either party wishes to terminate this contract, 1 calendar months' notice or forfeit salary in lieu of notice must be given to the other party. Annual leave cannot be used to offset the termination notice period.\n"
            + "If you terminate this contract with less than 1 month notice you are liable to pay for a compensation at HKD 37,000 for the damage of goodwill for Venturenix Limited on the failure to provide continuous service to our client.";
      case CJP:
        return "If either party wishes to terminate this contract, 1 calendar months' notice or forfeit salary in lieu of notice must be given to the other party. Annual leave cannot be used to offset the termination notice period.\n"
            + "If you terminate this contract with less than 1 month notice you are liable to pay for a compensation at HKD 29,000 for the damage of goodwill for Venturenix Limited on the failure to provide continuous service to our client. ";
      case CITA:
        return "If either party wishes to terminate this contract,  calendar months' notice or forfeit salary in lieu of notice must be given to the other party. Annual leave cannot be used to offset the termination notice period.\n"
            + "If you terminate this contract with less than 1 month notice you are liable to pay for a compensation at HKD 32,000 for the damage of goodwill for Venturenix Limited on the failure to provide continuous service to our client. ";
      case CAP:
        return "If either party wishes to terminate this contract, 1 calendar months' notice or forfeit salary in lieu of notice must be given to the other party. Annual leave cannot be used to offset the termination notice period.\n"
            + "If you terminate this contract with less than 1 month notice you are liable to pay for a compensation at HKD 46,000 for the damage of goodwill for Venturenix Limited on the failure to provide continuous service to our client. ";
      default:
        throw new IllegalArgumentException("Invalid title type");
    }
  }

  public static String fineNaming(Map<String, String> dynamicData) {
    StringBuilder result = new StringBuilder();
    result.append(LocalDate.now().toString());
    result.append("_");
    result.append(dynamicData.get("Name").replace(" ", "_"));
    result.append("_");
    result.append(dynamicData.get("title"));
    if (dynamicData.get("needMedic").equals("Y")) {
      result.append("_Medical");
    }
    result.append(".pdf");
    return result.toString();
  }

}
