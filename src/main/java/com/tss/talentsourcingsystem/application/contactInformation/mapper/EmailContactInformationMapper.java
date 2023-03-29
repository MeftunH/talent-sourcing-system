package com.tss.talentsourcingsystem.application.contactInformation.mapper;

import com.tss.talentsourcingsystem.application.contactInformation.dto.ContactInformationDto;
import com.tss.talentsourcingsystem.application.contactInformation.dto.ContactInformationSaveRequestDto;
import com.tss.talentsourcingsystem.application.contactInformation.entity.ContactInformation;
import com.tss.talentsourcingsystem.application.contactInformation.entity.EmailContactInformation;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface EmailContactInformationMapper {
    EmailContactInformationMapper INSTANCE = Mappers.getMapper(EmailContactInformationMapper.class);
    EmailContactInformation contactInformationSaveRequestDtoToContactInformation(ContactInformationSaveRequestDto contactInformationSaveRequestDto);

    ContactInformationDto contactInformationToContactInformationDto(ContactInformation savedContactInformation);
}
