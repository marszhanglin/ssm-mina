package com.aop;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.utils.RequestUtils;

/**
 * 测试after,before,around,throwing,returning Advice.
 * @author Admin
 *
 */
/**
 * 
 * <B style="color:#00f"> AOP切面测试</B>
 * <br>https://my.oschina.net/itblog/blog?sort=time&p=13&temp=1512721046448
 * @author zhanglin  2017-12-11
 */
@Aspect
@Component
public class AspceJAdviceTest {

	/**
	 * Pointcut
	 * 定义Pointcut，Pointcut的名称为aspectjMethod()，此方法没有返回值和参数
	 * 该方法就是一个标识，不进行调用
	 */
	@Pointcut("execution(* com.mvc..*.*(..))")
	//@Pointcut("execution(* mars.springmvc.ctr..*.*(..))")
	//@Pointcut("execution(* mars.springmvc.ctr.TestController.*(..))")
	private void aspectjMethod(){};
	
	/** 
	 * Before
	 * 在核心业务执行前执行，不能阻止核心业务的调用。
	 * @param joinPoint 
	 */  
	@Before("aspectjMethod()")  
	public void beforeAdvice(JoinPoint joinPoint) {  
		//System.out.println("##################beforeAdvice()-----此处意在执行核心业务逻辑前，做一些安全性的判断等等");
		Object[] argObjs=joinPoint.getArgs();
		HttpServletRequest request = null;
		List<String> urlValues=new ArrayList<String>();
		for(Object object:argObjs){
			if(null==object)continue;
			if(object instanceof HttpServletRequest){
				request = (HttpServletRequest) object;
			}
			if(object instanceof String){
				urlValues.add((String)object);
			}
		}
		
		RequestUtils.showUrlStringParams(urlValues);
		RequestUtils.showParams(request);
		RequestUtils.showHeaders(request);
		System.out.println("=========================start==========================");
		
	}
	
	/** 
	 * After 
	 * 核心业务逻辑退出后（包括正常执行结束和异常退出），执行此Advice
	 * @param joinPoint
	 */
	@After(value = "aspectjMethod()")  
	public void afterAdvice(JoinPoint joinPoint) {  
		//System.out.println("##################afterAdvice()-----此处意在执行核心业务逻辑之后，做一些日志记录操作等等");
		System.out.println("=========================end==========================");
	}  

	/** 
	 * Around 
	 * 手动控制调用核心业务逻辑，以及调用前和调用后的处理,
	 * 
	 * 注意：当核心业务抛异常后，立即退出，转向AfterAdvice
	 * 执行完AfterAdvice，再转到ThrowingAdvice
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */ 
	@Around(value = "aspectjMethod()")  
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {  
		//System.out.println("##################aroundAdvice()-----此处可以做类似于Before Advice的事情");
		
		//调用核心逻辑
		Object retVal = pjp.proceed();
		//System.out.println(" 此处可以做类似于After Advice的事情");
		return retVal;
	}  
	
	
	
	
	
	/**
	 * AfterReturning
	 * 核心业务逻辑调用正常退出后，不管是否有返回值，正常退出后，均执行此Advice
	 * (它可用于 限定  切入点之匹配具有对应返回值类型的方法)
	 * @param joinPoint
	 * @param retVal  只切入返回值为String的方法
	 */
	@AfterReturning(value = "aspectjMethod()", returning = "retVal")  
	public void afterReturningAdvice(JoinPoint joinPoint, String retVal) {  
	    System.out.println("##################afterReturningAdvice()-----此处可以对返回值做进一步处理");
	    System.out.println("Return Value: " + retVal); 
	}
	
	
//	/**
//	 * AfterReturning
//	 * 核心业务逻辑调用正常退出后，不管是否有返回值，正常退出后，均执行此Advice
//	 * (它可用于 限定  切入点之匹配具有对应返回值类型的方法)
//	 * @param joinPoint
//	 * @param retVal  只切入返回值为String的方法
//	 */
//	@AfterReturning(value = "aspectjMethod()", returning = "retVal")  
//	public void afterReturningAdvice(JoinPoint joinPoint, BaseRespone retVal) {  
//	    //System.out.println("##################afterReturningAdvice()-----此处可以对返回值做进一步处理,可通过joinPoint来获取所需要的内容");
//		System.out.println("--------------afterReturningAdvice----------------");
//		System.out.println("Return Value: " + new Gson().toJson(retVal)); 
//	    retVal.setRetMsg("fail");//可以篡改
//	}
	
	/**
	 * 核心业务逻辑调用异常退出后，执行此Advice，处理错误信息
	 * 注意：是抛出的异常，不是捕获的异常
	 * 注意：执行顺序在Around Advice之后
	 * @param joinPoint
	 * @param ex
	 */
	@AfterThrowing(value = "aspectjMethod()", throwing = "ex")  
	public void afterThrowingAdvice(JoinPoint joinPoint, Exception ex) {  
		//System.out.println("##################afterThrowingAdvice()-----此处意在执行核心业务逻辑出错时，捕获异常，并可做一些日志记录操作等等");
		System.out.println(" 异常位置："+joinPoint.getSignature().getDeclaringTypeName() + 
                "." + joinPoint.getSignature().getName());
		System.out.println(" 错误信息："+ex.getMessage());
	}  
}
