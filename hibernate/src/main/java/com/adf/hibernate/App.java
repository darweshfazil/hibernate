package com.adf.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Student.class)
								 .buildSessionFactory();
		
    	Session session = factory.getCurrentSession();
    	try {
    		System.out.println("Creating new student object....");
    		Student tempStudent = new Student("Paul", "Wall", "paul@adf.com");
    		session.beginTransaction();
    		System.out.println("Saving the student...");
    		session.save(tempStudent);
    		session.getTransaction().commit();
    		System.out.println("Done!");
    	}
    	finally {
    		factory.close();
    	}
    }
}
