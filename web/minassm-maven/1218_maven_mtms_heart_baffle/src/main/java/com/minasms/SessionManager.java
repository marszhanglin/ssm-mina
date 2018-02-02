package com.minasms;

import java.util.HashMap;

import org.apache.mina.core.session.IoSession;

public class SessionManager {
	private SessionManager() {
	}
	private static class Inner{
		//静态初始化器，由JVM来保证线程安全
		private static final SessionManager  INSTANCE= new SessionManager();
	}
	public SessionManager getInstance(){ 
		return Inner.INSTANCE;
	}
	private HashMap<String, IoSession> sessions =new HashMap<String, IoSession>();
	
	public void addSession(IoSession session){
		
	}
	
	public void removeSession(IoSession session){
		// TODO 删除session并
	}
	
}
