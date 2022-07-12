package com.adf.hibernate5;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseDemo 
{
    @SuppressWarnings("deprecation")
	public static void main( String[] args ) throws HibernateException
    {
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
		
    	Session session = factory.getCurrentSession();
    	try {
    		
    		session.beginTransaction();
    		
    		int theId = 1;
    		Instructor tempInstructor = (Instructor) session.get(Instructor.class, theId);
    		
    		Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
    		Course tempCourse2 = new Course("The Pinball Masterclass");
    		
    		tempInstructor.add(tempCourse1);
    		tempInstructor.add(tempCourse2);
    		
    		session.save(tempCourse1);
    		session.save(tempCourse2);
    		
    		// commit transaction
    		session.getTransaction().commit();
    		
    		System.out.println("Done!");
    	}
    	finally {
    		
    		factory.close();
    	}
    }
}
