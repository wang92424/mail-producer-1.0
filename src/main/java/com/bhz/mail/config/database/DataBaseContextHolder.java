package com.bhz.mail.config.database;
/****
 * 
 * <p>Title:DataBaseContextHolder</p>
 * <p>Description: 数据源实体</p>
 * <p>Company: </p>
 * @author MrWang
 * @date 2018年3月15日 下午11:02:49
 */
public class DataBaseContextHolder {
	
	public enum DataBaseType {
		MASTER,SLAVE
	}
		 
	private static final ThreadLocal<DataBaseType> contextHolder = new ThreadLocal<DataBaseType>();
 
	public static void setDataBaseType(DataBaseType dataBaseType){
		if(dataBaseType == null)throw new NullPointerException();
		contextHolder.set(dataBaseType);
	}
 
	public static DataBaseType getDataBaseType(){
		return contextHolder.get() == null ? DataBaseType.MASTER : contextHolder.get();
	}
 
	public static void clearDataBaseType(){
		contextHolder.remove();
 	}
		  
}
