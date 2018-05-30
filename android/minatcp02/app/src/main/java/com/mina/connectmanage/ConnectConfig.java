package com.mina.connectmanage;

import mina.marszhang.minatcp02.common.Const;

/**
 * <B style="color:#00f"> 客户端连接配置</B>
 * <br> ip 端口 回调  配置
 *
 * @author zhanglin
 */
public class ConnectConfig {
    private String ip;
    private int port;



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
     * @param port
     */
    public ConnectConfig(String ip,  int port) {
        this.ip = ip;
        this.port = port;
    }

}
