package com.polet.panoltli.repository;

import com.polet.panoltli.entity.TranslationHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TranslationRepository extends JpaRepository<TranslationHistoryEntity, UUID> {
    List<TranslationHistoryEntity> findByUserId(UUID userId);
}