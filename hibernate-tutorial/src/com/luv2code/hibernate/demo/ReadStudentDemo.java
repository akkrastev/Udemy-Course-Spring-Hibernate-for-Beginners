package com.luv2code.hibernate.demo;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// create a student object
			System.out.println("Creating new student object...");
			
			String dateOfBirthStr = "31/12/1998";
			Date dateOfBirth = DateUtils.parseDate(dateOfBirthStr);
			
			Student tempStudent = new Student("Duffy", "Duck", "duffy@luv2code.com", dateOfBirth);
			
			// start a transaction
			session.beginTransaction();
			
			// save the student object
			System.out.println("Saving the student...");
			System.out.println(tempStudent);
			session.save(tempStudent);
			
			// commit transaction
			session.getTransaction().commit();
			
			// MY NEW CODE
			
			// find out the student's id: primary key
			System.out.println("Saved student. Generated id: " + tempStudent.getId());
			
			// now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve the student based on the id: primary key
			System.out.println("\nGetting student with id:" + tempStudent.getId());
			Student myStudent = session.get(Student.class, tempStudent.getId());
			System.out.println("Get completed: " + myStudent.toString());
			
			// commit the transaction 
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			factory.close();
		}
	}

}