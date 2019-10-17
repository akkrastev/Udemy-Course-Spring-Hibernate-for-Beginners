package com.luv2code.hibernate.challenge.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.challenge.entity.Employee;


public class UpdateEmployeeDemo {

	public static void main(String[] args) {
		
		// create session factory
				SessionFactory factory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Employee.class)
										.buildSessionFactory();
				
				// create session
				Session session = factory.getCurrentSession();
				
				try {
					int employeeId = 1;
			
					// now get a new session and start transaction
					session = factory.getCurrentSession();
					session.beginTransaction();
					
					// retrieve the employee based on the id: primary key
					System.out.println("\nGetting employee with id:" + employeeId);
					Employee myEmployee = session.get(Employee.class, employeeId);
					
					System.out.println("Updating employee...");
					myEmployee.setFirstName("Scooby");
						
					// commit the transaction 
					session.getTransaction().commit();
					
					System.out.println("Done!");
				}
				finally {
					factory.close();
				}

	}

}
