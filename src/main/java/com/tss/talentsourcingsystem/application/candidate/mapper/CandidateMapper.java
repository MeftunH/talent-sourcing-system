package com.tss.talentsourcingsystem.application.candidate.mapper;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.candidate.dto.CandidateDto;
import com.tss.talentsourcingsystem.application.candidate.dto.CandidateSaveRequestDto;
import com.tss.talentsourcingsystem.application.candidate.entity.Candidate;
import com.tss.talentsourcingsystem.application.contactInformation.mapper.ContactInformationMapper;
import com.tss.talentsourcingsystem.application.person.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = ContactInformationMapper.class,unmappedTargetPolicy= ReportingPolicy.IGNORE)
public interface CandidateMapper {
    CandidateMapper INSTANCE=Mappers.getMapper(CandidateMapper.class);

    Candidate convertToCandidate(CandidateSaveRequestDto candidateSaveRequestDto);

    @Mapping(source="baseAdditionalFields.createdDate",target="baseAdditionalFieldsCreatedDate")
    @Mapping(source="baseAdditionalFields.updatedDate",target="baseAdditionalFieldsUpdatedDate")
    CandidateDto convertToCandidateDto(Candidate savedCandidate);

    List<CandidateDto> convertToCandidateDtoList(List<Candidate> candidateList);
}
