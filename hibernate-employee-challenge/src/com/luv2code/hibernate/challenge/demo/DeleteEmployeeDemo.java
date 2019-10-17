package com.luv2code.hibernate.challenge.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.challenge.entity.Employee;

public class DeleteEmployeeDemo {

	public static void main(String[] args) {
	
		// create session factory
		SessionFactory factory = new Configuration()
							     .configure("hibernate.cfg.xml")
							     .addAnnotatedClass(Employee.class)
							     .buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();
			
			// delete the employee based on the id: primary key
			System.out.println("Deleting employee id=3");
			session.createQuery("delete from Employee where id=3").executeUpdate();
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!!!");
		}
		finally {
			factory.close();
		}
		

	}

}
