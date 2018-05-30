package com.mina;

import com.mina.codec.sms.SmsObject;




public class SmsEventMessage {

    private SmsObject smsObject;

    private String msg;

    public SmsEventMessage(SmsObject smsObject, String msg) {
        this.smsObject = smsObject;
        this.msg = msg;
    }


    public SmsObject getSmsObject() {
        return smsObject;
    }

    public void setSmsObject(SmsObject smsObject) {
        this.smsObject = smsObject;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
