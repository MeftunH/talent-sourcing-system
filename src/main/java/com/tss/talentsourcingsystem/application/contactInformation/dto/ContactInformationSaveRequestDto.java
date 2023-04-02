package com.tss.talentsourcingsystem.application.contactInformation.dto;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.contactInformation.enums.ContactInformationType;
import jakarta.annotation.Nullable;
import lombok.Data;

public record ContactInformationSaveRequestDto(ContactInformationType contactInformationType, Long candidateId,
                                               @Nullable String phoneNumber, @Nullable String emailAddress) {
}
