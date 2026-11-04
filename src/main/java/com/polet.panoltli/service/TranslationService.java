package com.polet.panoltli.service;

import com.polet.panoltli.entity.TranslationHistoryEntity;
import java.util.List;

public interface TranslationService {
    TranslationHistoryEntity processTranslation(String userEmail, String detectedSign, Double confidenceScore, String externalAudioUrl);
    List<TranslationHistoryEntity> getUserHistory(String userEmail);
}