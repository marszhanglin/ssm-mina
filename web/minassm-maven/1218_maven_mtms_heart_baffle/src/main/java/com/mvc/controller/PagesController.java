package com.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PagesController  extends BaseController{ 
	
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
		return "/source/index"; 
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
