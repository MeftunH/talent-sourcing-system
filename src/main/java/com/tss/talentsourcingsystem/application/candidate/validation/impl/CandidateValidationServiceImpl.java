package com.tss.talentsourcingsystem.application.candidate.validation.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.candidate.entity.Candidate;
import com.tss.talentsourcingsystem.application.candidate.enums.CandidateErrorMessage;
import com.tss.talentsourcingsystem.application.candidate.repository.CandidateRepository;
import com.tss.talentsourcingsystem.application.candidate.validation.CandidateValidationService;
import com.tss.talentsourcingsystem.application.general.exception.GeneralBusinessException;
import com.tss.talentsourcingsystem.application.general.exception.IllegalFieldException;
import com.tss.talentsourcingsystem.application.person.entity.Person;
import com.tss.talentsourcingsystem.application.person.enums.PersonErrorMessage;
import com.tss.talentsourcingsystem.application.person.enums.PersonType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Objects;
import java.util.Optional;

@Service
@ControllerAdvice
public class CandidateValidationServiceImpl implements CandidateValidationService {
    private final CandidateRepository candidateRepository;

    public CandidateValidationServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository=candidateRepository;
    }

    @Override
    public void checkCandidateExists(Candidate candidate) {
        if (candidate==null) {
            throw new GeneralBusinessException(CandidateErrorMessage.CANDIDATE_NOT_FOUND);
        }
    }
    @Override
    public void checkOptionalCandidateExists(Optional<Person> candidateOptional) {
        if (candidateOptional.isEmpty()) {
            throw new GeneralBusinessException(CandidateErrorMessage.CANDIDATE_NOT_FOUND);
        }
    }

    @Override
    public void validateCandidate(Candidate candidate) {
        validateAreFieldsNonNull(candidate);
        validateIsPersonTypeCandidate(candidate);

    }
    private void validateAreFieldsNonNull(Candidate candidate) {
        if (Objects.isNull(candidate.getName())||Objects.isNull(candidate.getSurname())||Objects.isNull(candidate.getCandidateStatus())||Objects.isNull(candidate.getPersonType())) {
            throw new IllegalFieldException(PersonErrorMessage.FIELD_CANNOT_BE_NULL);
        }
    }

    private void validateIsPersonTypeCandidate(Candidate candidate) {
        if (!candidate.getPersonType().equals(PersonType.CANDIDATE)) {
            throw new IllegalFieldException(CandidateErrorMessage.PERSON_TYPE_MUST_BE_CANDIDATE);
        }
    }
}
