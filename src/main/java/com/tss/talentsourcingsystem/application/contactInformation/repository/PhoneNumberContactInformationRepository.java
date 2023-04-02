package com.tss.talentsourcingsystem.application.contactInformation.repository;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.contactInformation.entity.ContactInformation;
import com.tss.talentsourcingsystem.application.contactInformation.entity.PhoneNumberContactInformation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneNumberContactInformationRepository extends ContactInformationRepository<PhoneNumberContactInformation> {
    PhoneNumberContactInformation findByCandidateId(Long candidateId);
    PhoneNumberContactInformation findByPhoneNumber(String phoneNumber);
}
