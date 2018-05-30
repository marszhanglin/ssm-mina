package com.mvc.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.common.Const;
import com.email.MailUtil;
import com.google.gson.Gson;
import com.mvc.model.User;
import com.mvc.service.UserService;
import com.utils.Md5Utils;

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
		final String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		
		
		if(userService.loginValidate(username, Md5Utils.getMd5String(password))){
			map.put("success", true);
			msg="登录成功";
			
			
			
			// 往session中存放数据
			HttpSession session=request.getSession();
			session.setAttribute(Const.GLOBAL_USER, userService.findUserByName(username));
			session.setMaxInactiveInterval(30*60*1000);//session过期时间 单位秒
			
			try {
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							MailUtil.sendMail(userService.findUserByName(username).getEmail(), "登录成功", "<h1>内容。。。。。。。。。 </h1>");
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (MessagingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}).start();
			} catch (Exception e) { 
				e.printStackTrace();
			}
			
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
	@RequestMapping("/grid") 
	public Map<String,Object> grid(@RequestParam("currentpage") int currentpage,@RequestParam("rows") int rows ,HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info(request.getRequestURI());  
		log.info(new Gson().toJson(request.getParameterMap())); 
		Map<String, Object> map=new HashMap<String, Object>(); 
		List<User> users= userService.findPageByPage(currentpage, rows);  
		int userCount =userService.count();
		map.put("list",users );
		map.put("pagecount", userCount/rows+1);
		map.put("currentpage", currentpage);
		return map; 
	}
	
	
	
	/**
	 * 用户注册
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/register") 
	public Map<String, Object> register(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();
		String msg ="";
		log.info(request.getRequestURI());  
		log.info(new Gson().toJson(request.getParameterMap())); 
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		//String tnc = request.getParameter("tnc");
		
		
		if(userService.isUserExit(username)){
			map.put("success", false);
			msg="用户已存在";
		}else{
			User user =new User();
			user.setName(username);
			user.setPassword(Md5Utils.getMd5String(password));
			user.setEmail(email);
			user.setCreateTime(new Date(System.currentTimeMillis()));
			userService.save(user);
			map.put("success", true);
			msg="注册成功";
		} 
		map.put("msg", msg);
		return map; 
	}
	
	
	
}
