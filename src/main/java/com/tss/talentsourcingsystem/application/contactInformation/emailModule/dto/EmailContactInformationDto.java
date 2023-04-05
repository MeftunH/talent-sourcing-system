package com.tss.talentsourcingsystem.application.contactInformation.emailModule.dto;

import com.tss.talentsourcingsystem.application.contactInformation.dto.ContactInformationDto;
import com.tss.talentsourcingsystem.application.contactInformation.emailModule.entity.EmailContactInformation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * A DTO for the {@link EmailContactInformation} entity
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class EmailContactInformationDto extends ContactInformationDto {
    private String emailAddress;
}