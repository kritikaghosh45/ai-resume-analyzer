package com.aianalyzer.controller;

import com.aianalyzer.dto.ResumeAnalysisResponse;
import com.aianalyzer.service.ResumeAnalysisService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/resume")
@Validated
public class ResumeController {

    private final ResumeAnalysisService analysisService;

    public ResumeController(ResumeAnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @PostMapping("/analyze")
    public ResponseEntity<ResumeAnalysisResponse> analyzeResume(
            @RequestParam("file") @NotNull MultipartFile file,
            @RequestParam(value = "jobTitle", required = false) String jobTitle
    ) {
        ResumeAnalysisResponse response = analysisService.analyze(file, jobTitle);
        return ResponseEntity.ok(response);
    }
}
