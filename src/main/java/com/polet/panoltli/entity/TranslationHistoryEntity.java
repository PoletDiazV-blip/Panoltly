package com.polet.panoltli.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "translation_history", schema = "translation")
public class TranslationHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "detected_sign", nullable = false)
    private String detectedSign;

    @Column(name = "confidence_score")
    private Double confidenceScore;

    @Column(name = "external_audio_url")
    private String externalAudioUrl;

    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    public TranslationHistoryEntity() {}

    // Getters y Setters exactos
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }

    public String getDetectedSign() { return detectedSign; }
    public void setDetectedSign(String detectedSign) { this.detectedSign = detectedSign; }

    public Double getConfidenceScore() { return confidenceScore; }
    public void setConfidenceScore(Double confidenceScore) { this.confidenceScore = confidenceScore; }

    public String getExternalAudioUrl() { return externalAudioUrl; }
    public void setExternalAudioUrl(String externalAudioUrl) { this.externalAudioUrl = externalAudioUrl; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
}