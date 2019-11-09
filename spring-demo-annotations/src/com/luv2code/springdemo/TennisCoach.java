package com.luv2code.springdemo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component 
public class TennisCoach implements Coach {

	@Autowired
	@Qualifier("randomFortuneService")
	private FortuneService fortuneService;
	
	// define a default constructor
	public TennisCoach() {
		System.out.println(">> TennisCoach: inside default constructor <<");
	}
	
	// define my init method
	@PostConstruct
	public void doMyStartUpStuff() {
		System.out.println("\nTennisCoach: inside of doMyStartUpStuff()");
	}
	
	// define my destroy method
	@PreDestroy
	public void doMyCleanUpStuff() {
		System.out.println("\nTennisCoach: inside of doMyCleanUpStuff()");
	}
		
//	@Autowired
//	public TennisCoach(FortuneService theFortuneService) {
//		this.fortuneService = theFortuneService;
//	}
	
	// define a setter method
//	@Autowired
//	public void setFortuneService(FortuneService fortuneService) {
//		System.out.println(">> TennisCoach: inside setFortuneService() method <<");
//		this.fortuneService = fortuneService;
//	}
 


	@Override
	public String getDailyWorkout() {
		return  "Practice your backhand volley";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

}