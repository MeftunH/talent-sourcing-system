package com.tss.talentsourcingsystem.application.candidate.mapper;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.candidate.dto.CandidateDto;
import com.tss.talentsourcingsystem.application.candidate.dto.CandidateSaveRequestDto;
import com.tss.talentsourcingsystem.application.candidate.entity.Candidate;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy= ReportingPolicy.IGNORE,componentModel="spring")
public interface CandidateMapper {
    CandidateMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(CandidateMapper.class);

    Candidate convertToCandidate(CandidateSaveRequestDto candidateSaveRequestDto);

    CandidateDto convertToCandidateDto(Candidate savedCandidate);
}
