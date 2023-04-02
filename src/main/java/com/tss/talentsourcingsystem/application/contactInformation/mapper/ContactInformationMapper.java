package com.tss.talentsourcingsystem.application.contactInformation.mapper;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.contactInformation.dto.ContactInformationDto;
import com.tss.talentsourcingsystem.application.contactInformation.dto.ContactInformationSaveRequestDto;
import com.tss.talentsourcingsystem.application.contactInformation.entity.ContactInformation;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface ContactInformationMapper {
    ContactInformationMapper INSTANCE=Mappers.getMapper(ContactInformationMapper.class);

    default ContactInformation map(ContactInformationSaveRequestDto dto) {
        ContactInformation entity=null;
        switch (dto.contactInformationType()) {
            case EMAIL:
                entity=EmailContactInformationMapper.INSTANCE.contactInformationSaveRequestDtoToContactInformation(dto);
                break;
            case PHONE_NUMBER:
                entity=PhoneNumberContactInformationMapper.INSTANCE.contactInformationSaveRequestDtoToContactInformation(dto);
                break;
        }
        return entity;
    }

    @Named("contactInformationSetMapping")
    default Set<ContactInformation> contactInformationSetMapping(Set<ContactInformationSaveRequestDto> dtos) {
        if (dtos==null) {
            return Collections.emptySet();
        }

        return dtos.stream()
                .map(this::map)
                .collect(Collectors.toSet());
    }

    ContactInformationDto contactInformationToContactInformationDto(ContactInformation savedContactInformation);

    List<ContactInformationDto> contactInformationListToContactInformationDtoList(List<ContactInformation> contactInformationList);
}