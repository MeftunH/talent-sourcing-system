package com.tss.talentsourcingsystem.application.interaction.enums;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.general.errorMessage.BaseErrorMessage;

public enum InteractionErrorMessage implements BaseErrorMessage {
    INTERACTION_FIELDS_MUST_BE_NOT_NULL("Interaction fields must be not null", "Please check the interaction fields"),
    CANDIDATE_IS_NOT_VALID("Candidate is not valid", "Please check the candidate id" );
    private  final String message;
    private  final String detailMessage;
    InteractionErrorMessage(String message, String detailMessage){
        this.message = message;
        this.detailMessage = detailMessage;
    }
    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getDetailMessage() {
        return detailMessage;
    }
}
