package com.tss.talentsourcingsystem.application.contactInformation.phoneNumberModule.dto;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.contactInformation.dto.ContactInformationDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class PhoneNumberContactInformationDto extends ContactInformationDto {
    private String phoneNumber;
}
