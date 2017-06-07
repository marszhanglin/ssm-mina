package com.mvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.mvc.model.User;
import com.mvc.service.UserService;

@Controller
@RequestMapping("/users")
public class UsersController  extends BaseController{ 
	
	@Resource
	private UserService userService;
    /**
     * 日志记录
     */
    private static Logger log = Logger.getLogger(UsersController.class);
	
	/**
	 * 获取主界面
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/validate") 
	public Map<String, Object> loginValidate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();
		String msg ="";
		log.info(request.getRequestURI());  
		log.info(new Gson().toJson(request.getParameterMap())); 
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(userService.loginValidate(username, password)){
			map.put("success", true);
			msg="登录成功";
		}else{
			map.put("success", false);
			if(userService.isUserExit(username)){
				msg = "用户名密码不匹配";
			}else{
				msg = "用户不存在";
			}
		}
		map.put("msg", msg);
		return map; 
	}
	
	
	/**
	 * 获取用户列表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/list") 
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();
		log.info(request.getRequestURI());  
		log.info(new Gson().toJson(request.getParameterMap())); 
		return new ModelAndView(MINA_BASEPATH+"/users/list"); 
	}
	
	/**
	 * 获取用户列表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/listdata") 
	public Map listdata(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();
		log.info(request.getRequestURI());  
		log.info(new Gson().toJson(request.getParameterMap())); 
		List<User> users= userService.getUsers();
		log.info(new Gson().toJson(users));
		map.put("list",users );
		map.put("pageCount", 102);
		map.put("CurrentPage", 1);
		return map; 
	}
	
	
	
}
