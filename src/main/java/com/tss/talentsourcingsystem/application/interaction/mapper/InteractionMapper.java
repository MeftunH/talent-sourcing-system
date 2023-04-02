package com.tss.talentsourcingsystem.application.interaction.mapper;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.interaction.dto.InteractionDto;
import com.tss.talentsourcingsystem.application.interaction.dto.InteractionSaveRequestDto;
import com.tss.talentsourcingsystem.application.interaction.entity.Interaction;
import com.tss.talentsourcingsystem.application.util.DateUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring")
public interface InteractionMapper {
    InteractionMapper INSTANCE = Mappers.getMapper(InteractionMapper.class);

    @Named("dateFormat")
    default Date dateFormat(LocalDateTime date){
        return DateUtil.convertLocalDateTimeToDate(date);
    }

    @Mapping(source="baseAdditionalFields.createdDate",target="baseAdditionalFieldsCreatedDate",qualifiedByName="dateFormat")
    @Mapping(source="baseAdditionalFields.updatedDate",target="baseAdditionalFieldsUpdatedDate")
    InteractionDto interactionToInteractionDto(Interaction interaction);

    Interaction interactionDtoToInteraction(InteractionDto interactionDto);

    List<InteractionDto> interactionsToInteractionDtoList(List<Interaction> interactions);

    @Mapping(source="candidateId", target="candidate.id")
    Interaction interactionSaveRequestDtoToInteraction(InteractionSaveRequestDto interactionSaveRequest);

    List<InteractionDto> interactionListToInteractionDtoList(List<Interaction> interactions);

}
