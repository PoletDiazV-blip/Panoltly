package com.polet.panoltli.repository;

import com.polet.panoltli.entity.SignDictionaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SignDictionaryRepository extends JpaRepository<SignDictionaryEntity, UUID> {

    @Query("SELECT s FROM SignDictionaryEntity s WHERE s.word = :signName")
    Optional<SignDictionaryEntity> findBySignName(@Param("signName") String signName);
}