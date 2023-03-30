package com.tss.talentsourcingsystem.application.contactInformation.dto;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.contactInformation.enums.ContactInformationType;
import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class ContactInformationDto {
    private Long id;
    private ContactInformationType contactInformationType;
    private Long candidateId;
    @Nullable
    private String phoneNumber;
    @Nullable
    private String emailAddress;
    private Date baseAdditionalFieldsCreatedDate;
    private Date baseAdditionalFieldsUpdatedDate;
}
