package com.bhz.mail.mapper;

import java.util.List;

import com.bhz.mail.config.database.BaseMapper;
import com.bhz.mail.entity.MailSend;
/*****
 * 
 * Title:MailSend2Mapper
 * Description: 表二操作
 * Company: 
 * @author MrWang
 * @date 2018年3月18日 上午12:27:55
 */
public interface MailSend2Mapper {
	   int deleteByPrimaryKey(String sendId);

	    int insert(MailSend record);

	    int insertSelective(MailSend record);

	    MailSend selectByPrimaryKey(String sendId);

	    int updateByPrimaryKeySelective(MailSend record);

	    int updateByPrimaryKey(MailSend record);

		List<MailSend> queryDraftList();
	
}