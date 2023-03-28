package com.tss.talentsourcingsystem.application.candidate.controller;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.candidate.dto.CandidateDto;
import com.tss.talentsourcingsystem.application.candidate.dto.CandidateSaveRequestDto;
import com.tss.talentsourcingsystem.application.candidate.service.CandidateService;
import com.tss.talentsourcingsystem.application.generic.dto.RestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/candidates")
public class CandidateController {
    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService=candidateService;
    }

    @PostMapping
    public ResponseEntity<RestResponse<CandidateDto>> saveCandidate(@RequestBody CandidateSaveRequestDto candidateSaveRequestDto) {
        CandidateDto candidateDto = candidateService.saveCandidate(candidateSaveRequestDto);
        return ResponseEntity.ok(RestResponse.of(candidateDto));
    }
}
