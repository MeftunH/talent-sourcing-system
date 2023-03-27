package com.tss.talentsourcingsystem.application.candidate.service;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.candidate.dto.CandidateDto;
import com.tss.talentsourcingsystem.application.candidate.dto.CandidateSaveRequestDto;

public interface CandidateService {
    CandidateDto saveCandidate(CandidateSaveRequestDto candidateSaveRequestDto);
}
