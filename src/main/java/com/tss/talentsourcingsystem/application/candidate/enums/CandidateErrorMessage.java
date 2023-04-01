package com.tss.talentsourcingsystem.application.candidate.enums;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.general.errorMessage.BaseErrorMessage;

public enum CandidateErrorMessage implements BaseErrorMessage {
    CANDIDATE_NOT_FOUND("Candidate Not Found!","Please check the candidate id."),
    PHONE_NUMBER_IS_NOT_VALID("Phone number is not valid!","Please check the phone number."),
    PERSON_TYPE_MUST_BE_CANDIDATE("Person type must be candidate!","Please check the person type.");

    private  final String message;
    private  final String detailMessage;
    CandidateErrorMessage(String message, String detailMessage){
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
