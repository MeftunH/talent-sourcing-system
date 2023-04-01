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
import com.tss.talentsourcingsystem.application.general.exception.IllegalFieldException;
import com.tss.talentsourcingsystem.application.general.exception.ItemNotFoundException;
import com.tss.talentsourcingsystem.application.generic.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@ControllerAdvice
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
        List<ContactInformationDto> contactInformationListDto=new ArrayList<>();

        EmailContactInformation emailContactInformationList=emailContactInformationRepository.findByCandidateId(candidateId);
        PhoneNumberContactInformation phoneNumberContactInformation=phoneNumberContactInformationRepository.findByCandidateId(candidateId);

        if (emailContactInformationList!=null) {
            contactInformationListDto.add(EmailContactInformationMapper.INSTANCE.emailContactInformationToContactInformationDtoList(emailContactInformationList));
        }
        return contactInformationListDto;
    }

    @Override
    @Transactional
    public ContactInformationDto updateContactInformation(Long candidateId, ContactInformationSaveRequestDto contactInformationSaveRequestDto) {

        checkIfIsValidContactInformationType(contactInformationSaveRequestDto);
        checkIfIsInformationMatchToInformationType(contactInformationSaveRequestDto);

        ContactInformationType newType=contactInformationSaveRequestDto.getContactInformationType();
        ContactInformationType oldType=getCurrentContactInformationType(candidateId);
        if (newType.equals(oldType)) {
            if (contactInformationSaveRequestDto.getContactInformationType().equals(ContactInformationType.BOTH)) {
                return updateBothContactInformationTypes(candidateId, contactInformationSaveRequestDto);
            } else if (contactInformationSaveRequestDto.getContactInformationType().equals(ContactInformationType.EMAIL)) {
                return EmailContactInformationMapper.INSTANCE.emailContactInformationToContactInformationDto(updateEmailContactInformation(candidateId, contactInformationSaveRequestDto));
            } else if (contactInformationSaveRequestDto.getContactInformationType().equals(ContactInformationType.PHONE_NUMBER)) {
                return PhoneNumberContactInformationMapper.INSTANCE.contactInformationToContactInformationDto(updatePhoneNumberContactInformation(candidateId, contactInformationSaveRequestDto));
            }
        } else {
            if (oldType.equals(ContactInformationType.EMAIL)&&newType.equals(ContactInformationType.BOTH)) {
                updateEmailContactInformation(candidateId, contactInformationSaveRequestDto);
                savePhoneNumberContactInformation(contactInformationSaveRequestDto, candidateService.getCandidateById(candidateId));
            } else if (oldType.equals(ContactInformationType.PHONE_NUMBER)&&newType.equals(ContactInformationType.BOTH)) {
                updatePhoneNumberContactInformation(candidateId, contactInformationSaveRequestDto);
                saveEmailContactInformation(contactInformationSaveRequestDto, candidateService.getCandidateById(candidateId));
            } else if (oldType.equals(ContactInformationType.BOTH)&&newType.equals(ContactInformationType.EMAIL)) {
                deletePhoneNumberContactInformation(candidateId);
                return EmailContactInformationMapper.INSTANCE.emailContactInformationToContactInformationDto(updateEmailContactInformation(candidateId, contactInformationSaveRequestDto));
            } else if (oldType.equals(ContactInformationType.BOTH)&&newType.equals(ContactInformationType.PHONE_NUMBER)) {
                deleteEmailContactInformation(candidateId);
                return PhoneNumberContactInformationMapper.INSTANCE.contactInformationToContactInformationDto(updatePhoneNumberContactInformation(candidateId, contactInformationSaveRequestDto));
            } else if (oldType.equals(ContactInformationType.PHONE_NUMBER)&&newType.equals(ContactInformationType.EMAIL)) {
                deletePhoneNumberContactInformation(candidateId);
                return EmailContactInformationMapper.INSTANCE.emailContactInformationToContactInformationDto(saveEmailContactInformation(contactInformationSaveRequestDto, candidateService.getCandidateById(candidateId)));
            } else if (oldType.equals(ContactInformationType.EMAIL)&&newType.equals(ContactInformationType.PHONE_NUMBER)) {
                deleteEmailContactInformation(candidateId);
                return PhoneNumberContactInformationMapper.INSTANCE.contactInformationToContactInformationDto(savePhoneNumberContactInformation(contactInformationSaveRequestDto, candidateService.getCandidateById(candidateId)));
            }
            return saveContactInformation(contactInformationSaveRequestDto);
        }
        throw new GeneralBusinessException(GeneralErrorMessage.INTERNAL_SERVER_ERROR);

    }

    @Transactional
    public void deleteBothContactInformationTypes(Long candidateId) {
        deleteEmailContactInformation(candidateId);
        deletePhoneNumberContactInformation(candidateId);
    }

    @Transactional
    public void deletePhoneNumberContactInformation(Long candidateId) {
        phoneNumberContactInformationRepository.deleteByCandidateId(candidateId);
    }

    @Transactional
    public void deleteEmailContactInformation(Long candidateId) {
        emailContactInformationRepository.deleteByCandidateId(candidateId);
    }

    private ContactInformationType getCurrentContactInformationType(Long candidateId) {
        EmailContactInformation emailContactInformation=emailContactInformationRepository.findByCandidateId(candidateId);
        PhoneNumberContactInformation phoneNumberContactInformation=phoneNumberContactInformationRepository.findByCandidateId(candidateId);

        if (emailContactInformation!=null&&phoneNumberContactInformation!=null) {
            return ContactInformationType.BOTH;
        } else if (emailContactInformation!=null) {
            return ContactInformationType.EMAIL;
        } else if (phoneNumberContactInformation!=null) {
            return ContactInformationType.PHONE_NUMBER;
        }
        throw new ItemNotFoundException(ContactInformationErrorMessage.CONTACT_INFORMATION_TYPE_NOT_FOUND);
    }

    private ContactInformationDto updateBothContactInformationTypes(Long candidateId, ContactInformationSaveRequestDto contactInformationSaveRequestDto) {
        EmailContactInformation emailContactInformation=updateEmailContactInformation(candidateId, contactInformationSaveRequestDto);
        PhoneNumberContactInformation phoneNumberContactInformation=updatePhoneNumberContactInformation(candidateId, contactInformationSaveRequestDto);

        return buildContactInformationDto(emailContactInformation, phoneNumberContactInformation);
    }

    private PhoneNumberContactInformation updatePhoneNumberContactInformation(Long candidateId, ContactInformationSaveRequestDto contactInformationSaveRequestDto) {
        PhoneNumberContactInformation phoneNumberContactInformation=phoneNumberContactInformationRepository.findByCandidateId(candidateId);
        if (phoneNumberContactInformation==null) {
            throw new ItemNotFoundException(ContactInformationErrorMessage.PHONE_NUMBER_CONTACT_INFORMATION_NOT_FOUND);
        }
        phoneNumberContactInformation.setPhoneNumber(contactInformationSaveRequestDto.getPhoneNumber());
        setAdditionalFields(phoneNumberContactInformation);
        phoneNumberContactInformationRepository.save(phoneNumberContactInformation);

        return phoneNumberContactInformation;
    }

    private EmailContactInformation updateEmailContactInformation(Long candidateId, ContactInformationSaveRequestDto contactInformationSaveRequestDto) {
        EmailContactInformation emailContactInformation=emailContactInformationRepository.findByCandidateId(candidateId);
        if (emailContactInformation==null) {
            throw new ItemNotFoundException(ContactInformationErrorMessage.EMAIL_CONTACT_INFORMATION_NOT_FOUND);
        }
        emailContactInformation.setEmailAddress(contactInformationSaveRequestDto.getEmailAddress());
        setAdditionalFields(emailContactInformation);
        emailContactInformationRepository.save(emailContactInformation);

        return emailContactInformation;
    }

    private ContactInformationDto saveBothContactInformationTypes(ContactInformationSaveRequestDto contactInformationSaveRequestDto, Candidate candidate) {
        EmailContactInformation emailContactInformation=saveEmailContactInformation(contactInformationSaveRequestDto, candidate);
        PhoneNumberContactInformation phoneNumberContactInformation=savePhoneNumberContactInformation(contactInformationSaveRequestDto, candidate);

        return buildContactInformationDto(emailContactInformation, phoneNumberContactInformation);
    }

    private EmailContactInformation saveEmailContactInformation(ContactInformationSaveRequestDto contactInformationSaveRequestDto, Candidate candidate) {
        checkEmailContactInformationIsExists(candidate);
        EmailContactInformation emailContactInformation=new EmailContactInformation();
        emailContactInformation.setEmailAddress(contactInformationSaveRequestDto.getEmailAddress());
        emailContactInformation.setCandidate(candidate);
        setAdditionalFields(emailContactInformation);
        emailContactInformationRepository.save(emailContactInformation);

        return emailContactInformation;
    }

    private PhoneNumberContactInformation savePhoneNumberContactInformation(ContactInformationSaveRequestDto contactInformationSaveRequestDto, Candidate candidate) {
        checkPhoneNumberContactInformationIsExists(candidate);
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

    private void checkEmailContactInformationIsExists(Candidate candidate) {
        if (emailContactInformationRepository.findByCandidateId(candidate.getId())!=null)
            throw new IllegalFieldException(ContactInformationErrorMessage.EMAIL_CONTACT_INFORMATION_ALREADY_EXISTS);
    }

    private void checkPhoneNumberContactInformationIsExists(Candidate candidate) {
        if (phoneNumberContactInformationRepository.findByCandidateId(candidate.getId())!=null) {
            throw new IllegalFieldException(ContactInformationErrorMessage.PHONE_NUMBER_CONTACT_INFORMATION_ALREADY_EXISTS);
        }
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
