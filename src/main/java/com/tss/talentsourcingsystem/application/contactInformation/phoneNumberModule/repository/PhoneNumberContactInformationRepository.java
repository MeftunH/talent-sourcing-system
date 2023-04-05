package com.tss.talentsourcingsystem.application.contactInformation.phoneNumberModule.repository;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.contactInformation.phoneNumberModule.entity.PhoneNumberContactInformation;
import com.tss.talentsourcingsystem.application.contactInformation.repository.ContactInformationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberContactInformationRepository extends ContactInformationRepository<PhoneNumberContactInformation> {
    PhoneNumberContactInformation findByCandidateId(Long candidateId);
    PhoneNumberContactInformation findByPhoneNumber(String phoneNumber);
}
