package com.bhz.mail.config.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ReadOnlyConnectionInterceptor implements Ordered {

	public static final Logger LOGGER = LoggerFactory.getLogger(ReadOnlyConnectionInterceptor.class);
	
	@Around("@annotation(readOnlyConnection)")
	public Object proces(ProceedingJoinPoint proceedingJoinPoint, ReadOnlyConnection readOnlyConnection) throws Throwable {
		try {
			LOGGER.info("------------set datasource connection 2 readonly-------------");
			DataBaseContextHolder.setDataBaseType(DataBaseContextHolder.DataBaseType.SLAVE);
			Object result = proceedingJoinPoint.proceed();
			return result;
		}finally {
			DataBaseContextHolder.clearDataBaseType();
			LOGGER.info("------------clear datasource connection-------------");
		}
	}

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
