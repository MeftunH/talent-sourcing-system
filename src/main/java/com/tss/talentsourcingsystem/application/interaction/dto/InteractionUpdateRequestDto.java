package com.tss.talentsourcingsystem.application.interaction.dto;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.interaction.enums.CandidateResponseType;
import com.tss.talentsourcingsystem.application.interaction.enums.InteractionType;
import lombok.Data;

import java.io.Serializable;

@Data
public class InteractionUpdateRequestDto implements Serializable{
    private String content;
    private CandidateResponseType isCandidateResponded;
    private InteractionType interactionType;
}
