package com.tss.talentsourcingsystem.application.contactInformation.dto;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.contactInformation.enums.ContactInformationType;
import lombok.Data;

import java.util.Date;

@Data
public class ContactInformationDto {
    private final Long id;
    private final ContactInformationType contactInformationType;
    private final Long candidateId;
    private Date baseAdditionalFieldsCreatedDate;
    private Date baseAdditionalFieldsUpdatedDate;
}
