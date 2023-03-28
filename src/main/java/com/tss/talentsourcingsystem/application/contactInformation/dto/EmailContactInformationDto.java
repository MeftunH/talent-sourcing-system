package com.tss.talentsourcingsystem.application.contactInformation.dto;

import com.tss.talentsourcingsystem.application.contactInformation.entity.EmailContactInformation;
import com.tss.talentsourcingsystem.application.contactInformation.enums.ContactInformationType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * A DTO for the {@link EmailContactInformation} entity
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class EmailContactInformationDto extends ContactInformationDto {
    private String emailAddress;
}