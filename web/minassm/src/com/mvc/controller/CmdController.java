package com.mvc.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mina.codec.sms.SmsObject;
import com.mina.connectmanage.ConnectType;
import com.mina.connectmanage.MessageType;
import com.mina.session.ClientSession;
import com.mina.session.SessionManager;
import com.mvc.model.Event;
import com.mvc.model.ToastMessage;
import com.mvc.service.UserService;

@Controller
@RequestMapping("/cmd")
public class CmdController  extends BaseController{ 
	
	@Resource
	private UserService userService;
    /**
     * 日志记录
     */
    private static Logger log = Logger.getLogger(CmdController.class);
	
	/**
	 * 执行指令
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/excute") 
	public Map<String, Object> excute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();
		String msg ="";
		String action=request.getParameter("action");
		String pkgName=request.getParameter("pkgName");
		Event event =new Event();
		event.setAction(action);
		event.setPkgName(pkgName);
		String message =MessageType.packMessAge(MessageType.CMD, event);
		if(null!=message){
			map.put("code", "00");
			map.put("event", event);
			
			// 群发
			Collection<ClientSession> clientSessions= SessionManager.getInstance().getSessions();
			for(ClientSession clientSession:clientSessions){
				clientSession.deliver(new SmsObject(ConnectType.DATA, "12345", "service", "1", message));
			} 
			
		}else{
			map.put("code", "01");
			map.put("event", event);
		}
		
		return map; 
	}
	
	
	/**
	 * 吐司消息推送
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/toast") 
	public Map<String, Object> toast(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();
		String msg ="";
		String body=request.getParameter("body"); 
		ToastMessage toastMessage =new ToastMessage();
		toastMessage.setToastBodyString(body); 
		String message =MessageType.packMessAge(MessageType.TOAST, toastMessage);
		if(null!=message){
			map.put("code", "00");
			map.put("toastMessage", toastMessage);
			
			// 群发
			Collection<ClientSession> clientSessions= SessionManager.getInstance().getSessions();
			for(ClientSession clientSession:clientSessions){
				clientSession.deliver(new SmsObject(ConnectType.DATA, "12345", "service", "1", message));
			} 
			
		}else{
			map.put("code", "01");
			map.put("event", toastMessage);
		}
		
		return map; 
	}
	
	
	
	
	
	
}
