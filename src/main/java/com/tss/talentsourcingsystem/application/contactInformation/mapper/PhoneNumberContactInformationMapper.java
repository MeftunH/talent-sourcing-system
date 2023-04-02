package com.tss.talentsourcingsystem.application.contactInformation.mapper;

import com.tss.talentsourcingsystem.application.contactInformation.dto.ContactInformationDto;
import com.tss.talentsourcingsystem.application.contactInformation.dto.ContactInformationSaveRequestDto;
import com.tss.talentsourcingsystem.application.contactInformation.entity.ContactInformation;
import com.tss.talentsourcingsystem.application.contactInformation.entity.PhoneNumberContactInformation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */
@Mapper(uses = ContactInformationMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PhoneNumberContactInformationMapper {
    PhoneNumberContactInformationMapper INSTANCE=Mappers.getMapper(PhoneNumberContactInformationMapper.class);

    PhoneNumberContactInformation contactInformationSaveRequestDtoToContactInformation(ContactInformationSaveRequestDto contactInformationSaveRequestDto);

    @Mapping(source = "candidate.id", target = "candidateId")
    @Mapping(target = "contactInformationType", constant = "PHONE_NUMBER")
    @Mapping(source = "baseAdditionalFields.createdDate", target = "baseAdditionalFieldsCreatedDate")
    @Mapping(source = "baseAdditionalFields.updatedDate", target = "baseAdditionalFieldsUpdatedDate")
    ContactInformationDto contactInformationToContactInformationDto(PhoneNumberContactInformation savedContactInformation);

    List<ContactInformation> phoneNumberContactInformationListToContactInformationList(List<PhoneNumberContactInformation> phoneNumberContactInformationList);


    ContactInformationDto phoneNumberContactInformationToContactInformationDtoList(PhoneNumberContactInformation phoneNumberContactInformation);

    ContactInformationDto contactInformationToContactInformationDto(ContactInformationSaveRequestDto contactInformationSaveRequestDto);
}
