package com.tss.talentsourcingsystem.application.contactInformation.controller;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.contactInformation.dto.ContactInformationDto;
import com.tss.talentsourcingsystem.application.contactInformation.dto.ContactInformationSaveRequestDto;
import com.tss.talentsourcingsystem.application.contactInformation.service.ContactInformationService;
import com.tss.talentsourcingsystem.application.generic.dto.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
