package com.tss.talentsourcingsystem.application.contactInformation.repository;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.contactInformation.entity.ContactInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface ContactInformationRepository extends JpaRepository<ContactInformation, Long> {
}
