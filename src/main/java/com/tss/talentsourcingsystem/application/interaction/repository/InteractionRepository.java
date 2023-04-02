package com.tss.talentsourcingsystem.application.interaction.repository;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.interaction.entity.Interaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InteractionRepository extends JpaRepository<Interaction, Long> {
    List<Interaction> findByCandidateId(Long candidateId);

    List<Interaction> findAllByCandidateId(Long id);
}
