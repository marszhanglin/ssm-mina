package com.mvc.manager;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.minasms.codec.sms.SmsObject;
import com.mvc.model.SmsLog;
import com.mvc.model.User;
import com.mvc.service.SmsLogService;
import com.mvc.service.UserService;

@Service("userSessionManager")
public class UserSessionManager { 
	
	
	private UserSessionManager() {
	}
	
	@Resource
	private UserService userService;
	
	@Resource
	private SmsLogService smsLogService;
	
	
	private Map<String, User> sessionIdUserMap = new HashMap<String, User>();
	
	private Map<String, IoSession> userNameSessionMap = new HashMap<String, IoSession>();
	
	

	/**
	 * 修改用户在线状态
	 * @param session
	 * @param smsObject
	 */
	public void saveUserStatus(IoSession session,String netOnlineStatus) {
		User user =sessionIdUserMap.get(session.getId());
		if(null!=user){
			user.setNetonlineStatus(netOnlineStatus);
			userService.saveOrUpdate(user);
		}  
		sessionIdUserMap.remove(session.getId());
		userNameSessionMap.remove(session);
		System.out.println(sessionIdUserMap.size());
		System.out.println(userNameSessionMap.size());
		 for (Entry<String, User> entry : sessionIdUserMap.entrySet()) {
			   System.out.println("key= " + entry.getKey() + " and value= " + new Gson().toJson(entry.getValue()));
		}
		 for (Entry<String, IoSession> entry : userNameSessionMap.entrySet()) {
			   System.out.println("key= " + entry.getKey() );
		}
	} 
	
	/**
	 * 保存用户在线状态
	 * @param session
	 * @param smsObject
	 */
	public void saveUserOnlineStatus(IoSession session, SmsObject smsObject) {
		User user =new User();
		user.setName(smsObject.getSender());
		user.setIosessionId(session.getId()+"");
		user.setNetonlineStatus("00");
		userService.saveOrUpdate(user);
		
		sessionIdUserMap.put(session.getId()+"",user);
		userNameSessionMap.put(user.getName(),session);
		System.out.println(sessionIdUserMap.size());
		System.out.println(userNameSessionMap.size());
		 for (Entry<String, User> entry : sessionIdUserMap.entrySet()) {
			   System.out.println("key= " + entry.getKey() + " and value= " + new Gson().toJson(entry.getValue()));
		}
		 for (Entry<String, IoSession> entry : userNameSessionMap.entrySet()) {
			   System.out.println("key= " + entry.getKey() );
		}
	} 
	
	public void saveSmsLog(IoSession session, SmsObject smsObject) {
		SmsLog smsLog =new SmsLog();
		smsLog.setConnectType(smsObject.getType());
		smsLog.setCreateTime(new Date(System.currentTimeMillis()));
		smsLog.setSender(smsObject.getSender());
		smsLog.setReceiver(smsObject.getReceiver());
		smsLog.setNetonlineStatus("1");
		smsLog.setSessionId(session.getId()+"");
		smsLog.setBody(smsObject.getBody());
		smsLogService.save(smsLog);
	}
	
}
