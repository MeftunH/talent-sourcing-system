package com.tss.talentsourcingsystem.application.candidate.service.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.candidate.dto.CandidateDto;
import com.tss.talentsourcingsystem.application.candidate.dto.CandidateSaveRequestDto;
import com.tss.talentsourcingsystem.application.candidate.entity.Candidate;
import com.tss.talentsourcingsystem.application.candidate.mapper.CandidateMapper;
import com.tss.talentsourcingsystem.application.candidate.repository.CandidateRepository;
import com.tss.talentsourcingsystem.application.candidate.service.CandidateService;
import com.tss.talentsourcingsystem.application.generic.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class CandidateServiceImpl extends BaseService<Candidate> implements CandidateService {
    private final CandidateRepository candidateRepository;

    public CandidateServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository=candidateRepository;
    }
    @Override
    public CandidateDto saveCandidate(CandidateSaveRequestDto candidateSaveRequestDto) {
        Candidate candidate = CandidateMapper.INSTANCE.convertToCandidate(candidateSaveRequestDto);

        setAdditionalFields(candidate);
        Candidate savedCandidate = candidateRepository.save(candidate);
        return CandidateMapper.INSTANCE.convertToCandidateDto(savedCandidate);
    }
}
