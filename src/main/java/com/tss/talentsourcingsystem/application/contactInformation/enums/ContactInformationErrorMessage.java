package com.tss.talentsourcingsystem.application.contactInformation.enums;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.general.errorMessage.BaseErrorMessage;

public enum ContactInformationErrorMessage implements BaseErrorMessage {
    EMAIL_AND_PHONE_NUMBER_CAN_NOT_BE_NULL("Email and phone number can not be null","Please check the email and phone number"),
    EMAIL_CAN_NOT_BE_NULL("Email can not be null","Please check the email"),
    PHONE_NUMBER_CAN_NOT_BE_NULL("Phone number can not be null","Please check the phone number"),
    CONTACT_INFORMATION_TYPE_NOT_FOUND("Contact information type not found","Please check the contact information type");

    private  final String message;
    private  final String detailMessage;
    ContactInformationErrorMessage(String message, String detailMessage){
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
