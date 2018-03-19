package com.bhz.mail.mapper;

import java.util.List;

import com.bhz.mail.config.database.BaseMapper;
import com.bhz.mail.entity.MailSend;
/****
 * 
 * Title:MailSend1Mapper
 * Description: 表1操作
 * Company: 
 * @author MrWang
 * @date 2018年3月18日 上午12:27:27
 */
public interface MailSend1Mapper  {
	   int deleteByPrimaryKey(String sendId);

	    int insert(MailSend record);

	    int insertSelective(MailSend record);

	    MailSend selectByPrimaryKey(String sendId);

	    int updateByPrimaryKeySelective(MailSend record);

	    int updateByPrimaryKey(MailSend record);

		List<MailSend> queryDraftList();
}