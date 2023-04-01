package com.tss.talentsourcingsystem.application.contactInformation.validation;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

public interface ContactInformationValidationService {
    void validatePhoneNumber(String phoneNumber);
    void validateIsPhoneNoUnique(String phoneNumber);
    void validateEmail(String email);
    void validateIsEmailUnique(String email);
}
