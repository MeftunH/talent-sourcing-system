package com.tss.talentsourcingsystem.application.contactInformation.service;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.contactInformation.dto.ContactInformationDto;
import com.tss.talentsourcingsystem.application.contactInformation.dto.ContactInformationSaveRequestDto;

public interface ContactInformationService {
  ContactInformationDto saveContactInformation(ContactInformationSaveRequestDto contactInformationSaveRequestDto);
}
