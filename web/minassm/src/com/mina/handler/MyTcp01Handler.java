package com.mina.handler;

import org.apache.http.util.TextUtils;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.google.gson.Gson;
import com.mina.codec.sms.SmsObject;
import com.mina.connectmanage.ConnectType;
import com.mvc.service.impl.SessionManagerServiceImpl;

public class MyTcp01Handler extends IoHandlerAdapter {

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		System.out.println(session.getId()+":sessionCreated");
		SessionManagerServiceImpl.getInstance().sessionCreated(session);
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println(session.getId()+":sessionOpened");

	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println(session.getId()+":sessionClosed");

	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		System.out.println(session.getId()+":sessionIdle"+",status:"+status.toString());
		session.closeOnFlush();
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
		SessionManagerServiceImpl.getInstance().sessionRemoved(session);
		super.inputClosed(session);
	}
	
	private void doConnect(IoSession session, Object message) {
		
	}
	
	
}
