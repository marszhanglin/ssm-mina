package com.mina.codec.sms;

import com.mina.connectmanage.ConnectType;

/**
 * <B style="color:#00f"> </B>
 * <br>
 *
 * @author zhanglin  2017/3/25
 */
public class HeartMessage extends SmsObject {
    public HeartMessage() {
        super(ConnectType.HEART_BEATER, "service", "client", "no", "heartbeat");
    }

}
