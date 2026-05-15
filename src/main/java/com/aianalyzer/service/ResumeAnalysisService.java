package com.aianalyzer.service;

import com.aianalyzer.dto.ResumeAnalysisResponse;
import com.aianalyzer.util.ResumeTextExtractor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ResumeAnalysisService {

    private static final Set<String> BASE_SKILL_KEYWORDS = Set.of(
            "java", "spring", "hibernate", "docker", "kubernetes",
            "microservices", "cloud", "aws", "azure", "gcp",
            "sql", "postgresql", "mysql", "rest api", "api",
            "python", "javascript", "react", "angular", "node.js",
            "git", "ci/cd", "jira", "agile", "scrum"
    );

    private static final Map<String, Integer> SKILL_WEIGHTS = new HashMap<>();

    static {
        SKILL_WEIGHTS.put("java", 10);
        SKILL_WEIGHTS.put("spring", 9);
        SKILL_WEIGHTS.put("hibernate", 8);
        SKILL_WEIGHTS.put("docker", 8);
        SKILL_WEIGHTS.put("kubernetes", 9);
        SKILL_WEIGHTS.put("microservices", 8);
        SKILL_WEIGHTS.put("cloud", 7);
        SKILL_WEIGHTS.put("aws", 8);
        SKILL_WEIGHTS.put("azure", 7);
        SKILL_WEIGHTS.put("gcp", 7);
        SKILL_WEIGHTS.put("sql", 6);
        SKILL_WEIGHTS.put("postgresql", 7);
        SKILL_WEIGHTS.put("mysql", 6);
        SKILL_WEIGHTS.put("rest api", 7);
        SKILL_WEIGHTS.put("api", 6);
        SKILL_WEIGHTS.put("python", 6);
        SKILL_WEIGHTS.put("javascript", 6);
        SKILL_WEIGHTS.put("react", 7);
        SKILL_WEIGHTS.put("angular", 7);
        SKILL_WEIGHTS.put("node.js", 7);
        SKILL_WEIGHTS.put("git", 5);
        SKILL_WEIGHTS.put("ci/cd", 6);
        SKILL_WEIGHTS.put("jira", 5);
        SKILL_WEIGHTS.put("agile", 5);
        SKILL_WEIGHTS.put("scrum", 5);
    }

    public ResumeAnalysisResponse analyze(MultipartFile file, String jobTitle) {
        String text;
        try {
            text = ResumeTextExtractor.extractText(file);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to extract text from resume file", e);
        }

        String normalized = text.toLowerCase();
        List<String> matchingSkills = BASE_SKILL_KEYWORDS.stream()
                .filter(normalized::contains)
                .sorted()
                .collect(Collectors.toList());

        int rawScore = matchingSkills.stream()
                .mapToInt(skill -> SKILL_WEIGHTS.getOrDefault(skill, 5))
                .sum();

        int keywordScore = calculateJobTitleMatchScore(normalized, jobTitle);
        int totalScore = Math.min(100, rawScore + keywordScore);

        double relevance = totalScore / 100.0;

        return new ResumeAnalysisResponse(
                file.getOriginalFilename(),
                matchingSkills,
                totalScore,
                relevance,
                keywordScore,
                text
        );
    }

    private int calculateJobTitleMatchScore(String normalizedText, String jobTitle) {
        if (jobTitle == null || jobTitle.isBlank()) {
            return 0;
        }

        String normalizedJobTitle = jobTitle.toLowerCase();
        int points = 0;

        for (String token : normalizedJobTitle.split("\\s+")) {
            if (token.length() > 2 && normalizedText.contains(token)) {
                points += 5;
            }
        }

        return Math.min(points, 20);
    }
}
