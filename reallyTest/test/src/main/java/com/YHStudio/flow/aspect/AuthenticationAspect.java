package com.YHStudio.flow.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/** 
* @author : 刘杰
* @date 创建时间：2018年9月23日 下午7:50:51 
* @version 1.0 
* @ClassName: AuthenticationAspect
* 权限验证切面
*/
@Aspect
@Component(value="authenticationAspect")
public class AuthenticationAspect {
	
	// 定义切点表达式
	@Pointcut("execution(* com.YHStudio.flow.dao.Mapper.queryTableIsExists(..))")
	public void queryTabIsExistsPointCut() {};
	
	// 环绕通知
	@Around("queryTabIsExistsPointCut()")
	public Object queryTabIsExistsAround(ProceedingJoinPoint pjp) {
		// 获取 request 实例
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		// 获取 session 实例
		HttpSession session = request.getSession();
		// 获取 session 中共享的权限值
		Integer levelVal = (Integer) session.getAttribute("cus_level");
		Object result = null;
		try {
			// 拿到方法返回值
			result = pjp.proceed(pjp.getArgs());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		// 尾部处理
		if(levelVal > 1) return result;
		return null;
	}
	
}
