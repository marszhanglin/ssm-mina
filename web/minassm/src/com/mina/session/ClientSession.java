package com.mina.session;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.mina.codec.sms.SmsObject;
import com.mina.net.Connection;



/**
 * 
 * <B style="color:#00f">  </B>
 * <br>
 * @author zhanglin  2018-5-11
 */
public class ClientSession {


    public static final int STATUS_CLOSED = 0;

    public static final int STATUS_CONNECTED = 1;

    protected Connection connection;

    protected SessionManager sessionManager;

    private int status = STATUS_CONNECTED;

    private long startDate = System.currentTimeMillis();

    private long lastActiveDate;

    private long clientPacketCount = 0;

    private long serverPacketCount = 0;

    private final Map<String, Object> sessionData = new ConcurrentHashMap<String, Object>();

    private String clientId="";
    
    public ClientSession(Connection conn,String clientId) {
    	System.out.println("ClientSession.ClientSession");
        this.connection = conn;
        this.clientId = clientId;
        this.sessionManager = SessionManager.getInstance();
    }
    
    public String getClientId() {
    	System.out.println("ClientSession.getClientId");
		return clientId;
	}

	/**
     * 获取连接，当要关闭连接时需要连接对象
     * @return
     */
    public Connection getConnection() {
    	System.out.println("ClientSession.getConnection");
        return connection;
    }

    public int getStatus() {
    	System.out.println("ClientSession.getStatus");
        return status;
    }

    public void setStatus(int status) {
    	System.out.println("ClientSession.setStatus");
        this.status = status;
    }

    public Date getCreationDate() {
    	System.out.println("ClientSession.getCreationDate");
        return new Date(startDate);
    }

    public Date getLastActiveDate() {
    	System.out.println("ClientSession.getLastActiveDate");
        return new Date(lastActiveDate);
    }

    public void incrementClientPacketCount() {
    	System.out.println("ClientSession.incrementClientPacketCount");
        clientPacketCount++;
        lastActiveDate = System.currentTimeMillis();
    }

    public void incrementServerPacketCount() {
    	System.out.println("ClientSession.incrementServerPacketCount");
        serverPacketCount++;
        lastActiveDate = System.currentTimeMillis();
    }

    public long getNumClientPackets() {
    	System.out.println("ClientSession.getNumClientPackets");
        return clientPacketCount;
    }

    public long getNumServerPackets() {
    	System.out.println("ClientSession.getNumServerPackets");
        return serverPacketCount;
    }

    public void setSessionData(String key, Object value) {
    	System.out.println("ClientSession.setSessionData");
        synchronized (sessionData) {
            sessionData.put(key, value);
        }
    }

    public Object getSessionData(String key) {
    	System.out.println("ClientSession.getSessionData");
        synchronized (sessionData) {
            return sessionData.get(key);
        }
    }

    public void removeSessionData(String key) {
    	System.out.println("ClientSession.removeSessionData");
        synchronized (sessionData) {
            sessionData.remove(key);
        }
    }

    public void deliver(SmsObject smsObject ) {
    	System.out.println("ClientSession.deliver");
        if (connection != null && !connection.isClosed()) {
            connection.deliver(smsObject);//发送响应数据包
        }
    }

    public void close() {
    	System.out.println("ClientSession.close");
        if (connection != null) {
            connection.close();
        }
    }

    public boolean isClosed() {
    	System.out.println("ClientSession.isClosed");
        return connection.isClosed();
    }

    public String getHostAddress() throws UnknownHostException {
    	System.out.println("ClientSession.getHostAddress");
        return connection.getHostAddress();
    }

    public String getHostName() throws UnknownHostException {
    	System.out.println("ClientSession.getHostName");
        return connection.getHostName();
    }

    @Override
    public String toString() {
    	System.out.println("ClientSession.toString");
        return super.toString() + " status: " + status + " address: ";
    }

}
