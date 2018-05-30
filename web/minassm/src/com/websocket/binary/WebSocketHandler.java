package com.websocket.binary;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.google.gson.Gson;
import com.mina.codec.sms.SmsObject;
import com.mina.connectmanage.ConnectType;




public class WebSocketHandler extends IoHandlerAdapter {
	
	public static final String  CONNECT_ATTR="CONNECT_ATTR"; 
	
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
//		Connection connection=(Connection)session.getAttribute(CONNECT_ATTR);
//		connection.close(); 
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
		
		IoBuffer ioBuffert=(IoBuffer)message;
		System.out.println(ioBuffert.getString(Charset.forName("UTF-8").newDecoder()));
//		SmsObject  smsObject=(SmsObject)message;
//		String typeString=smsObject.getType();
//		if(!TextUtils.isEmpty(typeString)&&ConnectType.HEART_BEATER.equals(typeString)) {
//			System.out.println(session.getId()+":心跳["+smsObject.getBody()+"]");
//			return ;
//		}else if(!TextUtils.isEmpty(typeString)&&ConnectType.CONNECT.equals(typeString)){
//			System.out.println(session.getId()+":连接["+smsObject.getBody()+"]");
//			Connection connection =new Connection(session);
//			ClientSession clientSession=SessionManager.getInstance().createClientSession(connection, smsObject.getReceiver());
//			SessionManager.getInstance().addSession(clientSession);
//			session.setAttribute(CONNECT_ATTR, connection);
//			
//		} else if (!TextUtils.isEmpty(typeString)&&ConnectType.DATA.equals(typeString)) {
//			System.out.println(session.getId()+":数据["+smsObject.getBody()+"]");
//		}else if (!TextUtils.isEmpty(typeString)&&ConnectType.DISCONNECT.equals(typeString)) {
//			System.out.println(session.getId()+":断连["+smsObject.getBody()+"]");
//			Connection connection=(Connection)session.getAttribute(CONNECT_ATTR);
//			connection.close(); 
//		}
		SmsObject smsObject=new SmsObject(ConnectType.DATA, "broswer", "service", "validate", "12312313");
		CharsetEncoder ce=Charset.forName("UTF-8").newEncoder();
		IoBuffer ioBuffer = IoBuffer.allocate(100).setAutoExpand(true);
		ioBuffer.putString("T:"+smsObject.getType()+"\n", ce);
		ioBuffer.putString("S:"+smsObject.getSender()+"\n", ce);
		ioBuffer.putString("R:"+smsObject.getReceiver()+"\n", ce);
		ioBuffer.putString("V:"+smsObject.getValidate()+"\n", ce);
		ioBuffer.putString("L:"+smsObject.getBody().getBytes(Charset.forName("UTF-8")).length+"\n", ce);
		ioBuffer.putString(smsObject.getBody(), ce);
		System.out.println("encode:"+new Gson().toJson(message));
		ioBuffer.flip(); 
		WebSocketCodecPacket wsResponse =WebSocketCodecPacket.buildPacket(ioBuffer);
		
        session.write(wsResponse);
		//短连接   一连上来就关闭 
		//session.closeOnFlush();
	}
	
	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		System.out.println(session.getId()+":messageSent:"+message);
	}

	@Override
	public void inputClosed(IoSession session) throws Exception {
		System.out.println(session.getId()+":inputClosed"); 
		super.inputClosed(session);
	}
	
}
