package com.tss.talentsourcingsystem.application.contactInformation.emailModule.repository;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.contactInformation.emailModule.entity.EmailContactInformation;
import com.tss.talentsourcingsystem.application.contactInformation.repository.ContactInformationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailContactInformationRepository extends ContactInformationRepository<EmailContactInformation> {
    EmailContactInformation findByCandidateId(Long candidateId);

    EmailContactInformation findByEmailAddress(String email);
}
