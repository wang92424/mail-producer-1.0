package com.bhz.mail.config.database;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
/*****
 * 
 * <p>Title:ReadWriteSplitRoutingDataSource</p>
 * <p>Description: 主从数据库切换</p>
 * <p>Company: </p>
 * @author MrWang
 * @date 2018年3月15日 下午11:00:33
 */
public class ReadWriteSplitRoutingDataSource extends AbstractRoutingDataSource {
	 
	  @Override
	  protected Object determineCurrentLookupKey() {
		  return DataBaseContextHolder.getDataBaseType();
	  }
}
