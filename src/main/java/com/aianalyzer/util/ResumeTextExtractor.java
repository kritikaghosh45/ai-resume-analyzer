package com.aianalyzer.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ResumeTextExtractor {

    public static String extractText(MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        if (filename == null) {
            throw new IOException("File name is null");
        }

        String lower = filename.toLowerCase();

        if (lower.endsWith(".pdf")) {
            return extractFromPdf(file.getInputStream());
        } else if (lower.endsWith(".txt")) {
            return extractFromTxt(file.getInputStream());
        } else {
            throw new IOException(
                "Unsupported file type. Please upload a .pdf or .txt file."
            );
        }
    }

    private static String extractFromPdf(InputStream inputStream)
            throws IOException {
        try (PDDocument document = PDDocument.load(inputStream)) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        }
    }

    private static String extractFromTxt(InputStream inputStream)
            throws IOException {
        return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
    }
}