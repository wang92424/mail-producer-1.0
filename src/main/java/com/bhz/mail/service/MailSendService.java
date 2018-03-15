package com.bhz.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.bhz.mail.entity.MailSend;
import com.bhz.mail.mapper.MailSend1Mapper;
import com.bhz.mail.mapper.MailSend2Mapper;

@Service
public class MailSendService {

	@Autowired
	private MailSend1Mapper mailSend1Mapper;
	@Autowired
	private MailSend2Mapper mailSend2Mapper;
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate ;
	
	public void insert(MailSend mailSend) {
		// TODO Auto-generated method stub
		if(mailSend.getSendId().hashCode()%2==0) {
			mailSend1Mapper.insert(mailSend);
		}else {
			mailSend2Mapper.insert(mailSend);
		}

	}
	
	public void sendRedis(MailSend mailSend) {
		// TODO Auto-generated method stub
		ListOperations<String, String> listOperations = redisTemplate.opsForList();
		
	}
	
}
