package com.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import org.apache.commons.io.output.ByteArrayOutputStream;

public class GenPDF {
  public static String path =
      "/Users/oscarlo/contract_system/UAT/template_empty_page.pdf";
  public static String fullTemplatePath =
      "/Users/oscarlo/contract_system/Template/Contract_template_5-27pages.pdf";

  public static Map<String, String> dynamicData = Map.ofEntries(//
      Map.entry("Name", "John Doe"), //
      Map.entry("HKID", "A123456(7)"), //
      Map.entry("WorkingHours", "9:00 AM - 5:00 PM"), //
      Map.entry("contractPeriod", "02-May-2023 to 31-Oct-2023"), //
      Map.entry("position", "Contract Analyst Programmer "), //
      Map.entry("placeOfWork", "RMLPD"), //
      Map.entry("department", "LD"), //
      Map.entry("annualLeave", "14"), //
      Map.entry("Salary", "60000"), //
      Map.entry("title", "CSSA"), //
      Map.entry("needMedic", "Y"));

  public static void main(String[] args) throws IOException {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    new VenturenixOfferLetterPDFGenerator().generateOfferLetter(path, //
        fullTemplatePath, outputStream, //
        dynamicData);

    byte[] pdfBytes = outputStream.toByteArray();
    String outputPath = "/Users/oscarlo/contract_system/UAT/contract"//
        + LocalDate.now().toString() + "_"//
        + dynamicData.get("Name") + "_" //
        + dynamicData.get("title") + "_"//
        + dynamicData.get("needMedic") + ".pdf";

    try (FileOutputStream fileOutputStream = new FileOutputStream(outputPath)) {
      fileOutputStream.write(pdfBytes);
    }

    System.out.println("Finish Generate");
  }
}
