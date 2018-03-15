package com.bhz.mail;

import java.sql.Connection;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTests {


//	@Resource(name = "masterDataSource")
//	private DataSource masterDataSource;
//  
//	@Resource(name = "slaveDataSource")
//	private DataSource slaveDataSource;
//
//	/**
//	 * 
//	 * <B>方法名称：</B>测试主从<BR>
//	 * <B>概要说明：</B><BR>
//	 * @author baihezhuo
//	 * @since 2017年5月4日 上午10:35:49
//	 * @throws Exception
//	 */
//	@Test
//	public void contextLoads() throws Exception {
//		Connection c1 = masterDataSource.getConnection("root", "root");
//		System.err.println("c1:" + c1.getMetaData().getURL());
//		Connection c2 = slaveDataSource.getConnection("root", "root");
//		System.err.println("c2:" + c2.getMetaData().getURL());
//	}
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Test
	public void test3() throws Exception {
		
		ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
		
//		opsForValue.set("name", "yxxy");
		
		System.err.println("name: " + opsForValue.get("name"));
		redisTemplate.delete("name");
		
	}

}
