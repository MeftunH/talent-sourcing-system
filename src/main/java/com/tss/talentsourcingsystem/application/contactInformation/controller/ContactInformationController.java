package com.tss.talentsourcingsystem.application.contactInformation.controller;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.contactInformation.dto.ContactInformationDto;
import com.tss.talentsourcingsystem.application.contactInformation.dto.ContactInformationSaveRequestDto;
import com.tss.talentsourcingsystem.application.contactInformation.service.ContactInformationService;
import com.tss.talentsourcingsystem.application.generic.dto.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contact-informations")
@RequiredArgsConstructor
public class ContactInformationController {
    private final ContactInformationService contactInformationService;

    @PostMapping
    public ResponseEntity<RestResponse<ContactInformationDto>> saveContactInformation(@RequestBody ContactInformationSaveRequestDto contactInformationSaveRequestDto) {
        ContactInformationDto contactInformationDto = contactInformationService.saveContactInformation(contactInformationSaveRequestDto);
        return ResponseEntity.ok(RestResponse.of(contactInformationDto));
    }
    @GetMapping("candidate/{candidateId}")
    public ResponseEntity<RestResponse<List<ContactInformationDto>>> getContactInformationByCandidateId(@PathVariable Long candidateId) {
        List<ContactInformationDto> contactInformationDto = contactInformationService.getContactInformationByCandidateId(candidateId);
        return ResponseEntity.ok(RestResponse.of(contactInformationDto));
    }

    @PutMapping("candidate/{candidateId}")
    public ResponseEntity<RestResponse<ContactInformationDto>> updateContactInformation(@PathVariable Long candidateId, @RequestBody ContactInformationSaveRequestDto contactInformationSaveRequestDto) {
        ContactInformationDto contactInformationDto = contactInformationService.updateContactInformation(candidateId, contactInformationSaveRequestDto);
        return ResponseEntity.ok(RestResponse.of(contactInformationDto));
    }
}
