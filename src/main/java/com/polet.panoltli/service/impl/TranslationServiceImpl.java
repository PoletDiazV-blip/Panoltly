package com.polet.panoltli.service.impl;

import com.polet.panoltli.entity.TranslationHistoryEntity;
import com.polet.panoltli.entity.UserEntity;
import com.polet.panoltli.exception.ResourceNotFoundException;
import com.polet.panoltli.repository.TranslationRepository;
import com.polet.panoltli.repository.UserRepository;
import com.polet.panoltli.service.TranslationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TranslationServiceImpl implements TranslationService {

    private final TranslationRepository translationRepository;
    private final UserRepository userRepository;

    public TranslationServiceImpl(TranslationRepository translationRepository, UserRepository userRepository) {
        this.translationRepository = translationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public TranslationHistoryEntity processTranslation(String userEmail, String detectedSign, Double confidenceScore, String externalAudioUrl) {
        UserEntity user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        TranslationHistoryEntity history = new TranslationHistoryEntity();
        history.setUserId(user.getId());
        history.setDetectedSign(detectedSign);
        history.setConfidenceScore(confidenceScore);
        history.setExternalAudioUrl(externalAudioUrl);

        return translationRepository.save(history);
    }

    @Override
    public List<TranslationHistoryEntity> getUserHistory(String userEmail) {
        UserEntity user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        return translationRepository.findByUserId(user.getId());
    }
}