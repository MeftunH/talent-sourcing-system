package com.tss.talentsourcingsystem.application.candidate.dto;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.candidate.enums.CandidateStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
public class CandidateUpdateRequestDto implements Serializable {
    private String name;
    private String surname;
    private CandidateStatus candidateStatus;
}
