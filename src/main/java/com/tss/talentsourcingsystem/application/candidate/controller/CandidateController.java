package com.tss.talentsourcingsystem.application.candidate.controller;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.candidate.dto.CandidateDto;
import com.tss.talentsourcingsystem.application.candidate.dto.CandidateSaveRequestDto;
import com.tss.talentsourcingsystem.application.candidate.dto.CandidateUpdateRequestDto;
import com.tss.talentsourcingsystem.application.candidate.dto.CandidateUpdateStatusRequestDto;
import com.tss.talentsourcingsystem.application.candidate.service.CandidateService;
import com.tss.talentsourcingsystem.application.generic.dto.RestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/candidates")
public class CandidateController {
    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService=candidateService;
    }

    @PostMapping
    public ResponseEntity<RestResponse<CandidateDto>> saveCandidate(@RequestBody CandidateSaveRequestDto candidateSaveRequestDto) {
        CandidateDto candidateDto=candidateService.saveCandidate(candidateSaveRequestDto);
        return ResponseEntity.ok(RestResponse.of(candidateDto));
    }

    @GetMapping("/{candidateId}")
    public ResponseEntity<RestResponse<CandidateDto>> getCandidateById(@PathVariable Long candidateId) {
        CandidateDto candidateDto=candidateService.getCandidateDtoById(candidateId);
        return ResponseEntity.ok(RestResponse.of(candidateDto));
    }

    @PutMapping("/{candidateId}")
    public ResponseEntity<RestResponse<CandidateDto>> updateCandidate(@PathVariable Long candidateId, @RequestBody CandidateUpdateRequestDto candidateUpdateRequestDto) {
        CandidateDto candidateDto=candidateService.updateCandidate(candidateId, candidateUpdateRequestDto);
        return ResponseEntity.ok(RestResponse.of(candidateDto));
    }

    @DeleteMapping("/{candidateId}")
    public ResponseEntity<RestResponse<CandidateDto>> deleteCandidate(@PathVariable Long candidateId) {
        candidateService.deleteCandidate(candidateId);
        return ResponseEntity.ok(RestResponse.empty());
    }

    @PatchMapping("/{candidateId}/status")
    public ResponseEntity<RestResponse<CandidateDto>> updateCandidateStatus(@PathVariable Long candidateId, @RequestBody CandidateUpdateStatusRequestDto candidateUpdateStatusRequestDto) {
        CandidateDto candidateDto=candidateService.updateCandidateStatus(candidateId, candidateUpdateStatusRequestDto);
        return ResponseEntity.ok(RestResponse.of(candidateDto));
    }
}
