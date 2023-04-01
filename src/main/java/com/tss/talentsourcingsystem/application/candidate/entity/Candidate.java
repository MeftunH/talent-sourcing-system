package com.tss.talentsourcingsystem.application.candidate.entity;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.candidate.enums.CandidateStatus;
import com.tss.talentsourcingsystem.application.contactInformation.entity.ContactInformation;
import com.tss.talentsourcingsystem.application.person.entity.Person;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "candidate")
@PrimaryKeyJoinColumn(name = "candidate_id")
public class Candidate extends Person {
    @OneToMany(mappedBy = "candidate",fetch=FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ContactInformation> contactInformation=new HashSet<>();

    private CandidateStatus candidateStatus;
}
