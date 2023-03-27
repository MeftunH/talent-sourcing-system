package com.tss.talentsourcingsystem.application.candidate.dto;

import com.tss.talentsourcingsystem.application.candidate.entity.Candidate;
import com.tss.talentsourcingsystem.application.candidate.enums.CandidateStatus;
import com.tss.talentsourcingsystem.application.contactInformation.dto.ContactInformationSaveRequestDto;
import com.tss.talentsourcingsystem.application.person.enums.PersonType;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * A DTO for the {@link Candidate} entity
 */
@Data
public class CandidateSaveRequestDto implements Serializable {
    private final String name;
    private final String surname;
    private final PersonType personType;
    private final Set<ContactInformationSaveRequestDto> contactInformation;
    private final CandidateStatus candidateStatus;

}