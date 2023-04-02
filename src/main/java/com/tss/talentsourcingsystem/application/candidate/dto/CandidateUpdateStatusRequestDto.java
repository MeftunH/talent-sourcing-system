package com.tss.talentsourcingsystem.application.candidate.dto;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.candidate.enums.CandidateStatus;

import java.io.Serializable;

public record CandidateUpdateStatusRequestDto(CandidateStatus candidateStatus) implements Serializable {
}
