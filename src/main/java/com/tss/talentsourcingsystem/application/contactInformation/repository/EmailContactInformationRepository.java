package com.tss.talentsourcingsystem.application.contactInformation.repository;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.contactInformation.entity.ContactInformation;
import com.tss.talentsourcingsystem.application.contactInformation.entity.EmailContactInformation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailContactInformationRepository extends ContactInformationRepository<EmailContactInformation>{
    List<EmailContactInformation> findByCandidateId(Long candidateId);
}
