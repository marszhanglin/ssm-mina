package com.np.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.mina.core.session.IoSession;

import com.np.service.SessionManagerService;

public class SessionManagerServiceImpl implements SessionManagerService {

	private static SessionManagerServiceImpl instance;
	
	public static SessionManagerServiceImpl getInstance() {
		if (instance == null) {
	        synchronized (SessionManagerServiceImpl.class) {
	            instance = new SessionManagerServiceImpl();
	        }
	    }
	    return instance;
	}
	
	private Map<String, IoSession> sessionMap= new HashMap<String, IoSession>();
	
	@Override
	public void sessionCreated(IoSession ioSession) {
		sessionMap.put(ioSession.getId()+"", ioSession);
	}

	@Override
	public void sessionRemoved(IoSession ioSession) {
		sessionMap.remove(ioSession.getId()+"");
	}

}
