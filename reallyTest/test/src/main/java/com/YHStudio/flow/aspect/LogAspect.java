package com.YHStudio.flow.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/** 
* @author : 刘杰
* @date 创建时间：2018年9月23日 下午8:20:34 
* @version 1.0 
* @ClassName: LogAspect
* 日志切面
*/
@Aspect
@Component(value="logAspect")
public class LogAspect {

	// 定义切点表达式
	@Pointcut("execution(* com.YHStudio.flow.dao.Mapper.*(..))")
	public void logPointCut() {};
	
	// 前置通知
	@Before("logPointCut()")
	public void logBefore(JoinPoint jp) {
		// 打印方法执行之前的日志
		System.out.println(jp.getSignature().getName() + "方法开始执行");
	}

	// 异常通知
	@AfterThrowing(value="logPointCut()", throwing="error")
	public void logThrows(Exception error) {
		// 打印方法执行异常的日志
		System.out.println("异常信息:" + error.getMessage());
	}
	
	// 后置通知
	@After("logPointCut()")
	public void logAfter(JoinPoint jp) {
		// 打印方法执行之后的日志
		System.out.println(jp.getSignature().getName() + "方法执行结束!");
	}
	
}
