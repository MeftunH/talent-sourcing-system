package com.tss.talentsourcingsystem.application.contactInformation.entity;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("EMAIL")
public class EmailContactInformation extends ContactInformation {
    private String emailAdress;
}
