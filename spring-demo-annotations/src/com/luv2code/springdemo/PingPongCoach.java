package com.luv2code.springdemo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class PingPongCoach implements Coach {

	@Autowired
	@Qualifier("challengeFortuneService")
	private FortuneService fortuneService;
	
	// define a default constructor
	public PingPongCoach() {
		System.out.println(">> PingPongCoach: inside default constructor");
	}
	
//	// define my init method
//    @PostConstruct
//	public void doMyStartUpStuff() {
//		System.out.println("\nPingPongCoach: inside of doMyStartUpStuff()");
//	}
//		
//	// define my destroy method
//	@PreDestroy
//	public void doMyCleanUpStuff() {
//		System.out.println("\nPingPongCoach: inside of doMyCleanUpStuff()");
//	}
		
	@Override
	public String getDailyWorkout() {
		return "Practice your pingpong drop shot";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

}
