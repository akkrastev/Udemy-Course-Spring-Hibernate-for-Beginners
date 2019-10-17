package com.luv2code.hibernate.challenge.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.challenge.entity.Employee;

public class CreateEmployeeDemo {


	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				                 .configure("hibernate.cfg.xml")
				                 .addAnnotatedClass(Employee.class)
				                 .buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			System.out.println("Creating new employee object...");
			Employee tempEmployee = new Employee("Angel", "Krastev", "Google");
			
			// start a transaction
			session.beginTransaction();
			
			// save the employee
			System.out.println("Saving the employee...");
			session.save(tempEmployee);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done");
		}
		finally {
			factory.close();
		}
	}

}
