package com.aianalyzer.service;

import com.aianalyzer.dto.ResumeAnalysisResponse;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ResumeAnalysisServiceTest {

    private final ResumeAnalysisService service = new ResumeAnalysisService();

    @Test
    void analyze_shouldDetectKnownSkillsAndScoreResume() {
        String resumeText = "Experienced Java developer with Spring Boot, Docker, AWS, and REST API development.";
        MockMultipartFile file = new MockMultipartFile("file", "resume.txt", "text/plain", resumeText.getBytes());

        ResumeAnalysisResponse response = service.analyze(file, "Java Developer");

        assertEquals("resume.txt", response.getFileName());
        assertTrue(response.getDetectedSkills().contains("aws"));
        assertTrue(response.getDetectedSkills().contains("docker"));
        assertTrue(response.getDetectedSkills().contains("java"));
        assertTrue(response.getScore() > 0);
        assertEquals(resumeText.trim(), response.getExtractedText().trim());
    }
}
