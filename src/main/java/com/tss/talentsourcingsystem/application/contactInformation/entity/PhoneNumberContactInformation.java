package com.tss.talentsourcingsystem.application.contactInformation.entity;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PHONE")
public class PhoneNumberContactInformation extends ContactInformation {
    private String phoneNumber;
}
