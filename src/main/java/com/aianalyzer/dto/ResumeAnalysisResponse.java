package com.aianalyzer.dto;

import java.util.List;

public class ResumeAnalysisResponse {

    private final String fileName;
    private final List<String> detectedSkills;
    private final int score;
    private final double relevance;
    private final int jobTitleMatchScore;
    private final String extractedText;

    public ResumeAnalysisResponse(String fileName,
                                  List<String> detectedSkills,
                                  int score,
                                  double relevance,
                                  int jobTitleMatchScore,
                                  String extractedText) {
        this.fileName = fileName;
        this.detectedSkills = detectedSkills;
        this.score = score;
        this.relevance = relevance;
        this.jobTitleMatchScore = jobTitleMatchScore;
        this.extractedText = extractedText;
    }

    public String getFileName() {
        return fileName;
    }

    public List<String> getDetectedSkills() {
        return detectedSkills;
    }

    public int getScore() {
        return score;
    }

    public double getRelevance() {
        return relevance;
    }

    public int getJobTitleMatchScore() {
        return jobTitleMatchScore;
    }

    public String getExtractedText() {
        return extractedText;
    }
}
