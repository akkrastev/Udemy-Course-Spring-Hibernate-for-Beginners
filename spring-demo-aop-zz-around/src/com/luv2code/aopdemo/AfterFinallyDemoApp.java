package com.luv2code.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;

public class AfterFinallyDemoApp {

	public static void main(String[] args) {
		
		// read spring config java class
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// get the bean from the spring container
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		List<Account> theAccounts = null;
		
		try {
			
		    // boolean flag to simulate an exceptions 
			boolean tripWire = false;
			theAccounts = theAccountDAO.findAccounts(tripWire);
		
		}
		catch (Exception e) {
			System.out.println("\n\nMain program ... cought exception " + e); 
		}
		// display the accounts
		System.out.println("\n\n Main Program: AfterThrowingDemoApp");
		System.out.println("---");
		System.out.println(theAccounts);
		
		System.out.println("\n");
		
		// close the context
		context.close();

	}

}
