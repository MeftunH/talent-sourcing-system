package com.tss.talentsourcingsystem.application.contactInformation.service;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.contactInformation.dto.ContactInformationDto;
import com.tss.talentsourcingsystem.application.contactInformation.dto.ContactInformationSaveRequestDto;

import java.util.List;

public interface ContactInformationService {
    ContactInformationDto saveContactInformation(ContactInformationSaveRequestDto contactInformationSaveRequestDto);

    List<ContactInformationDto> getContactInformationByCandidateId(Long candidateId);

    ContactInformationDto updateContactInformation(Long candidateId, ContactInformationSaveRequestDto contactInformationUpdateRequestDto);
}
