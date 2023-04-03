package com.tss.talentsourcingsystem.application.contactInformation.mapper;

import com.tss.talentsourcingsystem.application.contactInformation.dto.ContactInformationDto;
import com.tss.talentsourcingsystem.application.contactInformation.dto.ContactInformationSaveRequestDto;
import com.tss.talentsourcingsystem.application.contactInformation.entity.EmailContactInformation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */
@Mapper(uses = ContactInformationMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface EmailContactInformationMapper {
    EmailContactInformationMapper INSTANCE=Mappers.getMapper(EmailContactInformationMapper.class);

    EmailContactInformation contactInformationSaveRequestDtoToContactInformation(ContactInformationSaveRequestDto contactInformationSaveRequestDto);

    @Mapping(source = "candidate.id", target = "candidateId")
    @Mapping(target = "contactInformationType", constant = "EMAIL")
    @Mapping(source = "baseAdditionalFields.createdDate", target = "baseAdditionalFieldsCreatedDate")
    @Mapping(source = "baseAdditionalFields.updatedDate", target = "baseAdditionalFieldsUpdatedDate")
    ContactInformationDto emailContactInformationToContactInformationDto(EmailContactInformation save);
}
