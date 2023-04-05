package com.tss.talentsourcingsystem.application.contactInformation.phoneNumberModule.entity;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.contactInformation.entity.ContactInformation;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue(value="PHONE_NUMBER")
@Getter
@Setter
public class PhoneNumberContactInformation extends ContactInformation {
   @Column(name = "phone_number")
    private String phoneNumber;
}
