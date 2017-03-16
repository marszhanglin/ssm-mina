package com.np.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.np.dao.InfoMapper;
import com.np.domain.Info;
import com.np.service.InfoService;

@Service("infoService")
public class InfoServiceImpl implements InfoService {
	
	@Resource
	private InfoMapper infoMapper;



	@Override
	public List<Info> selectListInfos() {
		// TODO Auto-generated method stub
		return infoMapper.selectListInfo();
	}

	

}
