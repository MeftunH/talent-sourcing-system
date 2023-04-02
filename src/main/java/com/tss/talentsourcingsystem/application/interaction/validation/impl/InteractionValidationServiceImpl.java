package com.tss.talentsourcingsystem.application.interaction.validation.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.candidate.service.CandidateService;
import com.tss.talentsourcingsystem.application.general.exception.IllegalFieldException;
import com.tss.talentsourcingsystem.application.interaction.entity.Interaction;
import com.tss.talentsourcingsystem.application.interaction.enums.InteractionErrorMessage;
import com.tss.talentsourcingsystem.application.interaction.validation.InteractionValidationService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Service
@ControllerAdvice
public class InteractionValidationServiceImpl implements InteractionValidationService {
    private final CandidateService candidateService;

    public InteractionValidationServiceImpl(CandidateService candidateService) {
        this.candidateService=candidateService;
    }
    @Override
    public void validateAreFieldsNonNull(Interaction interaction) {
        if (interaction.getInteractionType() == null || interaction.getInteractionType().describeConstable().isEmpty() || interaction.getContent()==null|| interaction.getContent().isEmpty()) {
            throw new IllegalFieldException(InteractionErrorMessage.INTERACTION_FIELDS_MUST_BE_NOT_NULL);
        }
    }
    @Override
    public void validateCandidateId(Long candidateId) {
        if (candidateId == null || candidateId == 0) {
            throw new IllegalFieldException(InteractionErrorMessage.CANDIDATE_IS_NOT_VALID);
        }
        if (candidateService.getCandidateById(candidateId)==null) {
            throw new IllegalFieldException(InteractionErrorMessage.CANDIDATE_IS_NOT_VALID);
        }
    }
}
