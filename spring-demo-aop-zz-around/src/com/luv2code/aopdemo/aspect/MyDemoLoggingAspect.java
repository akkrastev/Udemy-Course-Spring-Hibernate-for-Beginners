package com.luv2code.aopdemo.aspect;

import java.util.List;

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
	
	@Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
	public Object aroundGetFotune(
			ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
		
		// print out method we are advising on
		 String method = theProceedingJoinPoint.getSignature().toShortString();
		 System.out.println("\n========>>> Executing @Around on method: " + method);
		 
		// get start timestamp
		 long begin = System.currentTimeMillis();
		
		// execute the method
		Object result = theProceedingJoinPoint.proceed();
		
		// get end timestamp
		long end = System.currentTimeMillis();
		
		// compute the duration and display it
		long duration = end - begin;
		System.out.println("\n========>>> Duration: " + duration / 1000.0 + "seconds");
		
		return result;
	}
	
	@After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountAdvice(JoinPoint theJoinPoint) {
		// print out which method you are advising on 
	    String method = theJoinPoint.getSignature().toShortString();
	    System.out.println("\n========>>> Executing @After (finally) on method: " + method);		
		
	}
	
	@AfterThrowing(
			pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			throwing="theExc")
	public void afterThrowingFindAccountAdvice(
			JoinPoint joinPoint, Throwable theExc) {
		
		// print out which method you are advising on 
		String method = joinPoint.getSignature().toShortString();
		System.out.println("\n========>>> Executing @AfterThrowing on method: " + method);
		
		// log the exception
		System.out.println("\n========>>> The exception is " + theExc);
	}
	
	@AfterReturning(
			pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			returning="result")
	public void afterReturningFindAccountsAdvice(
			JoinPoint theJoinPoint, List<Account> result) {
		
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n========>>> Executing @AfterReturning on method: " + method);
		
		// print out the results of the method call
		System.out.println("\n========>>> result is: " + result);
		
		// post-process the data ... modify it
		
		// convert the account name to uppercase
		convertAccountNamesToupperCase(result);
		
		System.out.println("\n========>>> result is: " + result);
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
		System.out.println("\n=======>>> Executing @Before advice on addAccount()");
		
		// display the method signature
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
		System.out.println("Method: " + methodSig);
		
		// display the method arguments
		
		// get args
		Object[] args = theJoinPoint.getArgs();
		
		// loop thru args
		for(Object tempArg : args) {
			System.out.println(tempArg);
			
			if(tempArg instanceof Account) {
				// downcast and print Account specific stuff
				Account theAccount = (Account) tempArg;
				
				System.out.println("account name: " + theAccount.getName());
				System.out.println("account level: " + theAccount.getLevel());
			}
		}
	}
		
}
