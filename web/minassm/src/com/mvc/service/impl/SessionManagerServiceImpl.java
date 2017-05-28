package com.mvc.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Service;

import com.mvc.dao.UserMapper;
import com.mvc.service.SessionManagerService;

@Service("sessionManagerService")
public class SessionManagerServiceImpl implements SessionManagerService {
	
	private UserMapper userMapper;
	
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

	@Override
	public void test(String string) {
		System.out.println(string+"------");
	}
	
	
	
	

}
