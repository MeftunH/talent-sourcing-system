package com.tss.talentsourcingsystem.application.person.entity;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.generic.entity.BaseEntity;
import com.tss.talentsourcingsystem.application.person.enums.PersonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "person")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person extends BaseEntity {
    @Id
    @SequenceGenerator(name = "personSeq", sequenceName = "person_id_seq")
    @GeneratedValue(generator="personSeq")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;
    @Enumerated(EnumType.STRING)
    @Column(name = "person_type", nullable = false, length = 30)
    private PersonType personType;
}