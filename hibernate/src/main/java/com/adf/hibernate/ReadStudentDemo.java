package com.adf.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
    	Session session = factory.getCurrentSession();
    	try {
    		
    		// inserting a student
    		System.out.println("Creating new student object....");
    		Student tempStudent = new Student("Daffy", "Duck", "daffy@adf.com");
    		session.beginTransaction();
    		System.out.println("Saving the student...");
    		System.out.println(tempStudent);
    		session.save(tempStudent);
    		session.getTransaction().commit();
    		
    		System.out.println("Saved student. Generate id: "+tempStudent.getId());
    		
    		session = factory.getCurrentSession();
    		session.beginTransaction();
    		
    		System.out.println("\nGetting student with id: "+tempStudent.getId());
    		
    		Student myStudent = (Student) session.get(Student.class, tempStudent.getId());
    		
    		System.out.println("Get complete: " +myStudent);
    		
    		session.getTransaction().commit();
    		
    		System.out.println("Done!");
    	}
    	finally {
    		factory.close();
    	}

	}
}
