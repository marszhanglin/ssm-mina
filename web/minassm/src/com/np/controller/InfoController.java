package com.np.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.np.domain.Info;
import com.np.service.InfoService;
import com.np.utils.ResponseUtil;

@Controller
public class InfoController extends BaseController {
	@Resource
	private InfoService infoService;
	/**
	 * 获取主界面
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return "/index";
		
	}

	
	@RequestMapping("/infolist")
	public void infolist(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("infolist");
		List<Info> list = infoService.selectListInfos();
		JSONArray jsonArray = JSONArray.fromObject(list);
		
		ResponseUtil.write(response, jsonArray);
		
	}
	
}
