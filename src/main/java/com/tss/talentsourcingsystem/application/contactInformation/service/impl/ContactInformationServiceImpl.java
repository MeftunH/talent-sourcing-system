package com.tss.talentsourcingsystem.application.contactInformation.service.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.candidate.entity.Candidate;
import com.tss.talentsourcingsystem.application.candidate.repository.CandidateRepository;
import com.tss.talentsourcingsystem.application.candidate.service.CandidateService;
import com.tss.talentsourcingsystem.application.contactInformation.dto.ContactInformationDto;
import com.tss.talentsourcingsystem.application.contactInformation.dto.ContactInformationSaveRequestDto;
import com.tss.talentsourcingsystem.application.contactInformation.entity.ContactInformation;
import com.tss.talentsourcingsystem.application.contactInformation.entity.EmailContactInformation;
import com.tss.talentsourcingsystem.application.contactInformation.entity.PhoneNumberContactInformation;
import com.tss.talentsourcingsystem.application.contactInformation.enums.ContactInformationType;
import com.tss.talentsourcingsystem.application.contactInformation.mapper.ContactInformationMapper;
import com.tss.talentsourcingsystem.application.contactInformation.mapper.EmailContactInformationMapper;
import com.tss.talentsourcingsystem.application.contactInformation.mapper.PhoneNumberContactInformationMapper;
import com.tss.talentsourcingsystem.application.contactInformation.repository.EmailContactInformationRepository;
import com.tss.talentsourcingsystem.application.contactInformation.repository.PhoneNumberContactInformationRepository;
import com.tss.talentsourcingsystem.application.contactInformation.service.ContactInformationService;
import com.tss.talentsourcingsystem.application.general.errorMessage.GeneralErrorMessage;
import com.tss.talentsourcingsystem.application.general.exception.GeneralBusinessException;
import com.tss.talentsourcingsystem.application.generic.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class ContactInformationServiceImpl extends BaseService<ContactInformation> implements ContactInformationService {
    private final PhoneNumberContactInformationRepository phoneNumberContactInformationRepository;
    private final CandidateService candidateService;
    private final EmailContactInformationRepository emailContactInformationRepository;
    private final CandidateRepository candidateRepository;

    public ContactInformationServiceImpl(EmailContactInformationRepository emailContactInformationRepository, PhoneNumberContactInformationRepository phoneNumberContactInformationRepository, CandidateService candidateService,
                                         CandidateRepository candidateRepository) {
        this.emailContactInformationRepository=emailContactInformationRepository;
        this.phoneNumberContactInformationRepository=phoneNumberContactInformationRepository;
        this.candidateService=candidateService;
        this.candidateRepository=candidateRepository;
    }

    @Override
    public ContactInformationDto saveContactInformation(ContactInformationSaveRequestDto contactInformationSaveRequestDto) {
        Candidate candidate=candidateService.getCandidateById(contactInformationSaveRequestDto.getCandidateId());
        ContactInformation savedContactInformation;
        if (candidate==null) {
            throw new IllegalArgumentException("Candidate not found");
        }
        if (contactInformationSaveRequestDto.getContactInformationType().equals(ContactInformationType.EMAIL)) {
            EmailContactInformation emailContactInformation = EmailContactInformationMapper.INSTANCE.contactInformationSaveRequestDtoToContactInformation(contactInformationSaveRequestDto);
            emailContactInformation.setCandidate(candidate);
            setAdditionalFields(emailContactInformation);
            return EmailContactInformationMapper.INSTANCE.emailContactInformationToContactInformationDto(emailContactInformationRepository.save(emailContactInformation));
        }
        if (contactInformationSaveRequestDto.getContactInformationType().equals(ContactInformationType.PHONE_NUMBER)) {
            PhoneNumberContactInformation phoneNumberContactInformation = PhoneNumberContactInformationMapper.INSTANCE.contactInformationSaveRequestDtoToContactInformation(contactInformationSaveRequestDto);
            phoneNumberContactInformation.setCandidate(candidate);
            setAdditionalFields(phoneNumberContactInformation);
            return PhoneNumberContactInformationMapper.INSTANCE.contactInformationToContactInformationDto(phoneNumberContactInformationRepository.save(phoneNumberContactInformation));
        }
        if (contactInformationSaveRequestDto.getContactInformationType().equals(ContactInformationType.BOTH)) {
            EmailContactInformation emailContactInformation = EmailContactInformationMapper.INSTANCE.contactInformationSaveRequestDtoToContactInformation(contactInformationSaveRequestDto);
            emailContactInformation.setCandidate(candidate);
            setAdditionalFields(emailContactInformation);
            PhoneNumberContactInformation phoneNumberContactInformation = PhoneNumberContactInformationMapper.INSTANCE.contactInformationSaveRequestDtoToContactInformation(contactInformationSaveRequestDto);
            phoneNumberContactInformation.setCandidate(candidate);
            setAdditionalFields(phoneNumberContactInformation);
            savedContactInformation = emailContactInformationRepository.save(emailContactInformation);
            return ContactInformationMapper.INSTANCE.contactInformationToContactInformationDto(savedContactInformation);
        }
        throw new GeneralBusinessException(GeneralErrorMessage.CONTACT_INFORMATION_TYPE_NOT_FOUND);
    }
}
