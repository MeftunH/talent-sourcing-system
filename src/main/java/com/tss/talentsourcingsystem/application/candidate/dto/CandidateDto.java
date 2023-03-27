package com.tss.talentsourcingsystem.application.candidate.dto;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.candidate.enums.CandidateStatus;
import com.tss.talentsourcingsystem.application.contactInformation.dto.ContactInformationSaveRequestDto;
import com.tss.talentsourcingsystem.application.person.enums.PersonType;
import lombok.Data;

import java.util.Set;

@Data
public class CandidateDto {
    private final Long id;
    private final String name;
    private final String surname;
    private final PersonType personType;
    private final Set<ContactInformationSaveRequestDto> contactInformation;
    private final CandidateStatus candidateStatus;
}
