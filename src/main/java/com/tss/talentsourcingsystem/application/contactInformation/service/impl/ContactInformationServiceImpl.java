package com.tss.talentsourcingsystem.application.contactInformation.service.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.contactInformation.dto.ContactInformationDto;
import com.tss.talentsourcingsystem.application.contactInformation.dto.ContactInformationSaveRequestDto;
import com.tss.talentsourcingsystem.application.contactInformation.entity.ContactInformation;
import com.tss.talentsourcingsystem.application.contactInformation.enums.ContactInformationType;
import com.tss.talentsourcingsystem.application.contactInformation.repository.EmailContactInformationRepository;
import com.tss.talentsourcingsystem.application.contactInformation.repository.PhoneNumberContactInformationRepository;
import com.tss.talentsourcingsystem.application.contactInformation.service.ContactInformationService;
import com.tss.talentsourcingsystem.application.generic.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class ContactInformationServiceImpl extends BaseService<ContactInformation> implements ContactInformationService {
    private final EmailContactInformationRepository emailContactInformationRepository;
    private final PhoneNumberContactInformationRepository phoneNumberContactInformationRepository;

    public ContactInformationServiceImpl(EmailContactInformationRepository emailContactInformationRepository, PhoneNumberContactInformationRepository phoneNumberContactInformationRepository) {
        this.emailContactInformationRepository=emailContactInformationRepository;
        this.phoneNumberContactInformationRepository=phoneNumberContactInformationRepository;
    }

    @Override
    public ContactInformationDto saveContactInformation(ContactInformationSaveRequestDto contactInformationSaveRequestDto) {
        ContactInformation savedContactInformation=null;
        if (contactInformationSaveRequestDto.getContactInformationType().equals(ContactInformationType.EMAIL)) {

            savedContactInformation=emailContactInformationRepository.save(contactInformation);
        } else if (contactInformationSaveRequestDto.getContactInformationType().equals(ContactInformationType.PHONE_NUMBER)) {
            savedContactInformation=phoneNumberContactInformationRepository.save(contactInformation);
        }
        return ContactInformationMapper.INSTANCE.contactInformationToContactInformationDto(savedContactInformation);
    }
}
