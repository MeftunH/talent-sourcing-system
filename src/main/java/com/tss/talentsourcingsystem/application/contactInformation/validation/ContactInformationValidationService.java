package com.tss.talentsourcingsystem.application.contactInformation.validation;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.candidate.entity.Candidate;
import com.tss.talentsourcingsystem.application.contactInformation.dto.ContactInformationSaveRequestDto;

public interface ContactInformationValidationService {
    void validatePhoneNumber(String phoneNumber);
    void validateIsPhoneNoUnique(String phoneNumber);
    void validateEmail(String email);
    void validateIsEmailUnique(String email);

    void checkEmailContactInformationIsExists(Candidate candidate);

    void checkPhoneNumberContactInformationIsExists(Candidate candidate);

    void checkIfIsInformationMatchToInformationType(ContactInformationSaveRequestDto contactInformationSaveRequestDto);

    void checkIfIsValidContactInformationType(ContactInformationSaveRequestDto contactInformationSaveRequestDto);
}
