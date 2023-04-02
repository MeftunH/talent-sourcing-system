package com.tss.talentsourcingsystem.application.interaction.validation;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.interaction.entity.Interaction;

public interface InteractionValidationService {
    void validateAreFieldsNonNull(Interaction interaction);

    void validateCandidateId(Long candidateId);
}
