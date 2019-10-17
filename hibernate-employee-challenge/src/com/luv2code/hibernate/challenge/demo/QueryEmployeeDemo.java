package com.luv2code.hibernate.challenge.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.challenge.entity.Employee;

public class QueryEmployeeDemo {

	
	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
							     .configure("hibernate.cfg.xml")
							     .addAnnotatedClass(Employee.class)
							     .buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			// create 3 new employees
			System.out.println("Creating  3 new employee objects...");
			Employee tempEmployee1 = new Employee("Ivan", "Pecev", "WallTopia");
			Employee tempEmployee2 = new Employee("Kamen", "Marinov", "Kamenstroi");
			Employee tempEmployee3 = new Employee("Elena", "Krasteva", "Amazon");
			
			// start a transaction
			session.beginTransaction();
			
			// save
			session.save(tempEmployee1);
			session.save(tempEmployee2);
			session.save(tempEmployee3);
			
			// commit transaction
			session.getTransaction().commit();
			
			// start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// query employees: company = 'Google'
			List<Employee> theEmployees = session.createQuery("from Employee e where e.company='Google'")
										  .getResultList();
			
			System.out.println("Employees who work in Google:");
			for (Employee employee : theEmployees) {
				System.out.println(employee);
			}
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!!!");

		}
		finally {
			factory.close();
		}
	}
}
