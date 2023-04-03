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
import com.tss.talentsourcingsystem.application.contactInformation.validation.ContactInformationValidationService;
import com.tss.talentsourcingsystem.application.general.errorMessage.GeneralErrorMessage;
import com.tss.talentsourcingsystem.application.general.exception.GeneralBusinessException;
import com.tss.talentsourcingsystem.application.general.exception.ItemNotFoundException;
import com.tss.talentsourcingsystem.application.generic.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Service
@ControllerAdvice
public class ContactInformationServiceImpl extends BaseService<ContactInformation> implements ContactInformationService {
    private final PhoneNumberContactInformationRepository phoneNumberContactInformationRepository;
    private final CandidateService candidateService;
    private final EmailContactInformationRepository emailContactInformationRepository;
    private final CandidateValidationService candidateValidationService;
    private final ContactInformationValidationService contactInformationValidationService;

    public ContactInformationServiceImpl(EmailContactInformationRepository emailContactInformationRepository, PhoneNumberContactInformationRepository phoneNumberContactInformationRepository, CandidateService candidateService, CandidateValidationService candidateValidationService, ContactInformationValidationService contactInformationValidationService) {
        this.emailContactInformationRepository=emailContactInformationRepository;
        this.phoneNumberContactInformationRepository=phoneNumberContactInformationRepository;
        this.candidateService=candidateService;
        this.candidateValidationService=candidateValidationService;
        this.contactInformationValidationService=contactInformationValidationService;
    }

    @Override
    public ContactInformationDto saveContactInformation(ContactInformationSaveRequestDto contactInformationSaveRequestDto) {
        Candidate candidate=candidateService.getCandidateById(contactInformationSaveRequestDto.candidateId());
        candidateValidationService.checkCandidateExists(candidate);
        contactInformationValidationService.checkIfIsValidContactInformationType(contactInformationSaveRequestDto);
        contactInformationValidationService.checkIfIsInformationMatchToInformationType(contactInformationSaveRequestDto);

        if (contactInformationSaveRequestDto.contactInformationType().equals(ContactInformationType.BOTH)) {
            return saveBothContactInformationTypes(contactInformationSaveRequestDto, candidate);
        } else if (contactInformationSaveRequestDto.contactInformationType().equals(ContactInformationType.EMAIL)) {
            return EmailContactInformationMapper.INSTANCE.emailContactInformationToContactInformationDto(saveEmailContactInformation(contactInformationSaveRequestDto, candidate));
        } else if (contactInformationSaveRequestDto.contactInformationType().equals(ContactInformationType.PHONE_NUMBER)) {
            return PhoneNumberContactInformationMapper.INSTANCE.contactInformationToContactInformationDto(savePhoneNumberContactInformation(contactInformationSaveRequestDto, candidate));
        }
        throw new GeneralBusinessException(GeneralErrorMessage.INTERNAL_SERVER_ERROR);
    }

    @Override
    public List<ContactInformationDto> getContactInformationByCandidateId(Long candidateId) {
        List<ContactInformationDto> contactInformationListDto=new ArrayList<>();

        EmailContactInformation emailContactInformation=emailContactInformationRepository.findByCandidateId(candidateId);
        PhoneNumberContactInformation phoneNumberContactInformation=phoneNumberContactInformationRepository.findByCandidateId(candidateId);

        if (emailContactInformation!=null) {
            contactInformationListDto.add(EmailContactInformationMapper.INSTANCE.emailContactInformationToContactInformationDto(emailContactInformation));
        }
        if (phoneNumberContactInformation!=null) {
            contactInformationListDto.add(PhoneNumberContactInformationMapper.INSTANCE.phoneNumberContactInformationToContactInformationDto(phoneNumberContactInformation));
        }
        return contactInformationListDto;
    }

