package com.angel.dichallenge;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ChallengeHelloSpringApp {

	public static void main(String[] args) {
		
		// load spring configuration file
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		// retrieve bean from spring container
		Coach theGolfCoach = context.getBean("myGolfCoach", Coach.class);
		
		// call method on the bean
		System.out.println(theGolfCoach.getDailyWorkout());
		System.out.println(theGolfCoach.getDailyFortune());
		
		// close the context
		context.close();

	}

}
