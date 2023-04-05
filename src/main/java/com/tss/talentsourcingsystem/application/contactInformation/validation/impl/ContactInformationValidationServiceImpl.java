package com.tss.talentsourcingsystem.application.contactInformation.validation.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.candidate.entity.Candidate;
import com.tss.talentsourcingsystem.application.candidate.enums.CandidateErrorMessage;
import com.tss.talentsourcingsystem.application.contactInformation.dto.ContactInformationSaveRequestDto;
import com.tss.talentsourcingsystem.application.contactInformation.enums.ContactInformationErrorMessage;
import com.tss.talentsourcingsystem.application.contactInformation.enums.ContactInformationType;
import com.tss.talentsourcingsystem.application.contactInformation.emailModule.repository.EmailContactInformationRepository;
import com.tss.talentsourcingsystem.application.contactInformation.phoneNumberModule.repository.PhoneNumberContactInformationRepository;
import com.tss.talentsourcingsystem.application.contactInformation.validation.ContactInformationValidationService;
import com.tss.talentsourcingsystem.application.general.exception.GeneralBusinessException;
import com.tss.talentsourcingsystem.application.general.exception.IllegalFieldException;
import com.tss.talentsourcingsystem.application.general.exception.InformationMismatchException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@ControllerAdvice
public class ContactInformationValidationServiceImpl implements ContactInformationValidationService {
    private final EmailContactInformationRepository emailContactInformationRepository;
    private final PhoneNumberContactInformationRepository phoneNumberContactInformationRepository;

    public ContactInformationValidationServiceImpl(EmailContactInformationRepository emailContactInformationRepository, PhoneNumberContactInformationRepository phoneNumberContactInformationRepository) {
        this.emailContactInformationRepository=emailContactInformationRepository;
        this.phoneNumberContactInformationRepository=phoneNumberContactInformationRepository;
    }
    @Override
    public void checkEmailContactInformationIsExists(Candidate candidate) {
        if (emailContactInformationRepository.findByCandidateId(candidate.getId())!=null)
            throw new IllegalFieldException(ContactInformationErrorMessage.EMAIL_CONTACT_INFORMATION_ALREADY_EXISTS);
    }
    @Override
    public void checkPhoneNumberContactInformationIsExists(Candidate candidate) {
        if (phoneNumberContactInformationRepository.findByCandidateId(candidate.getId())!=null) {
            throw new IllegalFieldException(ContactInformationErrorMessage.PHONE_NUMBER_CONTACT_INFORMATION_ALREADY_EXISTS);
        }
    }
    @Override
    public void checkIfIsInformationMatchToInformationType(ContactInformationSaveRequestDto contactInformationSaveRequestDto) {
        if (contactInformationSaveRequestDto.contactInformationType().equals(ContactInformationType.BOTH)) {
            if (contactInformationSaveRequestDto.emailAddress()==null||contactInformationSaveRequestDto.phoneNumber()==null) {
                throw new IllegalArgumentException(ContactInformationErrorMessage.EMAIL_AND_PHONE_NUMBER_CAN_NOT_BE_NULL.getMessage());
            }
        } else if (contactInformationSaveRequestDto.contactInformationType().equals(ContactInformationType.EMAIL)) {
            if (contactInformationSaveRequestDto.emailAddress()==null) {
                throw new IllegalArgumentException(ContactInformationErrorMessage.EMAIL_CAN_NOT_BE_NULL.getMessage());
            }
        } else if (contactInformationSaveRequestDto.contactInformationType().equals(ContactInformationType.PHONE_NUMBER)) {
            if (contactInformationSaveRequestDto.phoneNumber()==null) {
                throw new IllegalArgumentException(ContactInformationErrorMessage.PHONE_NUMBER_CAN_NOT_BE_NULL.getMessage());
            }
        }

    }
    @Override
    public void checkIfIsValidContactInformationType(ContactInformationSaveRequestDto contactInformationSaveRequestDto) {
        List<ContactInformationType> contactInformationTypes=Arrays.asList(ContactInformationType.values());
        if (!contactInformationTypes.contains(contactInformationSaveRequestDto.contactInformationType())) {
            throw new GeneralBusinessException(ContactInformationErrorMessage.CONTACT_INFORMATION_TYPE_NOT_FOUND);
        }
    }

    @Override
    public void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.length()!=10) {
            throw new InformationMismatchException(ContactInformationErrorMessage.PHONE_NUMBER_IS_NOT_VALID);
        }
    }

    @Override
    public void validateIsPhoneNoUnique(String phoneNumber) {
        validateFieldNotNull(phoneNumber);
        if (phoneNumberContactInformationRepository.findByPhoneNumber(phoneNumber)!=null) {
            throw new InformationMismatchException(ContactInformationErrorMessage.PHONE_NUMBER_IS_NOT_UNIQUE);
        }
    }

    private void validateFieldNotNull(String field) {
        if (Objects.equals(field, "")||field==null||field.isBlank()) {
            throw new IllegalFieldException(CandidateErrorMessage.FIELD_CANNOT_BE_NULL);
        }
    }

    @Override
    public void validateEmail(String email) {
        String emailPattern = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,})+$";

        // Create a Pattern object from the pattern string
        Pattern pattern = Pattern.compile(emailPattern);

        // Create a Matcher object from the email and the pattern
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new IllegalFieldException(ContactInformationErrorMessage.EMAIL_IS_NOT_VALID);
                    }
    }

    @Override
    public void validateIsEmailUnique(String email) {
        validateFieldNotNull(email);
        if (emailContactInformationRepository.findByEmailAddress(email)!=null) {
            throw new IllegalFieldException(ContactInformationErrorMessage.EMAIL_IS_NOT_UNIQUE);
        }
    }


}
