package com.tss.talentsourcingsystem.application.candidate.dto;

import com.tss.talentsourcingsystem.application.candidate.entity.Candidate;
import com.tss.talentsourcingsystem.application.candidate.enums.CandidateStatus;
import com.tss.talentsourcingsystem.application.contactInformation.dto.ContactInformationSaveRequestDto;
import com.tss.talentsourcingsystem.application.person.dto.PersonDto;
import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

/**
 * A DTO for the {@link Candidate} entity
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CandidateSaveRequestDto extends PersonDto implements Serializable {
    private CandidateStatus candidateStatus;
    @Nullable
    private Set<ContactInformationSaveRequestDto> contactInformation;

}