package com.mvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.dao.SmsLogMapper;
import com.mvc.model.SmsLog;
import com.mvc.service.SmsLogService;

@Service("smsLogService")
public class SmsLogServiceImpl implements SmsLogService {
	
	@Autowired
	private SmsLogMapper smsLogMapper;


	@Override
	public void save(SmsLog smsLog) {
		// TODO Auto-generated method stub
		smsLogMapper.insertSelective(smsLog);
	}

	 

}
