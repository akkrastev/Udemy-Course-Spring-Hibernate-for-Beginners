package com.luv2code.springdemo;

public class TrackCoach implements Coach {

	// define a private field for the dependency
		private FortuneService fortuneService;
				
		public TrackCoach() {}
		
		// define a constructor for dependency injection
		public TrackCoach(FortuneService theFortuneService) {
			fortuneService = theFortuneService;
		}
	
	
	@Override
	public String getDailyWorkout() {
		return "Run a hard 5k";
	}

	@Override
	public String getDailyFortune() {
		// TODO Auto-generated method stub
		return  "Just Do It: " + fortuneService.getFortune();
	}
	
	// add init method
	public void doMyStartupStuff() {
		System.out.println("TrackCoach: inside method doMyStartupStuff");
	}
	
	// add destroy method
	public void doMyCleanupStuff() {
		System.out.println("TrackCoach: inside method doMyCleantupStuff");
	}
}
