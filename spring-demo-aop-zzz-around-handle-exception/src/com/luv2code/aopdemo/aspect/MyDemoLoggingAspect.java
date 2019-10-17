package com.luv2code.aopdemo.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
	
	private Logger myLogger = Logger.getLogger(MyDemoLoggingAspect.class.getName());
	
	@Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
	public Object aroundGetFotune(
			ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
		
		// print out method we are advising on
		 String method = theProceedingJoinPoint.getSignature().toShortString();
		 myLogger.info("\n========>>> Executing @Around on method: " + method);
		 
		// get start timestamp
		 long begin = System.currentTimeMillis();
		
		// execute the method
		Object result = null;
		
		try {
			result = theProceedingJoinPoint.proceed();
		} catch (Exception e) {
			
			// log the exception
			myLogger.warning(e.getMessage());
			
			// rethrow exception 
			throw e;
		}
		
		// get end timestamp
		long end = System.currentTimeMillis();
		
		// compute the duration and display it
		long duration = end - begin;
		myLogger.info("\n========>>> Duration: " + duration / 1000.0 + "seconds");
		
		return result;
	}
	
	@After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountAdvice(JoinPoint theJoinPoint) {
		// print out which method you are advising on 
	    String method = theJoinPoint.getSignature().toShortString();
	    myLogger.info("\n========>>> Executing @After (finally) on method: " + method);		
		
	}
	
	@AfterThrowing(
			pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			throwing="theExc")
	public void afterThrowingFindAccountAdvice(
			JoinPoint joinPoint, Throwable theExc) {
		
		// print out which method you are advising on 
		String method = joinPoint.getSignature().toShortString();
		myLogger.info("\n========>>> Executing @AfterThrowing on method: " + method);
		
		// log the exception
		myLogger.info("\n========>>> The exception is " + theExc);
	}
	
	@AfterReturning(
			pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			returning="result")
	public void afterReturningFindAccountsAdvice(
			JoinPoint theJoinPoint, List<Account> result) {
		
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n========>>> Executing @AfterReturning on method: " + method);
		
		// print out the results of the method call
		myLogger.info("\n========>>> result is: " + result);
		
		// post-process the data ... modify it
		
		// convert the account name to uppercase
		convertAccountNamesToupperCase(result);
		
		myLogger.info("\n========>>> result is: " + result);
	}

	private void convertAccountNamesToupperCase(List<Account> result) {
		
		// loop thru accounts
		for(Account tempAccount: result) {
			
			String thrUpperName = tempAccount.getName().toUpperCase();
			tempAccount.setName(thrUpperName);
		}
		  
	}

	@Before("com.luv2code.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {	
		myLogger.info("\n=======>>> Executing @Before advice on addAccount()");
		
		// display the method signature
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
		myLogger.info("Method: " + methodSig);
		
		// display the method arguments
		
		// get args
		Object[] args = theJoinPoint.getArgs();
		
		// loop thru args
		for(Object tempArg : args) {
			myLogger.info(tempArg.toString());
			
			if(tempArg instanceof Account) {
				// downcast and print Account specific stuff
				Account theAccount = (Account) tempArg;
				
				myLogger.info("account name: " + theAccount.getName());
				myLogger.info("account level: " + theAccount.getLevel());
			}
		}
	}
		
}
