package com.mvc.service;

import org.apache.mina.core.session.IoSession;

public interface SessionManagerService {
	public void sessionCreated(IoSession ioSession);

	public void sessionRemoved(IoSession ioSession);
	public void test(String string);
}
