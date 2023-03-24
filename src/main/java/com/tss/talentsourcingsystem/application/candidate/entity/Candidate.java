package com.tss.talentsourcingsystem.application.candidate.entity;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "candidates")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
