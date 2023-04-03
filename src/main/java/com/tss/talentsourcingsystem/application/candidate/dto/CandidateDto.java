package com.tss.talentsourcingsystem.application.candidate.dto;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tss.talentsourcingsystem.application.candidate.enums.CandidateStatus;
import com.tss.talentsourcingsystem.application.contactInformation.dto.ContactInformationDto;
import com.tss.talentsourcingsystem.application.person.enums.PersonType;

import java.util.Date;
import java.util.Set;

public record CandidateDto(Long id, String name, String surname, PersonType personType,
                           Set<ContactInformationDto> contactInformation, CandidateStatus candidateStatus,
                           @JsonFormat(pattern = "yyyy/MM/dd") Date baseAdditionalFieldsCreatedDate,
                           @JsonFormat(pattern = "yyyy/MM/dd") Date baseAdditionalFieldsUpdatedDate) {
}
