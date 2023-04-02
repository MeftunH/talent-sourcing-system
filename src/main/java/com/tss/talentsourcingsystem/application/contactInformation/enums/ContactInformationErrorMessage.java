package com.tss.talentsourcingsystem.application.contactInformation.enums;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.general.errorMessage.BaseErrorMessage;

public enum ContactInformationErrorMessage implements BaseErrorMessage {
    EMAIL_AND_PHONE_NUMBER_CAN_NOT_BE_NULL("Email and phone number can not be null","Please check the email and phone number"),
    EMAIL_CAN_NOT_BE_NULL("Email can not be null","Please check the email"),
    PHONE_NUMBER_CAN_NOT_BE_NULL("Phone number can not be null","Please check the phone number"),
    CONTACT_INFORMATION_TYPE_NOT_FOUND("Contact information type not found","Please check the contact information type"),
    EMAIL_CONTACT_INFORMATION_ALREADY_EXISTS("Email contact information already exists","Please check the email contact information"),
    PHONE_NUMBER_CONTACT_INFORMATION_ALREADY_EXISTS("Phone number contact information already exists","Please check the phone number contact information"),
    EMAIL_CONTACT_INFORMATION_NOT_FOUND("Email contact information not found","Please check the email contact information"),
    PHONE_NUMBER_CONTACT_INFORMATION_NOT_FOUND("Phone number contact information not found","Please check the phone number contact information"),
    PHONE_NUMBER_IS_NOT_VALID("Phone number is not valid","Please re-check the phone number"),
    PHONE_NUMBER_IS_NOT_UNIQUE( "Phone number is not unique","Please check the phone number"),
    EMAIL_IS_NOT_VALID("Email is not valid","Please re-check the email"),
    EMAIL_IS_NOT_UNIQUE("Email is not unique","Please check the email");

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
