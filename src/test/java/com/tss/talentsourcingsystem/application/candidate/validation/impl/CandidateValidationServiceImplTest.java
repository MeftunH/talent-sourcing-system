package com.tss.talentsourcingsystem.application.candidate.validation.impl;

import com.tss.talentsourcingsystem.application.candidate.entity.Candidate;
import com.tss.talentsourcingsystem.application.candidate.enums.CandidateErrorMessage;
import com.tss.talentsourcingsystem.application.candidate.enums.CandidateStatus;
import com.tss.talentsourcingsystem.application.candidate.repository.CandidateRepository;
import com.tss.talentsourcingsystem.application.person.enums.PersonType;
import com.tss.talentsourcingsystem.application.general.exception.IllegalFieldException;
import com.tss.talentsourcingsystem.application.general.exception.ItemNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CandidateValidationServiceImplTest {

    @InjectMocks
    private CandidateValidationServiceImpl candidateValidationService;
    @Mock
    private CandidateRepository candidateRepository;

    @Test
    void shouldCheckCandidateExistsThrowItemNotFoundExceptionWhenCandidateIsNull() {
        // Arrange
        Candidate nullCandidate = null;

        // Act & Assert
        Assertions.assertThrows(ItemNotFoundException.class, () -> {
            candidateValidationService.checkCandidateExists(nullCandidate);
        }, CandidateErrorMessage.CANDIDATE_NOT_FOUND.getMessage());
    }
    @Test
    void shouldCheckCandidateExistsNotThrowException() {
        Candidate candidate = new Candidate();
        candidate.setId(1L);
        candidate.setName("John");
        candidate.setSurname("Doe");

        candidateValidationService.checkCandidateExists(candidate);
    }
    @Test
    void shouldValidateCandidateStatusIsExistsDoesNotThrowException() {
       assertDoesNotThrow(()->candidateValidationService.validateCandidateStatusIsExists(CandidateStatus.INTERVIEWING));
    }
    @Test
    void shouldValidateCandidateDoesNotThrowException() {
        Candidate candidate = new Candidate();
        candidate.setName("John");
        candidate.setSurname("Doe");
        candidate.setCandidateStatus(CandidateStatus.HIRED);
        candidate.setPersonType(PersonType.CANDIDATE);

assertDoesNotThrow(() -> {
            candidateValidationService.validateCandidate(candidate);
        });

    }

    @Test
    void shouldValidateCandidateThrowIllegalFieldExceptionWhenCandidateStatusIsNull() {
        Candidate candidate = new Candidate();
        candidate.setName(null);
        candidate.setSurname(null);
        candidate.setCandidateStatus(null);
        candidate.setPersonType(PersonType.CANDIDATE);

        assertThrows(IllegalFieldException.class, () -> {
            candidateValidationService.validateCandidate(candidate);
        });
    }

    @Test
    void shouldValidateCandidateThrowIllegalFieldExceptionWhenPersonTypeIsNull() {
        Candidate candidate = new Candidate();
        candidate.setName("John");
        candidate.setSurname("Doe");
        candidate.setCandidateStatus(CandidateStatus.HIRED);
        candidate.setPersonType(null);

        assertThrows(IllegalFieldException.class, () -> {
            candidateValidationService.validateCandidate(candidate);
        });
    }

    @Test
    void shouldValidateCandidateStatusIsExistsThrowsExceptionWhenCandidateStatusIsNull() {

        // invalid candidate status
        assertThrows(IllegalFieldException.class, () -> {
            candidateValidationService.validateCandidateStatusIsExists(null);
        });
    }
    @Test
    void shouldValidateCandidateStatusIsExistsDoesNotThrowsException() {

        assertDoesNotThrow(() -> {
            candidateValidationService.validateCandidateStatusIsExists(CandidateStatus.HIRED);
        });
    }
}