package com.mina.session;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mina.net.Connection;
import com.mina.net.ConnectionCloseListener;

public class SessionManager {
    private static final Log log = LogFactory.getLog(SessionManager.class);
    private static SessionManager instance;
    private Map<String, ClientSession> clientSessions = new ConcurrentHashMap<String, ClientSession>();
    private final AtomicInteger connectionsCounter = new AtomicInteger(0);
    private ClientSessionListener clientSessionListener = new ClientSessionListener();
    private SessionManager() {
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            synchronized (SessionManager.class) {
                instance = new SessionManager();
            }
        }
        return instance;
    }

    public ClientSession createClientSession(Connection conn,String clientId) {
        ClientSession session = new ClientSession(conn,clientId);
        conn.init(session);
        conn.registerCloseListener(clientSessionListener);
        connectionsCounter.incrementAndGet();
        log.debug("ClientSession created.");
        return session;
    }

    public void addSession(ClientSession session) {
        clientSessions.put(session.getClientId(), session);
    }
    
    public void getSessions(String clientId) {
        clientSessions.get(clientId);
    }

    public Collection<ClientSession> getSessions() {
        return clientSessions.values();
    }

    public boolean removeSession(ClientSession session) {
        if (session == null) {
            return false;
        }
        boolean clientRemoved = clientSessions.remove(session.getClientId()) != null;

        if (clientRemoved ) {
            connectionsCounter.decrementAndGet();
            return true;
        }
        return false;
    }

    public void closeAllSessions() {
        try {
            Set<ClientSession> sessions = new HashSet<ClientSession>();
            sessions.addAll(clientSessions.values());

            for (ClientSession session : sessions) {
                try {
                    session.getConnection().systemShutdown();
                } catch (Throwable t) {
                }
            }
        } catch (Exception e) {
        }
    }

    private class ClientSessionListener implements ConnectionCloseListener {

        public void onConnectionClose(Object handback) {
            try {
                ClientSession session = (ClientSession) handback;
                removeSession(session);
            } catch (Exception e) {
                log.error("Could not close socket", e);
            }
        }
    }
}
