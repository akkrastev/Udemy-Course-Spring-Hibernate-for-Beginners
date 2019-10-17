package com.luv2code.springdemo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

@Component
public class ChallengeFortuneService implements FortuneService {

	private String fileName = "C:\\Users\\Acer\\eclipse-workspace\\spring-demo-annotations\\src\\challenge.fortunes.txt";
	private List<String> fortunes;
	
	// create a random number generator
	private Random myRandom = new Random();
	
	public ChallengeFortuneService() {
		File theFile = new File(fileName);
		
		System.out.println("Reading fortunes from file: " + theFile);
		System.out.println("File exists: " + theFile.exists());
		
		fortunes = new ArrayList<String>();
		
		try (BufferedReader br = new BufferedReader(
				new FileReader(theFile))){
			
			String tempLine;
			
			while((tempLine = br.readLine()) != null) {
				fortunes.add(tempLine);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	// define my init method
    @PostConstruct
	public void doMyStartUpStuff() {
		System.out.println("\nChallengeFortuneService: inside of doMyStartUpStuff()");
	}
		
	// define my destroy method
	@PreDestroy
	public void doMyCleanUpStuff() {
		System.out.println("\nChallengeFortuneService: inside of doMyCleanUpStuff()");
	}
	
	
	@Override
	public String getFortune() {
		int index = myRandom.nextInt(fortunes.size());
		String fortune = fortunes.get(index);
		return fortune;
	}

}
