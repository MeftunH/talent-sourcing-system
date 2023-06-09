package com.tss.talentsourcingsystem.application.candidate.service;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.candidate.dto.CandidateDto;
import com.tss.talentsourcingsystem.application.candidate.dto.CandidateSaveRequestDto;
import com.tss.talentsourcingsystem.application.candidate.dto.CandidateUpdateRequestDto;
import com.tss.talentsourcingsystem.application.candidate.dto.CandidateUpdateStatusRequestDto;
import com.tss.talentsourcingsystem.application.candidate.entity.Candidate;

import java.util.List;

public interface CandidateService {
    CandidateDto saveCandidate(CandidateSaveRequestDto candidateSaveRequestDto);

    Candidate getCandidateById(Long candidateId);
    CandidateDto getCandidateDtoById(Long candidateId);

    CandidateDto updateCandidate(Long candidateId, CandidateUpdateRequestDto candidateUpdateRequestDto);

    void deleteCandidate(Long candidateId);

    CandidateDto updateCandidateStatus(Long candidateId, CandidateUpdateStatusRequestDto candidateStatus);

    List<CandidateDto> getAllCandidates();
}
