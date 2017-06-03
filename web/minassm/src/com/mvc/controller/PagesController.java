package com.mvc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.mvc.service.SessionManagerService;

@Controller
public class PagesController  extends BaseController{ 
	
	@Resource
	private SessionManagerService sessionManagerService;
	
    /**
     * 日志记录
     */
    private static Logger log = Logger.getLogger(PagesController.class);
    
	/**
	 * 获取主界面
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/index") 
	public String index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug(request.getRequestURI()); 
		return "/index"; 
	} 
	/**
	 * 获取主界面
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/login") 
	public String login(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info(request.getRequestURI()); 
		return MINA_BASEPATH+"/login"; 
	} 
	
	/**
	 * 获取主界面
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/loginValidate") 
	public Map loginValidate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info(request.getRequestURI());  
		log.info(new Gson().toJson(request.getParameterMap())); 
		String username = request.getParameter("username");
		String password = request.getParameter("password"); 
		
		Map map=new HashMap<String, Object>();
		map.put("success", true);
		return map; 
	}
	
	/**
	 * 用户列表界面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mina/userlist")
	public String userlist(HttpServletRequest request,
			HttpServletResponse response) throws Exception { 
		return "/mina/userlist";  
	}
	
	
}
