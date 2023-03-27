package com.tss.talentsourcingsystem.application.contactInformation.repository;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactInformationRepository extends JpaRepository<ContactInformationRepository, Long> {
}
