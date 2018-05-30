package com.mvc.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mina.session.ClientSession;
import com.mina.session.SessionManager;

@Controller
@RequestMapping("/session")
public class SessionController  extends BaseController{ 
	
    /**
     * 日志记录
     */
    private static Logger log = Logger.getLogger(SessionController.class);
	
	/**
	 * 执行指令
	 * Accept:application/json, text/javascript, *\/*; q=0.01
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/list") 
	public Map<String, Object> sessions(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();
		String msg ="";
		Collection<ClientSession> clientSessions=SessionManager.getInstance().getSessions();
		map.put("size", clientSessions.size());
		StringBuilder builder =new StringBuilder();
		for(ClientSession clientSession: clientSessions ){
			builder.append(clientSession.getClientId()+",");
		}
		map.put("clients", builder.toString());
		map.put("msg", msg);
		return map; 
	}
}
