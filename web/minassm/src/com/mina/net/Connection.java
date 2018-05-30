package com.mina.net;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.session.IoSession;

import com.mina.codec.sms.SmsObject;
import com.mina.connectmanage.ConnectType;
import com.mina.session.ClientSession;



/**
 * 
 * <B style="color:#00f"> </B>
 * <br>
 * @author zhanglin  2018-5-11
 */
public class Connection {

    private static final Log log = LogFactory.getLog(Connection.class);

    private IoSession ioSession;

    private ClientSession session;

    private ConnectionCloseListener closeListener; 

    private boolean closed;

    public Connection(IoSession ioSession) {
    	System.out.println("Connection.Connection");
        this.ioSession = ioSession;
        this.closed = false;
    }

    public void close() {
    	System.out.println("Connection.close");
        boolean closedSuccessfully = false;
        synchronized (this) {
            if (!isClosed()) {
                try {
                    deliver(new SmsObject(ConnectType.DISCONNECT, "", "", "", "close"));
                } catch (Exception e) {
                    // Ignore
                }
                if (session != null) {
                    session.setStatus(ClientSession.STATUS_CLOSED);
                }
                ioSession.close(false);
                closed = true;
                closedSuccessfully = true;
            }
        }
        if (closedSuccessfully) {
            notifyCloseListeners();
        }
    }
    
    public void systemShutdown() {
    	System.out.println("Connection.systemShutdown");
        deliver(new SmsObject(ConnectType.DISCONNECT, "", "", "", "systemShutdown"));
        close();
    }

    public void init(ClientSession session) {
    	System.out.println("Connection.init");
        this.session = session;
    }

    public boolean isClosed() {
    	System.out.println("Connection.isClosed");
        if (session == null) {
            return closed;
        }
        return session.getStatus() == ClientSession.STATUS_CLOSED;
    }


    public void registerCloseListener(ConnectionCloseListener listener) {
    	System.out.println("Connection.registerCloseListener");
        if (closeListener != null) {
            throw new IllegalStateException("Close listener already configured");
        }
        if (isClosed()) {
            listener.onConnectionClose(session);
        } else {
            closeListener = listener;
        }
    }

    public void unregisterCloseListener(ConnectionCloseListener listener) {
    	System.out.println("Connection.unregisterCloseListener");
        if (closeListener == listener) {
            closeListener = null;
        }
    }

    private void notifyCloseListeners() {
    	System.out.println("Connection.notifyCloseListeners");
        if (closeListener != null) {
            try {
                closeListener.onConnectionClose(session);
            } catch (Exception e) {
                log.error("Error notifying listener: " + closeListener, e);
            }
        }
    }

    
    
    /**
     * 发送短信消息包
     * @param smsObject
     */
    public void deliver(SmsObject smsObject) {
        if (!isClosed()) {
            boolean errorDelivering = false;
            try { 
                ioSession.write(smsObject);
            } catch (Exception e) {
                log.debug("Connection: Error delivering packet" + "\n"
                        + this.toString(), e);
                errorDelivering = true;
            }
            if (errorDelivering) {
                close();
            } else {
                session.incrementServerPacketCount();
            }
        }
    }

    public String getHostAddress() throws UnknownHostException {
        return ((InetSocketAddress) ioSession.getRemoteAddress()).getAddress()
                .getHostAddress();
    }


    public String getHostName() throws UnknownHostException {
        return ((InetSocketAddress) ioSession.getRemoteAddress()).getAddress()
                .getHostName();
    }

}
