package com.tss.talentsourcingsystem.application.interaction.service.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.general.errorMessage.GeneralErrorMessage;
import com.tss.talentsourcingsystem.application.general.exception.ItemNotFoundException;
import com.tss.talentsourcingsystem.application.generic.service.BaseService;
import com.tss.talentsourcingsystem.application.interaction.dto.InteractionDto;
import com.tss.talentsourcingsystem.application.interaction.dto.InteractionSaveRequestDto;
import com.tss.talentsourcingsystem.application.interaction.dto.InteractionUpdateRequestDto;
import com.tss.talentsourcingsystem.application.interaction.entity.Interaction;
import com.tss.talentsourcingsystem.application.interaction.enums.CandidateResponseType;
import com.tss.talentsourcingsystem.application.interaction.mapper.InteractionMapper;
import com.tss.talentsourcingsystem.application.interaction.repository.InteractionRepository;
import com.tss.talentsourcingsystem.application.interaction.service.InteractionService;
import com.tss.talentsourcingsystem.application.interaction.validation.InteractionValidationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InteractionServiceImpl extends BaseService<Interaction> implements InteractionService {
    private final InteractionRepository interactionRepository;
    private final InteractionValidationService interactionValidationService;
    public InteractionServiceImpl(InteractionRepository interactionRepository, InteractionValidationService interactionValidationService) {
        this.interactionRepository=interactionRepository;
        this.interactionValidationService=interactionValidationService;
    }

    @Override
    public InteractionDto saveInteraction(InteractionSaveRequestDto interactionSaveRequest) {
        Interaction interaction =InteractionMapper.INSTANCE.interactionSaveRequestDtoToInteraction(interactionSaveRequest);

        interactionValidationService.validateCandidateId(interactionSaveRequest.getCandidateId());
        interactionValidationService.validateAreFieldsNonNull(interaction);

        setAdditionalFields(interaction);
        if (interaction.getIsCandidateResponded()==null){
            interaction.setIsCandidateResponded(CandidateResponseType.NO);
        }

        Interaction savedInteraction = interactionRepository.save(interaction);
        return InteractionMapper.INSTANCE.interactionToInteractionDto(savedInteraction);
    }

    @Override
    public InteractionDto getInteractionById(Long id) {
        Interaction interaction=getInteraction(id);
        return InteractionMapper.INSTANCE.interactionToInteractionDto(interaction);
    }

    private Interaction getInteraction(Long id) {
        Interaction interaction = interactionRepository.findById(id).orElseThrow(()->new ItemNotFoundException(GeneralErrorMessage.ITEM_NOT_FOUND));
        return interaction;
    }

    @Override
    public List<InteractionDto> getInteractionsByCandidateId(Long id) {
        interactionValidationService.validateCandidateId(id);
        List<Interaction> interactions = interactionRepository.findAllByCandidateId(id);
        return InteractionMapper.INSTANCE.interactionListToInteractionDtoList(interactions);
    }

    @Override
    public InteractionDto updateInteraction(Long id, InteractionUpdateRequestDto interactionUpdateRequestDto) {
        Interaction interaction=getInteraction(id);
        interactionValidationService.validateAreFieldsNonNull(interaction);
        interaction.setInteractionType(interactionUpdateRequestDto.getInteractionType());
        interaction.setContent(interactionUpdateRequestDto.getContent());
        interaction.setIsCandidateResponded(interactionUpdateRequestDto.getIsCandidateResponded());

        setAdditionalFields(interaction);

        interactionValidationService.validateCandidateId(interaction.getCandidate().getId());
        interactionValidationService.validateAreFieldsNonNull(interaction);

        Interaction updatedInteraction = interactionRepository.save(interaction);

        return InteractionMapper.INSTANCE.interactionToInteractionDto(updatedInteraction);
    }

    @Override
    public void deleteInteraction(Long id) {
        Interaction interaction=getInteraction(id);
        interactionRepository.delete(interaction);
    }
}
