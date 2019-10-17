package com.angel.dichallenge;

public class GolfCoach implements Coach {

	private FortuneService randomFortuneService;
	

	public void setRandomFortuneService(FortuneService randomFortuneService) {
		this.randomFortuneService = randomFortuneService;
	}

	@Override
	public String getDailyWorkout() {
		return "Practice your putting skills for 2 hours today";
	}

	@Override
	public String getDailyFortune() {
		return randomFortuneService.getFortune();
	}

}
