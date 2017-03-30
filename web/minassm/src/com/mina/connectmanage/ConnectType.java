package com.mina.connectmanage;

/**
 * <B style="color:#00f"> 连接类型</B>
 * <br>目前就这两种 要么心跳 要么数据
 * <br>什么登陆，什么在线、离线、群发（组id）、单发  后面再考虑  估计要来一个新的字段
 *
 * @author zhanglin  ${date}
 */
public class ConnectType {
    public static String CONNECT = "00";
    public static String HEART_BEATER = "01";
    public static String DATA="02";
    public static String DISCONNECT = "03";
}
