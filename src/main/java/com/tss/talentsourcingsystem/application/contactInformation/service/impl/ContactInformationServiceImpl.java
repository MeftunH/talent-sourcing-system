package com.tss.talentsourcingsystem.application.contactInformation.service.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.candidate.entity.Candidate;
import com.tss.talentsourcingsystem.application.candidate.service.CandidateService;
import com.tss.talentsourcingsystem.application.candidate.validation.CandidateValidationService;
import com.tss.talentsourcingsystem.application.contactInformation.dto.ContactInformationDto;
import com.tss.talentsourcingsystem.application.contactInformation.dto.ContactInformationSaveRequestDto;
import com.tss.talentsourcingsystem.application.contactInformation.entity.ContactInformation;
import com.tss.talentsourcingsystem.application.contactInformation.entity.EmailContactInformation;
import com.tss.talentsourcingsystem.application.contactInformation.entity.PhoneNumberContactInformation;
import com.tss.talentsourcingsystem.application.contactInformation.enums.ContactInformationErrorMessage;
import com.tss.talentsourcingsystem.application.contactInformation.enums.ContactInformationType;
import com.tss.talentsourcingsystem.application.contactInformation.mapper.EmailContactInformationMapper;
import com.tss.talentsourcingsystem.application.contactInformation.mapper.PhoneNumberContactInformationMapper;
import com.tss.talentsourcingsystem.application.contactInformation.repository.EmailContactInformationRepository;
import com.tss.talentsourcingsystem.application.contactInformation.repository.PhoneNumberContactInformationRepository;
import com.tss.talentsourcingsystem.application.contactInformation.service.ContactInformationService;
import com.tss.talentsourcingsystem.application.general.errorMessage.GeneralErrorMessage;
import com.tss.talentsourcingsystem.application.general.exception.GeneralBusinessException;
import com.tss.talentsourcingsystem.application.generic.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ContactInformationServiceImpl extends BaseService<ContactInformation> implements ContactInformationService {
    private final PhoneNumberContactInformationRepository phoneNumberContactInformationRepository;
    private final CandidateService candidateService;
    private final EmailContactInformationRepository emailContactInformationRepository;
    private final CandidateValidationService candidateValidationService;

    public ContactInformationServiceImpl(EmailContactInformationRepository emailContactInformationRepository, PhoneNumberContactInformationRepository phoneNumberContactInformationRepository, CandidateService candidateService, CandidateValidationService candidateValidationService) {
        this.emailContactInformationRepository=emailContactInformationRepository;
        this.phoneNumberContactInformationRepository=phoneNumberContactInformationRepository;
        this.candidateService=candidateService;
        this.candidateValidationService=candidateValidationService;
    }

    @Override
    public ContactInformationDto saveContactInformation(ContactInformationSaveRequestDto contactInformationSaveRequestDto) {
        Candidate candidate=candidateService.getCandidateById(contactInformationSaveRequestDto.getCandidateId());
        candidateValidationService.checkCandidateExists(candidate);
        checkIfIsValidContactInformationType(contactInformationSaveRequestDto);
        checkIfIsInformationMatchToInformationType(contactInformationSaveRequestDto);

        if (contactInformationSaveRequestDto.getContactInformationType().equals(ContactInformationType.BOTH)) {
            return saveBothContactInformationTypes(contactInformationSaveRequestDto, candidate);
        } else if (contactInformationSaveRequestDto.getContactInformationType().equals(ContactInformationType.EMAIL)) {
            return EmailContactInformationMapper.INSTANCE.emailContactInformationToContactInformationDto(saveEmailContactInformation(contactInformationSaveRequestDto, candidate));
        } else if (contactInformationSaveRequestDto.getContactInformationType().equals(ContactInformationType.PHONE_NUMBER)) {
            return PhoneNumberContactInformationMapper.INSTANCE.contactInformationToContactInformationDto(savePhoneNumberContactInformation(contactInformationSaveRequestDto, candidate));
        }
        throw new GeneralBusinessException(GeneralErrorMessage.INTERNAL_SERVER_ERROR);
    }

    @Override
    public List<ContactInformationDto> getContactInformationByCandidateId(Long candidateId) {
        List<EmailContactInformation> emailContactInformationList=emailContactInformationRepository.findByCandidateId(candidateId);
        List<PhoneNumberContactInformation> phoneNumberContactInformationList=phoneNumberContactInformationRepository.findByCandidateId(candidateId);

        List<ContactInformationDto> contactInformationListDto=EmailContactInformationMapper.INSTANCE.emailContactInformationListToContactInformationDtoList(emailContactInformationList);
        contactInformationListDto.addAll(PhoneNumberContactInformationMapper.INSTANCE.phoneNumberContactInformationListToContactInformationDtoList(phoneNumberContactInformationList));

        return contactInformationListDto;
    }

    private ContactInformationDto saveBothContactInformationTypes(ContactInformationSaveRequestDto contactInformationSaveRequestDto, Candidate candidate) {
        EmailContactInformation emailContactInformation=saveEmailContactInformation(contactInformationSaveRequestDto, candidate);
        PhoneNumberContactInformation phoneNumberContactInformation=savePhoneNumberContactInformation(contactInformationSaveRequestDto, candidate);

        return buildContactInformationDto(emailContactInformation, phoneNumberContactInformation);
    }

    private EmailContactInformation saveEmailContactInformation(ContactInformationSaveRequestDto contactInformationSaveRequestDto, Candidate candidate) {
        EmailContactInformation emailContactInformation=new EmailContactInformation();
        emailContactInformation.setEmailAddress(contactInformationSaveRequestDto.getEmailAddress());
        emailContactInformation.setCandidate(candidate);
        setAdditionalFields(emailContactInformation);
        emailContactInformationRepository.save(emailContactInformation);

        return emailContactInformation;
    }

    private PhoneNumberContactInformation savePhoneNumberContactInformation(ContactInformationSaveRequestDto contactInformationSaveRequestDto, Candidate candidate) {
        PhoneNumberContactInformation phoneNumberContactInformation=new PhoneNumberContactInformation();
        phoneNumberContactInformation.setPhoneNumber(contactInformationSaveRequestDto.getPhoneNumber());
        phoneNumberContactInformation.setCandidate(candidate);
        setAdditionalFields(phoneNumberContactInformation);
        phoneNumberContactInformationRepository.save(phoneNumberContactInformation);

        return phoneNumberContactInformation;
    }

    private ContactInformationDto buildContactInformationDto(EmailContactInformation emailContactInformation, PhoneNumberContactInformation phoneNumberContactInformation) {
        return ContactInformationDto.builder()
                .contactInformationType(ContactInformationType.BOTH)
                .emailAddress(emailContactInformation.getEmailAddress())
                .id(emailContactInformation.getId())
                .phoneNumber(phoneNumberContactInformation.getPhoneNumber())
                .baseAdditionalFieldsUpdatedDate(phoneNumberContactInformation.getBaseAdditionalFields().getUpdatedDate())
                .baseAdditionalFieldsCreatedDate(phoneNumberContactInformation.getBaseAdditionalFields().getCreatedDate())
                .candidateId(phoneNumberContactInformation.getCandidate().getId())
                .build();
    }

    private void checkIfIsInformationMatchToInformationType(ContactInformationSaveRequestDto contactInformationSaveRequestDto) {
        if (contactInformationSaveRequestDto.getContactInformationType().equals(ContactInformationType.BOTH)) {
            if (contactInformationSaveRequestDto.getEmailAddress()==null||contactInformationSaveRequestDto.getPhoneNumber()==null) {
                throw new IllegalArgumentException(ContactInformationErrorMessage.EMAIL_AND_PHONE_NUMBER_CAN_NOT_BE_NULL.getMessage());
            }
        } else if (contactInformationSaveRequestDto.getContactInformationType().equals(ContactInformationType.EMAIL)) {
            if (contactInformationSaveRequestDto.getEmailAddress()==null) {
                throw new IllegalArgumentException(ContactInformationErrorMessage.EMAIL_CAN_NOT_BE_NULL.getMessage());
            }
        } else if (contactInformationSaveRequestDto.getContactInformationType().equals(ContactInformationType.PHONE_NUMBER)) {
            if (contactInformationSaveRequestDto.getPhoneNumber()==null) {
                throw new IllegalArgumentException(ContactInformationErrorMessage.PHONE_NUMBER_CAN_NOT_BE_NULL.getMessage());
            }
        }

    }

    private void checkIfIsValidContactInformationType(ContactInformationSaveRequestDto contactInformationSaveRequestDto) {
        List<ContactInformationType> contactInformationTypes=Arrays.asList(ContactInformationType.values());
        if (!contactInformationTypes.contains(contactInformationSaveRequestDto.getContactInformationType())) {
            throw new GeneralBusinessException(ContactInformationErrorMessage.CONTACT_INFORMATION_TYPE_NOT_FOUND);
        }
    }
}
