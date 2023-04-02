package com.tss.talentsourcingsystem.application.contactInformation.dto;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.contactInformation.enums.ContactInformationType;
import lombok.Data;

@Data
public class ContactInformationUpdateRequestDto {
    private final ContactInformationType contactInformationType;
    private final Long candidateId;
    private String phoneNumber;
    private String email;
}
