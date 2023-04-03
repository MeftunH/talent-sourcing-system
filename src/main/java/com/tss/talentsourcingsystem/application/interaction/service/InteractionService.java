package com.tss.talentsourcingsystem.application.interaction.service;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.interaction.dto.InteractionDto;
import com.tss.talentsourcingsystem.application.interaction.dto.InteractionSaveRequestDto;
import com.tss.talentsourcingsystem.application.interaction.dto.InteractionUpdateRequestDto;

import java.util.List;

public interface InteractionService {
    InteractionDto saveInteraction(InteractionSaveRequestDto interactionSaveRequest);

    InteractionDto getInteractionById(Long id);

    List<InteractionDto> getInteractionsByCandidateId(Long id);

    InteractionDto updateInteraction(Long id, InteractionUpdateRequestDto interactionUpdateRequestDto);

    void deleteInteraction(Long id);
}
