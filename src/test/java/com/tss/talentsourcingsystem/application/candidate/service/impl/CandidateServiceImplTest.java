package com.tss.talentsourcingsystem.application.candidate.service.impl;

import com.tss.talentsourcingsystem.application.candidate.dto.CandidateDto;
import com.tss.talentsourcingsystem.application.candidate.dto.CandidateSaveRequestDto;
import com.tss.talentsourcingsystem.application.candidate.dto.CandidateUpdateRequestDto;
import com.tss.talentsourcingsystem.application.candidate.entity.Candidate;
import com.tss.talentsourcingsystem.application.candidate.enums.CandidateStatus;
import com.tss.talentsourcingsystem.application.candidate.mapper.CandidateMapper;
import com.tss.talentsourcingsystem.application.candidate.repository.CandidateRepository;
import com.tss.talentsourcingsystem.application.candidate.validation.CandidateValidationService;
import com.tss.talentsourcingsystem.application.general.exception.ItemNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CandidateServiceImplTest {

    @InjectMocks
    @Spy
    private CandidateServiceImpl candidateService;
    @Mock
    private CandidateRepository candidateRepository;
    @Mock
    private CandidateValidationService candidateValidationService;
    @Test
    void shouldSaveCandidate() {
        Candidate mockCandidate = mock(Candidate.class);
        when(mockCandidate.getCandidateStatus()).thenReturn(CandidateStatus.INTERVIEWING);

        CandidateSaveRequestDto mockRequestDto = mock(CandidateSaveRequestDto.class);
        when(mockRequestDto.getCandidateStatus()).thenReturn(CandidateStatus.INTERVIEWING);
        when(candidateRepository.save(any())).thenReturn(mockCandidate);

        CandidateDto result = candidateService.saveCandidate(mockRequestDto);

        Assertions.assertEquals(mockCandidate.getId(), result.id());
    }
    @Test
    void shouldThrowExceptionWhenCandidateNotFound() {
        assertThrows(NullPointerException.class, ()->candidateService.saveCandidate(null));

    }

    @Test
    void shouldGetCandidateWhenCandidate() {
        long id=252L;
        // Create a mock candidate object
        Candidate mockCandidate = Mockito.mock(Candidate.class);
        when(mockCandidate.getId()).thenReturn(id);
        when(candidateRepository.findById(anyLong())).thenReturn(Optional.of(mockCandidate));

         Candidate candidate=candidateService.getCandidateById(id);

        Assertions.assertEquals(mockCandidate.getId(),candidate.getId());
    }
    @Test
    void shouldGetCandidateThrowExceptionWhenCandidateNotFound() {
        doThrow(ItemNotFoundException.class).when(candidateRepository).findById(anyLong());
        assertThrows(ItemNotFoundException.class,()->candidateService.getCandidateById(anyLong()));
    }

    @Test
    void shouldGetCandidateDtoById() {
        long id=252L;
        Candidate mockCandidate = Mockito.mock(Candidate.class);
        when(mockCandidate.getId()).thenReturn(id);
        when(candidateRepository.findById(anyLong())).thenReturn(Optional.of(mockCandidate));
        CandidateMapper mockMapper = Mockito.mock(CandidateMapper.class);
        CandidateDto mockDto = Mockito.mock(CandidateDto.class);
        CandidateDto candidate=candidateService.getCandidateDtoById(id);

        Assertions.assertEquals(mockCandidate.getId(),candidate.id());
    }

    @Test
    void shouldUpdateCandidate() {
        Candidate mockCandidate = new Candidate();
        mockCandidate.setId(1L);
        mockCandidate.setName("John");
        mockCandidate.setSurname("Doe");
        mockCandidate.setCandidateStatus(CandidateStatus.INTERVIEWING);
        when(candidateRepository.findById(anyLong())).thenReturn(Optional.of(mockCandidate));

        CandidateUpdateRequestDto mockUpdateRequestDto = mock(CandidateUpdateRequestDto.class);

        when(mockUpdateRequestDto.getName()).thenReturn("Jane");
        when(mockUpdateRequestDto.getSurname()).thenReturn("Smith");
        when(mockUpdateRequestDto.getCandidateStatus()).thenReturn(CandidateStatus.INTERVIEWING);

        when(candidateRepository.save(any(Candidate.class))).thenReturn(mockCandidate);

        when(candidateService.getCandidateById(anyLong())).thenReturn(mockCandidate);
        CandidateDto result = candidateService.updateCandidate(mockCandidate.getId(), mockUpdateRequestDto);

        // Check that the correct DTO is returned
        Assertions.assertEquals(mockUpdateRequestDto.getName(), result.name());
        Assertions.assertEquals(mockUpdateRequestDto.getSurname(), result.surname());
        Assertions.assertEquals(mockUpdateRequestDto.getCandidateStatus(), result.candidateStatus());
    }

    @Test
    void shouldThrowExceptionWhenCandidateNotFoundForUpdate() {
        CandidateUpdateRequestDto mockUpdateRequestDto = mock(CandidateUpdateRequestDto.class);

        assertThrows(NoSuchElementException.class,()->candidateService.updateCandidate(null, mockUpdateRequestDto));
    }

    @Test
    void shouldDeleteCandidate() {
        long id=252L;
        // Create a mock candidate object
        Candidate mockCandidate = Mockito.mock(Candidate.class);
        when(mockCandidate.getId()).thenReturn(id);
        when(candidateRepository.findById(anyLong())).thenReturn(Optional.of(mockCandidate));
        CandidateMapper mockMapper = Mockito.mock(CandidateMapper.class);
        CandidateDto mockDto = Mockito.mock(CandidateDto.class);
        CandidateDto candidate=candidateService.getCandidateDtoById(id);
        when(candidateService.getCandidateById(id)).thenReturn(mockCandidate);

        // Call the method being tested
        candidateService.deleteCandidate(1L);

        // Verify that the candidate was deleted
        verify(candidateRepository).delete(mockCandidate);
    }
    @Test
    void shouldThrowExceptionIfCandidateNotFound() {
        assertThrows(NoSuchElementException.class, ()->candidateService.deleteCandidate(anyLong()));

    }
    @Test
    void shouldReturnAllCandidates() {
        List<CandidateDto> result = candidateService.getAllCandidates();
        assertNotNull(result);
    }
}