package com.luv2code.hibernate.challenge.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.challenge.entity.Employee;

public class RetrieveEmployeeDemo {

	public static void main(String[] args) {

        // create session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Employee.class)
								 .buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// create new employee object
			System.out.println("Creating new employee object...");
			Employee tempEmployee = new Employee("Miroslav", "Krushev", "Facebook");
			
			// start a transaction
			session.beginTransaction();
			
			// save the employee
			session.save(tempEmployee);
			
			// commit transaction
			session.getTransaction().commit();
			
			// find out the employee's id: primary key
			System.out.println("Saved employee. Generated id: " + tempEmployee.getId());
			
			// start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve the student based on the id: primary key
			Employee myEmployee = session.get(Employee.class, tempEmployee.getId());
			System.out.println("Get completed" + myEmployee);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!!!");
		}
		finally {
			factory.close();
		}
		

	}

}
