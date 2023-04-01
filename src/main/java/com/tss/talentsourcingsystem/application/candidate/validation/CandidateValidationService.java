package com.tss.talentsourcingsystem.application.candidate.validation;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.candidate.entity.Candidate;
import com.tss.talentsourcingsystem.application.person.entity.Person;

import java.util.Optional;

public interface CandidateValidationService {
    void checkCandidateExists(Candidate candidate);
    void checkOptionalCandidateExists(Optional<Person> candidateOptional);

    void validateCandidate(Candidate candidate);

}
