package com.bhz.mail;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
/***
 * 
 * <p>Title:MainConfig</p>
 * <p>Description: MainConfig</p>
 * <p>Company: sa</p>
 * @author Administrator
 * @date 2018年3月13日 上午11:09:30
 */
@EnableWebMvc   //启用spring mvc
@Configuration	//xml配置类
@ComponentScan({"com.bhz.mail.*"})
@MapperScan(basePackages="com.bhz.mail.mapper")
public class MainConfig {

}
