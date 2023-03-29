package com.tss.talentsourcingsystem.application.contactInformation.service.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.contactInformation.dto.ContactInformationDto;
import com.tss.talentsourcingsystem.application.contactInformation.dto.ContactInformationSaveRequestDto;
import com.tss.talentsourcingsystem.application.contactInformation.entity.ContactInformation;
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
            savedContactInformation=emailContactInformationRepository.
                    save(EmailContactInformationMapper.INSTANCE.contactInformationSaveRequestDtoToContactInformation(contactInformationSaveRequestDto));
            return EmailContactInformationMapper.INSTANCE.contactInformationToContactInformationDto(savedContactInformation);
        } else if (contactInformationSaveRequestDto.getContactInformationType().equals(ContactInformationType.PHONE_NUMBER)) {
            savedContactInformation=phoneNumberContactInformationRepository.
                    save(PhoneNumberContactInformationMapper.INSTANCE.contactInformationSaveRequestDtoToContactInformation(contactInformationSaveRequestDto));
            return PhoneNumberContactInformationMapper.INSTANCE.contactInformationToContactInformationDto(savedContactInformation);
        }
        throw new GeneralBusinessException(GeneralErrorMessage.CONTACT_INFORMATION_TYPE_NOT_FOUND);
    }
}
