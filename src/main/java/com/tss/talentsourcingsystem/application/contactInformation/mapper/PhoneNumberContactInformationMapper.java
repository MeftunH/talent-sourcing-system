package com.tss.talentsourcingsystem.application.contactInformation.mapper;

import com.tss.talentsourcingsystem.application.contactInformation.dto.ContactInformationDto;
import com.tss.talentsourcingsystem.application.contactInformation.dto.ContactInformationSaveRequestDto;
import com.tss.talentsourcingsystem.application.contactInformation.entity.ContactInformation;
import com.tss.talentsourcingsystem.application.contactInformation.entity.PhoneNumberContactInformation;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")

public interface PhoneNumberContactInformationMapper {
    PhoneNumberContactInformationMapper INSTANCE = Mappers.getMapper(PhoneNumberContactInformationMapper.class);
    PhoneNumberContactInformation contactInformationSaveRequestDtoToContactInformation(ContactInformationSaveRequestDto contactInformationSaveRequestDto);

    ContactInformationDto contactInformationToContactInformationDto(ContactInformation savedContactInformation);
}
