package com.adf.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
    	Session session = factory.getCurrentSession();
    	try {
    		System.out.println("Creating new student object....");
    		Student tempStudent1 = new Student("John", "Doe", "john@adf.com");
    		Student tempStudent2 = new Student("Mary", "Public", "mary@adf.com");
    		Student tempStudent3 = new Student("Bonita", "Applebum", "bonita@adf.com");
    		
    		session.beginTransaction();
    		System.out.println("Saving the students...");
    		
    		session.save(tempStudent1);
    		session.save(tempStudent2);
    		session.save(tempStudent3);
    		
    		session.getTransaction().commit();
    		System.out.println("Done!");
    	}
    	finally {
    		factory.close();
    	}
	}
}
