package com.bhz.mail.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.bhz.mail.config.database.ReadOnlyConnection;
import com.bhz.mail.entity.MailSend;
import com.bhz.mail.enumeration.MailStatus;
import com.bhz.mail.enumeration.RedisPriorityQueue;
import com.bhz.mail.mapper.MailSend1Mapper;
import com.bhz.mail.mapper.MailSend2Mapper;
import com.bhz.mail.utils.FastJsonConvertUtil;

@Service
public class MailSendService {

	@Autowired
	private MailSend1Mapper mailSend1Mapper;
	@Autowired
	private MailSend2Mapper mailSend2Mapper;
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate ;
	
	private final Logger LOGGER = LoggerFactory.getLogger(MailSendService.class);
	
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
		ListOperations<String, String> opsForList = redisTemplate.opsForList();
		Long priority = mailSend.getSendPriority();
		Long ret = 0L;
		Long size = 0L;
		if(priority<4L) {
			ret = opsForList.rightPush(RedisPriorityQueue.DEFER_QUEUE.getCode(), FastJsonConvertUtil.convertObjectToJSON(mailSend));
			size = opsForList.size(RedisPriorityQueue.DEFER_QUEUE.getCode());
		}else if(priority<7L) {
			ret = opsForList.rightPush(RedisPriorityQueue.NORMAL_QUEUE.getCode(), FastJsonConvertUtil.convertObjectToJSON(mailSend));
			size = opsForList.size(RedisPriorityQueue.NORMAL_QUEUE.getCode());
		}else {
			ret = opsForList.rightPush(RedisPriorityQueue.FAST_QUEUE.getCode(), FastJsonConvertUtil.convertObjectToJSON(mailSend));
			size = opsForList.size(RedisPriorityQueue.FAST_QUEUE.getCode());
			
		}
		if(ret==size) {
			mailSend.setSendStatus(MailStatus.SEND_IN.getCode());
			if(mailSend.getSendId().hashCode()%2 == 0){
				mailSend2Mapper.updateByPrimaryKey(mailSend);
			} else {
				mailSend1Mapper.updateByPrimaryKey(mailSend);
			}
			LOGGER.info("----------【进入队列成功，ID: {}】----------", mailSend.getSendId());
		} else {
			LOGGER.error("----------【进入队列失败，等待重新投递, ID: {}】----------", mailSend.getSendId());
		}
	}
	
	@ReadOnlyConnection
	public List<MailSend> queryDraftList(){
		List<MailSend> list = new ArrayList<>();
		list.addAll(mailSend1Mapper.queryDraftList());
		list.addAll(mailSend2Mapper.queryDraftList());
		
		return list;
	}
	
}
