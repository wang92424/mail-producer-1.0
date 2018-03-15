package com.bhz.mail.service.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bhz.mail.entity.MailSend;
import com.bhz.mail.service.MailSendService;
/***
 * 
 * <p>Title:ProducerController</p>
 * <p>Description: 控制层</p>
 * <p>Company: </p>
 * @author MrWang
 * @date 2018年3月15日 下午11:04:26
 */
@RestController
public class ProducerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProducerController.class);
	
	@Autowired
	private MailSendService mailSendService;
	
	@RequestMapping(value="/send", produces = {"application/json;charset=UTF-8"})
	public void send(@RequestBody(required = false) MailSend mailSend) {
		
		try {
			
			
		}catch(Exception e) {
			LOGGER.info("异常信息:{}",e);
			
			throw new RuntimeException(e);
			
		}
		
	}
	
}
