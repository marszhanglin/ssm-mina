package com.mina.connectmanage;

/**
 * <B style="color:#00f"> 客户端连接配置</B>
 * <br> ip 端口 回调  配置
 *
 * @author zhanglin
 */
public class ConnectConfig {
    private String ip;
    private int port;
    private MinaMessageInterface minaMessageInterface;

    public MinaMessageInterface getMinaMessageInterface() {
        return minaMessageInterface;
    }

    public void setMinaMessageInterface(MinaMessageInterface minaMessageInterface) {
        this.minaMessageInterface = minaMessageInterface;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    /**
     *
     * @param ip
     * @param minaMessageInterface 消息回调接口
     * @param port
     */
    public ConnectConfig(String ip, MinaMessageInterface minaMessageInterface, int port) {
        this.ip = ip;
        this.minaMessageInterface = minaMessageInterface;
        this.port = port;
    }
}
