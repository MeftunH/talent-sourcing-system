package com.tss.talentsourcingsystem.application.person.dto;

import com.tss.talentsourcingsystem.application.person.entity.Person;
import com.tss.talentsourcingsystem.application.person.enums.PersonType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link Person} entity
 */
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class PersonDto implements Serializable {
    private Date baseAdditionalFieldsCreatedDate;
    private Date baseAdditionalFieldsUpdatedDate;
    private Long id;
    private String name;
    private String surname;
    private PersonType personType;
}