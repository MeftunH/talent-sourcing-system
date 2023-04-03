package com.tss.talentsourcingsystem.application.candidate.service.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.candidate.dto.CandidateDto;
import com.tss.talentsourcingsystem.application.candidate.dto.CandidateSaveRequestDto;
import com.tss.talentsourcingsystem.application.candidate.dto.CandidateUpdateRequestDto;
import com.tss.talentsourcingsystem.application.candidate.dto.CandidateUpdateStatusRequestDto;
import com.tss.talentsourcingsystem.application.candidate.entity.Candidate;
import com.tss.talentsourcingsystem.application.candidate.mapper.CandidateMapper;
import com.tss.talentsourcingsystem.application.candidate.repository.CandidateRepository;
import com.tss.talentsourcingsystem.application.candidate.service.CandidateService;
import com.tss.talentsourcingsystem.application.candidate.validation.CandidateValidationService;
import com.tss.talentsourcingsystem.application.generic.service.BaseService;
import com.tss.talentsourcingsystem.application.person.entity.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateServiceImpl extends BaseService<Candidate> implements CandidateService {
    private final CandidateRepository candidateRepository;
    private final CandidateValidationService candidateValidationService;

    public CandidateServiceImpl(CandidateRepository candidateRepository, CandidateValidationService candidateValidationService) {
        this.candidateRepository=candidateRepository;
        this.candidateValidationService=candidateValidationService;
    }

    private static List<Candidate> getCandidates(List<Person> candidates) {
        List<Candidate> candidateList=new ArrayList<>();
        for (Person person : candidates) {
            Candidate candidate=(Candidate) person;
            candidateList.add(candidate);
        }
        return candidateList;
    }

    @Override
    public CandidateDto saveCandidate(CandidateSaveRequestDto candidateSaveRequestDto) {
        Candidate candidate=CandidateMapper.INSTANCE.convertToCandidate(candidateSaveRequestDto);
        setAdditionalFields(candidate);
        candidateValidationService.validateCandidate(candidate);
        candidate=candidateRepository.save(candidate);
        return CandidateMapper.INSTANCE.convertToCandidateDto(candidate);
    }

    @Override
    public Candidate getCandidateById(Long candidateId) {
        Optional<Person> candidateOptional=candidateRepository.findById(candidateId);
        candidateValidationService.checkOptionalCandidateExists(candidateOptional);
        return (Candidate) candidateOptional.get();
    }

    @Override
    public CandidateDto getCandidateDtoById(Long candidateId) {
        Candidate candidate=getCandidateById(candidateId);
        return CandidateMapper.INSTANCE.convertToCandidateDto(candidate);
    }

    @Override
    public CandidateDto updateCandidate(Long candidateId, CandidateUpdateRequestDto candidateUpdateRequestDto) {
        Candidate candidate=getCandidateById(candidateId);
        setAdditionalFields(candidate);
        candidate.setCandidateStatus(candidateUpdateRequestDto.getCandidateStatus());
        candidate.setName(candidateUpdateRequestDto.getName());
        candidate.setSurname(candidateUpdateRequestDto.getSurname());

        candidateValidationService.validateCandidate(candidate);

        candidate=candidateRepository.save(candidate);
        return CandidateMapper.INSTANCE.convertToCandidateDto(candidate);
    }

    @Override
    public void deleteCandidate(Long candidateId) {
        Candidate candidate=getCandidateById(candidateId);
        candidateRepository.delete(candidate);
    }

    @Override
    public CandidateDto updateCandidateStatus(Long candidateId, CandidateUpdateStatusRequestDto candidateUpdateStatusRequestDto) {
        candidateValidationService.validateCandidateStatusIsExists(candidateUpdateStatusRequestDto.candidateStatus());
        Candidate candidate=getCandidateById(candidateId);
        candidate.setCandidateStatus(candidateUpdateStatusRequestDto.candidateStatus());
        candidate=candidateRepository.save(candidate);
        return CandidateMapper.INSTANCE.convertToCandidateDto(candidate);
    }

    @Override
    public List<CandidateDto> getAllCandidates() {
        List<Person> candidates=candidateRepository.findAll();
        List<Candidate> candidateList=getCandidates(candidates);
        return CandidateMapper.INSTANCE.convertToCandidateDtoList(candidateList);
    }
}
