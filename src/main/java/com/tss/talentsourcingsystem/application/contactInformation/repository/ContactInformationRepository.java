package com.tss.talentsourcingsystem.application.contactInformation.repository;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.contactInformation.entity.ContactInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ContactInformationRepository<T extends ContactInformation> extends JpaRepository<T, Long> {
}
