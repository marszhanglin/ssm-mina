package com.mina.handler;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.google.gson.Gson;
import com.mina.codec.sms.SmsObject;
import com.mina.connectmanage.ConnectType;

public class MyTcp01Handler extends IoHandlerAdapter {

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		System.out.println(session.getId()+"服务器端session被创建sessionCreated");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println(session.getId()+"服务器端session被打开sessionOpened");

	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println(session.getId()+"服务器端session被关闭sessionClosed");

	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		System.out.println(session.getId()+"服务器端session进入休眠sessionIdle"+"---"+status.toString());

	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		System.out.println(session.getId()+"服务器端session异常抛出exceptionCaught");
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		System.out.println(session.getId()+"服务器端session接收到消息messageReceived"+new Gson().toJson(message));
		session.write( new SmsObject(ConnectType.DATA,"780965203", "270504808","validatevalue", "hello client 我是服务端"));
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		System.out.println(session.getId()+"服务器端session发送消息messageSent:"+message);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void inputClosed(IoSession session) throws Exception {
		System.out.println(session.getId()+"服务器端inputClosed"); 
		super.inputClosed(session);
	}
	
}
