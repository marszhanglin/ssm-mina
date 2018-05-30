package com.minasms.handler;

import org.apache.http.util.TextUtils;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.minasms.codec.sms.SmsObject;
import com.minasms.connectmanage.ConnectType;
import com.mvc.manager.UserSessionManager;

public class MyTcp01Handler extends IoHandlerAdapter {
	
	@Autowired
	private UserSessionManager userSessionManager;
	
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		System.out.println(session.getId()+":sessionCreated");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println(session.getId()+":sessionOpened");

	}
	
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println(session.getId()+":sessionClosed"); 
		userSessionManager.saveUserStatus(session,"02");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		System.out.println(session.getId()+":sessionIdle"+",status:"+status.toString());
		session.closeOnFlush();
		userSessionManager.saveUserStatus(session,"01");
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		System.out.println(session.getId()+":exceptionCaught:"+cause.getMessage());
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		System.out.println(new Gson().toJson(message));
		SmsObject  smsObject=(SmsObject)message;
		String typeString=smsObject.getType();
		if(!TextUtils.isEmpty(typeString)&&ConnectType.CONNECT.equals(typeString)){
			System.out.println(session.getId()+":连接["+smsObject.getBody()+"]");
			doConnect( session,  message);
		}else if (!TextUtils.isEmpty(typeString)&&ConnectType.HEART_BEATER.equals(typeString)) {
			System.out.println(session.getId()+":心跳["+smsObject.getBody()+"]");
		}else if (!TextUtils.isEmpty(typeString)&&ConnectType.DATA.equals(typeString)) {
			System.out.println(session.getId()+":数据["+smsObject.getBody()+"]");
		}else if (!TextUtils.isEmpty(typeString)&&ConnectType.DISCONNECT.equals(typeString)) {
			System.out.println(session.getId()+":断连["+smsObject.getBody()+"]");
		}

		session.write( new SmsObject(ConnectType.DATA,"no", "no","no", "i am service , "+smsObject.getBody()));
		
		
		userSessionManager.saveSmsLog(session, smsObject);
		
		userSessionManager.saveUserOnlineStatus(session, smsObject);
		
		
		
		//短连接   一连上来就关闭 
		//session.closeOnFlush();
	}
	
	
	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		System.out.println(session.getId()+":messageSent:"+message);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void inputClosed(IoSession session) throws Exception {
		System.out.println(session.getId()+":inputClosed"); 
		super.inputClosed(session);
	}
	
	private void doConnect(IoSession session, Object message) {
		
	}
	
	
}
