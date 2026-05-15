package com.aianalyzer.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ResumeTextExtractor {

    public static String extractText(MultipartFile file) throws IOException {
        String contentType = file.getContentType();
        if (contentType != null && contentType.startsWith("text/")) {
            // For plain text files, read directly
            try (InputStreamReader reader = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8)) {
                StringBuilder text = new StringBuilder();
                char[] buffer = new char[1024];
                int length;
                while ((length = reader.read(buffer)) != -1) {
                    text.append(buffer, 0, length);
                }
                return text.toString();
            }
        } else {
            throw new IOException("Unsupported file type. Only plain text files are supported in this pure Java version.");
        }
    }
}
