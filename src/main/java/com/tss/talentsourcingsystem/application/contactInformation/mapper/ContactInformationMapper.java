package com.tss.talentsourcingsystem.application.contactInformation.mapper;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.contactInformation.dto.ContactInformationDto;
import com.tss.talentsourcingsystem.application.contactInformation.entity.ContactInformation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy= ReportingPolicy.IGNORE,componentModel = "spring")
public interface ContactInformationMapper {
    ContactInformationMapper INSTANCE = Mappers.getMapper(ContactInformationMapper.class);
    @Mapping(source = "baseAdditionalFields.updatedDate", target = "baseAdditionalFieldsUpdatedDate")
    @Mapping(source = "baseAdditionalFields.createdDate", target = "baseAdditionalFieldsCreatedDate")
    ContactInformationDto convertToContactInformationDto(ContactInformation contactInformation);
}
