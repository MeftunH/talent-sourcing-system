package com.tss.talentsourcingsystem.application.contactInformation.entity;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.candidate.entity.Candidate;
import com.tss.talentsourcingsystem.application.contactInformation.enums.ContactInformationType;
import com.tss.talentsourcingsystem.application.generic.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "contact_information_type", length = 30, discriminatorType = DiscriminatorType.STRING)
public abstract class ContactInformation extends BaseEntity {
    @Id
    @SequenceGenerator(name = "contactInfoSeq", sequenceName = "contact_info_id_sec")
    @GeneratedValue(generator="contactInfoSeq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidate candidate;

}