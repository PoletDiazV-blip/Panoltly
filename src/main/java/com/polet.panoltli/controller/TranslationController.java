package com.polet.panoltli.controller;

import com.polet.panoltli.entity.TranslationHistoryEntity;
import com.polet.panoltli.service.TranslationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/translations")
public class TranslationController {

    private final TranslationService translationService;

    public TranslationController(TranslationService translationService) {
        this.translationService = translationService;
    }

    @PostMapping("/process")
    public ResponseEntity<TranslationHistoryEntity> process(
            Authentication authentication,
            @RequestBody Map<String, Object> request) {

        String userEmail = authentication.getName();
        String detectedSign = (String) request.get("detectedSign");
        Double confidenceScore = Double.valueOf(request.get("confidenceScore").toString());
        String externalAudioUrl = (String) request.get("externalAudioUrl");

        TranslationHistoryEntity result = translationService.processTranslation(
                userEmail, detectedSign, confidenceScore, externalAudioUrl
        );

        return ResponseEntity.ok(result);
    }

    @GetMapping("/history")
    public ResponseEntity<List<TranslationHistoryEntity>> getHistory(Authentication authentication) {
        String userEmail = authentication.getName();
        List<TranslationHistoryEntity> history = translationService.getUserHistory(userEmail);
        return ResponseEntity.ok(history);
    }
}