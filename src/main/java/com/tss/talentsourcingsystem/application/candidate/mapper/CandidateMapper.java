package com.tss.talentsourcingsystem.application.candidate.mapper;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.candidate.dto.CandidateDto;
import com.tss.talentsourcingsystem.application.candidate.dto.CandidateSaveRequestDto;
import com.tss.talentsourcingsystem.application.candidate.entity.Candidate;
import com.tss.talentsourcingsystem.application.contactInformation.mapper.ContactInformationMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = ContactInformationMapper.class)
public interface CandidateMapper {
    CandidateMapper INSTANCE=Mappers.getMapper(CandidateMapper.class);

    Candidate convertToCandidate(CandidateSaveRequestDto candidateSaveRequestDto);

    CandidateDto convertToCandidateDto(Candidate savedCandidate);
}