    @Override
    @Transactional
    public ContactInformationDto updateContactInformation(Long candidateId, ContactInformationSaveRequestDto contactInformationSaveRequestDto) {

        contactInformationValidationService.checkIfIsValidContactInformationType(contactInformationSaveRequestDto);
        contactInformationValidationService.checkIfIsInformationMatchToInformationType(contactInformationSaveRequestDto);

        ContactInformationType newType=contactInformationSaveRequestDto.contactInformationType();
        ContactInformationType oldType=getCurrentContactInformationType(candidateId);
        switch (newType) {
            case BOTH -> {
                if (oldType==ContactInformationType.EMAIL) {
                    updateEmailContactInformation(candidateId, contactInformationSaveRequestDto);
                    savePhoneNumberContactInformation(contactInformationSaveRequestDto, candidateService.getCandidateById(candidateId));
                } else if (oldType==ContactInformationType.PHONE_NUMBER) {
                    updatePhoneNumberContactInformation(candidateId, contactInformationSaveRequestDto);
                    saveEmailContactInformation(contactInformationSaveRequestDto, candidateService.getCandidateById(candidateId));
                } else if (oldType==ContactInformationType.BOTH) {
                    return updateBothContactInformationTypes(candidateId, contactInformationSaveRequestDto);
                }
            }
            case EMAIL -> {
                if (oldType==ContactInformationType.EMAIL) {
                    return EmailContactInformationMapper.INSTANCE.emailContactInformationToContactInformationDto(updateEmailContactInformation(candidateId, contactInformationSaveRequestDto));
                } else if (oldType==ContactInformationType.PHONE_NUMBER) {
                    deletePhoneNumberContactInformation(candidateId);
                    return EmailContactInformationMapper.INSTANCE.emailContactInformationToContactInformationDto(saveEmailContactInformation(contactInformationSaveRequestDto, candidateService.getCandidateById(candidateId)));
                } else if (oldType==ContactInformationType.BOTH) {
                    deletePhoneNumberContactInformation(candidateId);
                    return EmailContactInformationMapper.INSTANCE.emailContactInformationToContactInformationDto(updateEmailContactInformation(candidateId, contactInformationSaveRequestDto));
                }
            }
            case PHONE_NUMBER -> {
                if (oldType==ContactInformationType.EMAIL) {
                    deleteEmailContactInformation(candidateId);
                    return PhoneNumberContactInformationMapper.INSTANCE.contactInformationToContactInformationDto(savePhoneNumberContactInformation(contactInformationSaveRequestDto, candidateService.getCandidateById(candidateId)));
                } else if (oldType==ContactInformationType.PHONE_NUMBER) {
                    return PhoneNumberContactInformationMapper.INSTANCE.contactInformationToContactInformationDto(updatePhoneNumberContactInformation(candidateId, contactInformationSaveRequestDto));
                } else if (oldType==ContactInformationType.BOTH) {
                    deleteEmailContactInformation(candidateId);
                    return PhoneNumberContactInformationMapper.INSTANCE.contactInformationToContactInformationDto(updatePhoneNumberContactInformation(candidateId, contactInformationSaveRequestDto));
                }
            }
        }
        return saveContactInformation(contactInformationSaveRequestDto);
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
        phoneNumberContactInformation.setPhoneNumber(contactInformationSaveRequestDto.phoneNumber());
        setAdditionalFields(phoneNumberContactInformation);
        phoneNumberContactInformationRepository.save(phoneNumberContactInformation);

        return phoneNumberContactInformation;
    }

    private EmailContactInformation updateEmailContactInformation(Long candidateId, ContactInformationSaveRequestDto contactInformationSaveRequestDto) {
        EmailContactInformation emailContactInformation=emailContactInformationRepository.findByCandidateId(candidateId);
        if (emailContactInformation==null) {
            throw new ItemNotFoundException(ContactInformationErrorMessage.EMAIL_CONTACT_INFORMATION_NOT_FOUND);
        }
        emailContactInformation.setEmailAddress(contactInformationSaveRequestDto.emailAddress());
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
        contactInformationValidationService.checkEmailContactInformationIsExists(candidate);
        EmailContactInformation emailContactInformation=new EmailContactInformation();
        emailContactInformation.setEmailAddress(contactInformationSaveRequestDto.emailAddress());
        emailContactInformation.setCandidate(candidate);
        setAdditionalFields(emailContactInformation);
        emailContactInformationRepository.save(emailContactInformation);

        return emailContactInformation;
    }

    private PhoneNumberContactInformation savePhoneNumberContactInformation(ContactInformationSaveRequestDto contactInformationSaveRequestDto, Candidate candidate) {
        contactInformationValidationService.checkPhoneNumberContactInformationIsExists(candidate);
        PhoneNumberContactInformation phoneNumberContactInformation=new PhoneNumberContactInformation();
        phoneNumberContactInformation.setPhoneNumber(contactInformationSaveRequestDto.phoneNumber());
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


}
