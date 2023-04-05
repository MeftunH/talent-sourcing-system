package com.tss.talentsourcingsystem.application.candidate.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tss.talentsourcingsystem.application.candidate.dto.CandidateDto;
import com.tss.talentsourcingsystem.application.candidate.dto.CandidateSaveRequestDto;
import com.tss.talentsourcingsystem.application.candidate.dto.CandidateUpdateRequestDto;
import com.tss.talentsourcingsystem.application.candidate.dto.CandidateUpdateStatusRequestDto;
import com.tss.talentsourcingsystem.application.candidate.enums.CandidateStatus;
import com.tss.talentsourcingsystem.application.candidate.repository.CandidateRepository;
import com.tss.talentsourcingsystem.application.generic.dto.RestResponse;
import com.tss.talentsourcingsystem.application.person.enums.PersonType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Transactional
class CandidateControllerTest {

    private static final String BASE_URL="http://localhost:8081/api/v1/candidates";

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private CandidateRepository candidateRepository;


    @Autowired
    private CandidateController candidateController;

    @BeforeEach
    void setUp() {
        this.mockMvc=MockMvcBuilders.webAppContextSetup(context).build();
        this.objectMapper=new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    void saveCandidateShouldReturnOk() throws Exception {
        CandidateSaveRequestDto requestDto=mock(CandidateSaveRequestDto.class);
        when(requestDto.getName()).thenReturn("John");
        when(requestDto.getSurname()).thenReturn("Doe");
        when(requestDto.getCandidateStatus()).thenReturn(CandidateStatus.HIRED);
        when(requestDto.getPersonType()).thenReturn(PersonType.CANDIDATE);

        ObjectMapper objectMapper=new ObjectMapper();
        CandidateDto responseDto=Mockito.mock(CandidateDto.class);
        when(responseDto.name()).thenReturn("John");
        when(responseDto.surname()).thenReturn("Doe");
        when(responseDto.candidateStatus()).thenReturn(CandidateStatus.HIRED);

        String content=objectMapper.writeValueAsString(requestDto);

        MvcResult result=mockMvc.perform(
                        post(BASE_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content))
                .andExpect(status().isOk())
                .andReturn();

        RestResponse<CandidateDto> restResponse=objectMapper.readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<RestResponse<CandidateDto>>() {
                });

        assertTrue(restResponse.isSuccess());
        assertEquals(responseDto.name(), restResponse.getData().name());
        assertEquals(responseDto.surname(), restResponse.getData().surname());
        assertEquals(responseDto.candidateStatus(), restResponse.getData().candidateStatus());
    }

    @Test
    void getCandidateByIdShouldReturnOk() throws Exception {
        MvcResult result=mockMvc.perform(
                        get(BASE_URL+"/{candidateId}", 252L)
                                .accept(MediaType.APPLICATION_JSON)
                                .content("")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        RestResponse<CandidateDto> restResponse=objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<RestResponse<CandidateDto>>() {
        });

        assertTrue(restResponse.isSuccess());
    }

    @Test
    void updateCandidateShouldReturnOk() throws Exception {
        Long candidateId=252L;
        CandidateUpdateRequestDto requestDto=new CandidateUpdateRequestDto();
        requestDto.setName("John");
        requestDto.setSurname("Doe");
        requestDto.setCandidateStatus(CandidateStatus.HIRED);

        CandidateDto responseDto=mock(CandidateDto.class);
        when(responseDto.id()).thenReturn(candidateId);
        when(responseDto.name()).thenReturn("John");
        when(responseDto.surname()).thenReturn("Doe");
        when(responseDto.candidateStatus()).thenReturn(CandidateStatus.HIRED);


        MvcResult result=mockMvc.perform(
                        put(BASE_URL+"/{candidateId}", candidateId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andReturn();
        RestResponse<CandidateDto> restResponse=objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<RestResponse<CandidateDto>>() {
        });

        assertTrue(restResponse.isSuccess());
        assertEquals(responseDto.id(), restResponse.getData().id());
        assertEquals(responseDto.name(), restResponse.getData().name());
        assertEquals(responseDto.surname(), restResponse.getData().surname());
        assertEquals(responseDto.candidateStatus(), restResponse.getData().candidateStatus());
    }

    @Test
    void deleteCandidate_shouldReturnOk() throws Exception {
        long candidateId=0L;

        mockMvc.perform(get(BASE_URL+"/"+candidateId))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdateCandidateStatus() throws Exception {
        // Create a candidate update request DTO
        CandidateUpdateStatusRequestDto requestDto=mock(CandidateUpdateStatusRequestDto.class);
        when(requestDto.candidateStatus()).thenReturn(CandidateStatus.HIRED);

        // Perform the patch request using MockMvc
        MvcResult result=mockMvc.perform(patch(BASE_URL+"/252/status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk()).andReturn();
        RestResponse<CandidateDto> restResponse=objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<RestResponse<CandidateDto>>() {
        });
        assertTrue(restResponse.isSuccess());
    }

    @Test
    void testGetAllCandidates() throws Exception {

        MvcResult result=mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk()).andReturn();
        RestResponse<List<CandidateDto>> restResponse=objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<RestResponse<List<CandidateDto>>>() {
        });
        assertTrue(restResponse.isSuccess());
    }
}