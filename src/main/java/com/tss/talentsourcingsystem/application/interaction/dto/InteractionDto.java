package com.tss.talentsourcingsystem.application.interaction.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tss.talentsourcingsystem.application.interaction.entity.Interaction;
import com.tss.talentsourcingsystem.application.interaction.enums.CandidateResponseType;
import com.tss.talentsourcingsystem.application.interaction.enums.InteractionType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link Interaction} entity
 */
public record InteractionDto(@JsonFormat(pattern = "yyyy/MM/dd") Date baseAdditionalFieldsCreatedDate,
                             @JsonFormat(pattern = "yyyy/MM/dd") Date baseAdditionalFieldsUpdatedDate, Long id,
                             String content, CandidateResponseType isCandidateResponded,
                             InteractionType interactionType, Long candidateId) implements Serializable {
}