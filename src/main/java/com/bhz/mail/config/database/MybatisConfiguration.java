package com.bhz.mail.config.database;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.apache.bcel.util.ClassLoaderRepository;
import org.aspectj.apache.bcel.util.ClassLoaderRepository.SoftHashMap;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
/****
 * <p>Title:MybatisConfiguration</p>
 * <p>Description: 设置数据源</p>
 * <p>Company: </p>
 * @author MrWang
 * @date 2018年3月15日 下午11:01:10
 */
@Configuration
@AutoConfigureAfter({DataSourceConfiguration.class})
public class MybatisConfiguration extends MybatisAutoConfiguration {
	
	@Resource(name = "masterDataSource")
	private DataSource masterDataSource;
  
	@Resource(name = "slaveDataSource")
	private DataSource slaveDataSource;
 
	@Bean(name="sqlSessionFactory")
  	public SqlSessionFactory sqlSessionFactory() throws Exception {
		return super.sqlSessionFactory(roundRobinDataSouceProxy());
	}
 
	public AbstractRoutingDataSource roundRobinDataSouceProxy(){
		ReadWriteSplitRoutingDataSource proxy = new ReadWriteSplitRoutingDataSource();
		SoftHashMap targetDataResources = new ClassLoaderRepository.SoftHashMap();
		targetDataResources.put(DataBaseContextHolder.DataBaseType.MASTER, masterDataSource);
		targetDataResources.put(DataBaseContextHolder.DataBaseType.SLAVE, slaveDataSource);
    	proxy.setDefaultTargetDataSource(masterDataSource);//默认源
    	proxy.setTargetDataSources(targetDataResources);
    	return proxy;
	}
	
}
