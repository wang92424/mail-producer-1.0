package com.bhz.mail.service.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bhz.mail.constant.Const;
import com.bhz.mail.entity.MailSend;
import com.bhz.mail.enumeration.MailStatus;
import com.bhz.mail.service.MailSendService;
import com.bhz.mail.utils.KeyUtil;
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
		
		// 1 尽量缩小代码的范围, 并且捕获异常的时候 一定要 捕获比较细粒度的异常 既然catch 所以一定要进行处理
		// 2 如果是有事务存在的, 那么在catch 的时候 一定要进行手工的回滚异常

		try {
			//1. MailSend ---> validate 数据校验、业务校验
			//2. insert
			mailSend.setSendId(KeyUtil.generatorUUID());
			mailSend.setSendCount(0L);
			mailSend.setSendStatus(MailStatus.DRAFT.getCode());
			mailSend.setVersion(0L);
			//updateTime
			mailSend.setUpdateBy(Const.SYS_RUNTIME);
			mailSendService.insert(mailSend);
			//3. 把数据扔到redis里去 并且更新数据库状态
			mailSendService.sendRedis(mailSend);

			
		}catch(Exception e) {
			LOGGER.info("异常信息:{}",e);
			
			throw new RuntimeException(e);
			
		}
		
	}
	
}
