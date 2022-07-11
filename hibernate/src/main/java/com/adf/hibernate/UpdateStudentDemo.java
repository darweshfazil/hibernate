package com.adf.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
    	Session session = factory.getCurrentSession();
    	try {
    		
    		int studentId = 1;
    		
    		session = factory.getCurrentSession();
    		session.beginTransaction();
    		
    		System.out.println("\nGetting student with id: "+studentId);
    		
    		Student myStudent = (Student) session.get(Student.class, studentId);
    		
    		System.out.println("Updating student...");
    		myStudent.setFirstName("Scooby");
    		
    		session.getTransaction().commit();
    		
    		session = factory.getCurrentSession();
    		session.beginTransaction();
    		
    		System.out.println("Update email for all students");
    		
    		session.createQuery("update Student set email='foo@gmail.com'").executeUpdate();
    		
    		session.getTransaction().commit();
    		
    		System.out.println("Done!");
    	}
    	finally {
    		factory.close();
    	}

	}
}
