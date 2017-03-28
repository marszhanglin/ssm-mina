package com.mina.connectmanage;

/**
 * <B style="color:#00f"> mina消息回调接口</B>
 * <br>
 *
 * @author zhanglin  ${date}
 */
public interface MinaMessageInterface {

    public void messageReceived(Object object);

    /** 系统消息  测试用 */
    public void systemMsg(String object);
}
