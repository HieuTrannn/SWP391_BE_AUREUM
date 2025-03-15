package com.example.SkincareProductSales.model;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailDetail {
    String recipient;

    String subject;

    String msgBody;

    String fullName;

    String attachment;

    String buttonValue;

    String link;

    public EmailDetail() {
    }

    public EmailDetail(String recipient, String subject, String msgBody, String fullName, String attachment, String buttonValue, String link) {
        this.recipient = recipient;
        this.subject = subject;
        this.msgBody = msgBody;
        this.fullName = fullName;
        this.attachment = attachment;
        this.buttonValue = buttonValue;
        this.link = link;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getButtonValue() {
        return buttonValue;
    }

    public void setButtonValue(String buttonValue) {
        this.buttonValue = buttonValue;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
