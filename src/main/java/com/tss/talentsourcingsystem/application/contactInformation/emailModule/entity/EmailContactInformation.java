package com.tss.talentsourcingsystem.application.contactInformation.emailModule.entity;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.contactInformation.entity.ContactInformation;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue(value="EMAIL")
@Getter
@Setter
public class EmailContactInformation extends ContactInformation {
   @Column(name = "email_address")
    private String emailAddress;
}
